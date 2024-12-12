package org.factoriaf5.vcp.dto;
import java.time.LocalDate;

import org.factoriaf5.vcp.model.AppointmentStatus;
import org.factoriaf5.vcp.model.ConsultationType;

public record AppointmentDto(
    LocalDate appointmentDate, 
    ConsultationType consultation, 
    String reason, 
    AppointmentStatus status) {
    }
