package org.factoriaf5.vcp.model;

import java.time.LocalDate;

public class Appointment {

    private static long idCounter = 0;

    private final long id;
    private LocalDate appointmentDate;
    private ConsultationType consultation;
    private String reason;
    private AppointmentSatus status;

    public Appointment(LocalDate appointmentDate, ConsultationType consltation, String reason, AppointmentSatus status) {
        this.id = generateId();
        this.appointmentDate = appointmentDate;
        this.consultation = consltation;
        this.reason = reason;
        this.status = status;
    }

    private static synchronized long generateId() {
        return ++idCounter;
    }

}
