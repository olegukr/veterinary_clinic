package org.factoriaf5.vcp.services;

import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.model.GenderType;
import org.factoriaf5.vcp.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    private PatientRepository patientRepository;
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        patientRepository = mock(PatientRepository.class);
        patientService = new PatientService(patientRepository);
    }

    @Test
    void testCreatePatient() {
        Patient patient = new Patient("Buddy", 1L, 5, "Golden Retriever", GenderType.M, "https://example.com/buddy.jpg");
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient createdPatient = patientService.createPatient(patient);

        assertThat(createdPatient).isEqualTo(patient);
        verify(patientRepository).save(patient);
    }

    @Test
    void testGetPatientById() {
        Long id = 1L;
        Patient patient = new Patient("Lucy", 2L, 3, "Chihuahua", GenderType.W, "https://example.com/lucy.jpg");
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        Optional<Patient> foundPatient = patientService.getPatientById(id);

        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get()).isEqualTo(patient);
        verify(patientRepository).findById(id);
    }

    @Test
    void testGetPatientByIdUser() {
        Long idUser = 2L;
        Patient patient = new Patient("Lucy", idUser, 3, "Chihuahua", GenderType.W, "https://example.com/lucy.jpg");
        when(patientRepository.findByIdUser(idUser)).thenReturn(Optional.of(patient));

        Optional<Patient> foundPatient = patientService.getPatientByIdUser(idUser);

        assertThat(foundPatient).isPresent();
        assertThat(foundPatient.get()).isEqualTo(patient);
        verify(patientRepository).findByIdUser(idUser);
    }

    @Test
    void testGetAllPatients() {

        Patient patient1 = new Patient("Buddy", 1L, 5, "Golden Retriever", GenderType.M, "https://example.com/buddy.jpg");
        Patient patient2 = new Patient("Lucy", 2L, 3, "Chihuahua", GenderType.W, "https://example.com/lucy.jpg");
        List<Patient> patients = Arrays.asList(patient1, patient2);
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> allPatients = patientService.getAllPatients();

        assertThat(allPatients).hasSize(2).containsExactly(patient1, patient2);
        verify(patientRepository).findAll();
    }

    @Test
    void testUpdatePatient() {

        Long id = 1L;
        Patient existingPatient = new Patient("Buddy", 1L, 5, "Golden Retriever", GenderType.M, "https://example.com/buddy.jpg");
        Patient updatedPatient = new Patient("Max", 1L, 6, "Poodle", GenderType.M, "https://example.com/max.jpg");
        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);

        Patient result = patientService.updatePatient(id, updatedPatient);

        assertThat(result.getName()).isEqualTo("Max");
        assertThat(result.getAge()).isEqualTo(6);
        assertThat(result.getBreed()).isEqualTo("Poodle");
        assertThat(result.getImageUrl()).isEqualTo("https://example.com/max.jpg");
        verify(patientRepository).findById(id);
        verify(patientRepository).save(existingPatient);
    }

    @Test
    void testDeletePatient() {
        Long id = 1L;
        patientService.deletePatient(id);
        verify(patientRepository).deleteById(id);
    }
}
