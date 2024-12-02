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

    public static long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(long idCounter) {
        Appointment.idCounter = idCounter;
    }

    public long getId() {
        return id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public ConsultationType getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationType consultation) {
        this.consultation = consultation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AppointmentSatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentSatus status) {
        this.status = status;
    }

}
