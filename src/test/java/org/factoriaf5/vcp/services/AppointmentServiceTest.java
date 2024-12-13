package org.factoriaf5.vcp.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.factoriaf5.vcp.dto.AppointmentDto;
import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.model.AppointmentStatus;
import org.factoriaf5.vcp.model.ConsultationType;
import org.factoriaf5.vcp.model.GenderType;
import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.model.User;
import org.factoriaf5.vcp.model.UserType;
import org.factoriaf5.vcp.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @InjectMocks
    AppointmentService service;

    @Mock
    AppointmentRepository repository;

    private Patient patient;

    @BeforeEach
    void setUp() {
        User user = new User("testUser", "password", UserType.USER, "1234567890");

        patient = new Patient("Buddy", user, 4, "Golden Retriever", GenderType.M, "https://example.com/images/buddy.jpg");
    }

    @Test
    void shouldFindAllAppointments() {
        // Arrange
        List<Appointment> appointments = Arrays.asList(
                new Appointment(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED, patient),
                new Appointment(LocalDate.of(2024, 12, 20), ConsultationType.FOLLOW_UP, "Follow-up visit", AppointmentStatus.COMPLETED, patient)
        );
        when(repository.findAll()).thenReturn(appointments);

        // Act
        List<Appointment> result = service.findAll();

        // Assert
        assertThat(result).isEqualTo(appointments);
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldSaveNewAppointment() {
        // Arrange
        AppointmentDto appointmentDto = new AppointmentDto(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED, patient);
        Appointment appointment = new Appointment(appointmentDto);
        when(repository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Appointment result = service.save(appointmentDto);

        // Assert
        assertThat(result).isEqualTo(appointment);
        assertThat(result.getPatient()).isEqualTo(patient);
        verify(repository, times(1)).save(any(Appointment.class));
    }

    @Test
    void shouldFindAppointmentById() {
        // Arrange
        Appointment appointment = new Appointment(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED, patient);
        when(repository.findById(1L)).thenReturn(Optional.of(appointment));

        // Act
        Optional<Appointment> result = service.findById(1L);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(appointment);
        assertThat(result.get().getPatient()).isEqualTo(patient);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldUpdateAppointment() {
        // Arrange
        Appointment existingAppointment = new Appointment(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED, patient);
        AppointmentDto updatedDto = new AppointmentDto(LocalDate.of(2024, 12, 20), ConsultationType.FOLLOW_UP, "Updated reason", AppointmentStatus.COMPLETED, patient);
        Appointment updatedAppointment = new Appointment(updatedDto);

        when(repository.findById(1L)).thenReturn(Optional.of(existingAppointment));
        when(repository.save(any(Appointment.class))).thenReturn(updatedAppointment);

        // Act
        Appointment result = service.updateAppointment(1L, updatedDto);

        // Assert
        assertThat(result).isEqualTo(updatedAppointment);
        assertThat(result.getPatient()).isEqualTo(patient);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Appointment.class));
    }

    @Test
    void shouldPartiallyUpdateAppointment() {
        // Arrange
        Appointment existingAppointment = new Appointment(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED, patient);
        Map<String, Object> updates = Map.of(
                "appointmentDate", "2024-12-20",
                "reason", "Updated reason",
                "status", "COMPLETED"
        );

        when(repository.findById(1L)).thenReturn(Optional.of(existingAppointment));
        when(repository.save(any(Appointment.class))).thenReturn(existingAppointment);

        // Act
        Appointment result = service.partialUpdateAppointment(1L, updates);

        // Assert
        assertThat(result.getAppointmentDate()).isEqualTo(LocalDate.of(2024, 12, 20));
        assertThat(result.getReason()).isEqualTo("Updated reason");
        assertThat(result.getStatus()).isEqualTo(AppointmentStatus.COMPLETED);
        assertThat(result.getPatient()).isEqualTo(patient);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(existingAppointment);
    }
}
