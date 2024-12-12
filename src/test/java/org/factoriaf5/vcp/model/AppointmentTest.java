package org.factoriaf5.vcp.model;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import org.factoriaf5.vcp.dto.AppointmentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AppointmentTest {

    @BeforeEach
    void resetIdCounter() {
        Appointment.setIdCounter(0);
    }

    @Test
    void shouldCreateAppointmentUsingConstructor() {
        // Arrange
        LocalDate date = LocalDate.of(2024, 12, 15);
        ConsultationType consultation = ConsultationType.STANDARD;
        String reason = "Routine check-up";
        AppointmentStatus status = AppointmentStatus.SCHEDULED;

        // Act
        Appointment appointment = new Appointment(date, consultation, reason, status);

        // Assert
        assertThat(appointment.getId()).isEqualTo(1); // ID is auto-incremented
        assertThat(appointment.getAppointmentDate()).isEqualTo(date);
        assertThat(appointment.getConsultation()).isEqualTo(consultation);
        assertThat(appointment.getReason()).isEqualTo(reason);
        assertThat(appointment.getStatus()).isEqualTo(status);
    }

    @Test
    void shouldCreateAppointmentUsingDto() {
        // Arrange
        AppointmentDto dto = new AppointmentDto(
                LocalDate.of(2024, 12, 15),
                ConsultationType.STANDARD,
                "Routine check-up",
                AppointmentStatus.SCHEDULED
        );

        // Act
        Appointment appointment = new Appointment(dto);

        // Assert
        assertThat(appointment.getAppointmentDate()).isEqualTo(dto.appointmentDate());
        assertThat(appointment.getConsultation()).isEqualTo(dto.consultation());
        assertThat(appointment.getReason()).isEqualTo(dto.reason());
        assertThat(appointment.getStatus()).isEqualTo(dto.status());
    }

    @Test
    void shouldIncrementIdCounter() {
        // Act
        Appointment appointment1 = new Appointment(LocalDate.now(), ConsultationType.STANDARD, "Reason 1", AppointmentStatus.PENDING);
        Appointment appointment2 = new Appointment(LocalDate.now(), ConsultationType.STANDARD, "Reason 2", AppointmentStatus.COMPLETED);

        // Assert
        assertThat(appointment1.getId()).isEqualTo(1);
        assertThat(appointment2.getId()).isEqualTo(2);
    }

    @Test
    void shouldSetAndGetProperties() {
        // Arrange
        Appointment appointment = new Appointment();
        LocalDate date = LocalDate.of(2024, 12, 20);
        ConsultationType consultation = ConsultationType.FOLLOW_UP;
        String reason = "Updated reason";
        AppointmentStatus status = AppointmentStatus.COMPLETED;

        // Act
        appointment.setAppointmentDate(date);
        appointment.setConsultation(consultation);
        appointment.setReason(reason);
        appointment.setStatus(status);

        // Assert
        assertThat(appointment.getAppointmentDate()).isEqualTo(date);
        assertThat(appointment.getConsultation()).isEqualTo(consultation);
        assertThat(appointment.getReason()).isEqualTo(reason);
        assertThat(appointment.getStatus()).isEqualTo(status);
    }

    @Test
    void shouldResetIdCounter() {
        // Arrange
        Appointment.setIdCounter(100);

        // Act
        Appointment appointment = new Appointment(LocalDate.of(2024, 12, 15), ConsultationType.STANDARD, "Test", AppointmentStatus.SCHEDULED);

        // Assert
        assertThat(Appointment.getIdCounter()).isEqualTo(101);
        assertThat(appointment.getId()).isEqualTo(101);
    }

    @Test
    void shouldHandleMultipleAppointments() {
        // Act
        Appointment appointment1 = new Appointment(LocalDate.now(), ConsultationType.STANDARD, "Reason 1", AppointmentStatus.PENDING);
        Appointment appointment2 = new Appointment(LocalDate.now(), ConsultationType.EMERGENCY, "Reason 2", AppointmentStatus.CANCELLED);

        // Assert
        assertThat(appointment1.getId()).isEqualTo(1);
        assertThat(appointment2.getId()).isEqualTo(2);
    }
}