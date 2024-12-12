package org.factoriaf5.vcp.model;

import java.time.LocalDate;

import org.factoriaf5.vcp.dto.AppointmentDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {

    private static long idCounter = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate appointmentDate;

    @Enumerated(EnumType.STRING) // Store enum as string in the database
    private ConsultationType consultation;

    private String reason;

    @Enumerated(EnumType.STRING) // Store enum as string in the database
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false) // Foreign key to the Patient table
    private Patient patient;

    public Appointment() {
    }

    public Appointment(LocalDate appointmentDate, ConsultationType consultation, String reason, AppointmentStatus status, Patient patient) {
        this.id = generateId();
        this.appointmentDate = appointmentDate;
        this.consultation = consultation;
        this.reason = reason;
        this.status = status;
        this.patient = patient;
    }

    public Appointment(AppointmentDto appointmentDto) {
        this(appointmentDto.appointmentDate(), 
             appointmentDto.consultation(), 
             appointmentDto.reason(), 
             appointmentDto.status(),
             appointmentDto.patient());
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

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
