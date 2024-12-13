package org.factoriaf5.vcp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TreatmentTest {

    private Treatment treatment;
    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setName("Buddy");
        patient.setAge(5);
        patient.setBreed("Golden Retriever");
        patient.setGender(GenderType.M);
        patient.setImageUrl("https://example.com/images/buddy.jpg");

        treatment = new Treatment(
                "Vaccination",
                "Administered rabies vaccine",
                LocalDate.of(2024, 12, 12),
                patient
        );
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("Vaccination", treatment.getTreatmentType());
        assertEquals("Administered rabies vaccine", treatment.getDescription());
        assertEquals(LocalDate.of(2024, 12, 12), treatment.getTreatmentDate());
        assertEquals(patient, treatment.getPatient());

        treatment.setTreatmentType("Surgery");
        treatment.setDescription("Removed tumor");
        treatment.setTreatmentDate(LocalDate.of(2024, 12, 13));
        Patient newPatient = new Patient();
        newPatient.setId(2L);
        treatment.setPatient(newPatient);

        assertEquals("Surgery", treatment.getTreatmentType());
        assertEquals("Removed tumor", treatment.getDescription());
        assertEquals(LocalDate.of(2024, 12, 13), treatment.getTreatmentDate());
        assertEquals(newPatient, treatment.getPatient());
    }

    @Test
    void testConstructor() {
        Treatment newTreatment = new Treatment(
                "Checkup",
                "General health check",
                LocalDate.of(2024, 12, 14),
                patient
        );

        assertEquals("Checkup", newTreatment.getTreatmentType());
        assertEquals("General health check", newTreatment.getDescription());
        assertEquals(LocalDate.of(2024, 12, 14), newTreatment.getTreatmentDate());
        assertEquals(patient, newTreatment.getPatient());
    }

    @Test
    void testToString() {
        String expected = "Treatment{" +
                "id=null, treatmentType='Vaccination', description='Administered rabies vaccine', " +
                "treatmentDate=2024-12-12, patient=" + patient +
                '}';
        assertEquals(expected, treatment.toString());
    }

    @Test
    void testPatientAssociation() {
        assertEquals(1L, treatment.getPatient().getId());
        assertEquals("Buddy", treatment.getPatient().getName());
        assertEquals("Golden Retriever", treatment.getPatient().getBreed());
    }
}
