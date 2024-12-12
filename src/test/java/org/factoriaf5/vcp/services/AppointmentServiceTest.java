package org.factoriaf5.vcp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.factoriaf5.vcp.dto.AppointmentDto;
import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.model.AppointmentStatus;
import org.factoriaf5.vcp.model.ConsultationType;
import org.factoriaf5.vcp.repository.AppointmentRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
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

    private AppointmentRepository appointmentRepository;
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        appointmentRepository = Mockito.mock(AppointmentRepository.class);
        appointmentService = new AppointmentService(appointmentRepository);
    }

    @Test
    void testFindAll() {

        List<Appointment> appointments = new ArrayList<>();

        Appointment appointment1 = new Appointment( 
            LocalDate.of(2024, 12, 01), 
            ConsultationType.EMERGENCY, 
            "Headage", 
            AppointmentStatus.PENDING);

        Appointment appointment2 = new Appointment( 
            LocalDate.of(2024, 12, 02), 
            ConsultationType.STANDARD, 
            "Headage", 
            AppointmentStatus.COMPLETED);

        appointments.add(appointment1);
        appointments.add(appointment2);

        // When
        when(repository.findAll()).thenReturn(appointments);
        List<Appointment> list = service.findAll();

        // Then
        assertThat(list.size(), equalTo(2));
        
        assertThat(list.get(0).getConsultation(), is(ConsultationType.EMERGENCY));
        assertThat(list.get(0).getAppointmentDate(), is(LocalDate.of(2024, 12, 01)));
        assertThat(list.get(0).getStatus(), is(AppointmentStatus.PENDING));

    }

       @Test
    void shouldFindAllAppointments() {
        // Arrange
        List<Appointment> appointments = Arrays.asList(
                new Appointment(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED),
                new Appointment(LocalDate.of(2024, 12, 20), ConsultationType.FOLLOW_UP, "Follow-up visit", AppointmentStatus.COMPLETED)
        );
        when(appointmentRepository.findAll()).thenReturn(appointments);

        // Act
        List<Appointment> result = appointmentService.findAll();

        // Assert
        assertThat(result).isEqualTo(appointments);
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    void shouldSaveNewAppointment() {
        // Arrange
        AppointmentDto appointmentDto = new AppointmentDto(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED);
        Appointment appointment = new Appointment(appointmentDto);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Appointment result = appointmentService.save(appointmentDto);

        // Assert
        assertThat(result).isEqualTo(appointment);
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    void shouldFindAppointmentById() {
        // Arrange
        Appointment appointment = new Appointment(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED);
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        // Act
        Optional<Appointment> result = appointmentService.findById(1L);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(appointment);
        verify(appointmentRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowWhenAppointmentNotFoundById() {
        // Arrange
        when(appointmentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> appointmentService.findById(1L).orElseThrow(() -> new RuntimeException("Appointment not found")))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Appointment not found");
        verify(appointmentRepository, times(1)).findById(1L);
    }

    @Test
    void shouldUpdateAppointment() {
        // Arrange
        Appointment existingAppointment = new Appointment(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED);
        AppointmentDto updatedDto = new AppointmentDto(LocalDate.of(2024, 12, 20), ConsultationType.FOLLOW_UP, "Updated reason", AppointmentStatus.COMPLETED);
        Appointment updatedAppointment = new Appointment(updatedDto);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(existingAppointment));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(updatedAppointment);

        // Act
        Appointment result = appointmentService.updateAppointment(1L, updatedDto);

        // Assert
        assertThat(result).isEqualTo(updatedAppointment);
        verify(appointmentRepository, times(1)).findById(1L);
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    void shouldDeleteAppointmentById() {
        // Arrange
        when(appointmentRepository.existsById(1L)).thenReturn(true);

        // Act
        appointmentService.deleteById(1L);

        // Assert
        verify(appointmentRepository, times(1)).existsById(1L);
        verify(appointmentRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowWhenDeletingNonExistingAppointment() {
        // Arrange
        when(appointmentRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThatThrownBy(() -> appointmentService.deleteById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Appointment not found with ID: 1");
        verify(appointmentRepository, times(1)).existsById(1L);
        verify(appointmentRepository, never()).deleteById(1L);
    }

    @Test
    void shouldPartiallyUpdateAppointment() {
        // Arrange
        Appointment existingAppointment = new Appointment(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Routine check-up", AppointmentStatus.SCHEDULED);
        Map<String, Object> updates = Map.of(
                "appointmentDate", "2024-12-20",
                "reason", "Updated reason",
                "status", "COMPLETED"
        );

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(existingAppointment));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(existingAppointment);

        // Act
        Appointment result = appointmentService.partialUpdateAppointment(1L, updates);

        // Assert
        assertThat(result.getAppointmentDate()).isEqualTo(LocalDate.of(2024, 12, 20));
        assertThat(result.getReason()).isEqualTo("Updated reason");
        assertThat(result.getStatus()).isEqualTo(AppointmentStatus.COMPLETED);
        verify(appointmentRepository, times(1)).findById(1L);
        verify(appointmentRepository, times(1)).save(existingAppointment);
    }
}
