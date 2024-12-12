package org.factoriaf5.vcp.services;

import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePatient() {
        Patient patient = new Patient();
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient createdPatient = patientService.createPatient(patient);

        assertNotNull(createdPatient);
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testGetPatientById() {
        Patient patient = new Patient();
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Optional<Patient> foundPatient = patientService.getPatientById(1L);

        assertTrue(foundPatient.isPresent());
        assertEquals(patient, foundPatient.get());
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllPatients() {
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.getAllPatients();

        assertEquals(2, result.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testUpdatePatient() {
        Patient existingPatient = new Patient();
        existingPatient.setName("Old Name");
        Patient updatedPatient = new Patient();
        updatedPatient.setName("New Name");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(existingPatient)).thenReturn(existingPatient);

        Patient result = patientService.updatePatient(1L, updatedPatient);

        assertEquals("New Name", result.getName());
        verify(patientRepository, times(1)).findById(1L);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    void testDeletePatient() {
        doNothing().when(patientRepository).deleteById(1L);

        patientService.deletePatient(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }
}
