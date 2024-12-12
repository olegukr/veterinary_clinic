package org.factoriaf5.vcp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String treatmentType;
    private String description;
    private LocalDate treatmentDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public Treatment() {
    }

    public Treatment(String treatmentType, String description, LocalDate treatmentDate, Patient patient) {
        this.treatmentType = treatmentType;
        this.description = description;
        this.treatmentDate = treatmentDate;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "id=" + id +
                ", treatmentType='" + treatmentType + '\'' +
                ", description='" + description + '\'' +
                ", treatmentDate=" + treatmentDate +
                ", patient=" + patient +
                '}';
    }
}
