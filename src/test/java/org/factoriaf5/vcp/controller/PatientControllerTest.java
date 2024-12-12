package org.factoriaf5.vcp.controller;

import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.model.User;
import org.factoriaf5.vcp.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePatient() {
        Patient patient = new Patient();
        when(patientService.createPatient(patient)).thenReturn(patient);

        ResponseEntity<Patient> response = patientController.createPatient(patient);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).createPatient(patient);
    }

    @Test
    void testGetPatientById_Found() {
        Long patientId = 1L;
        Patient patient = new Patient();
        when(patientService.getPatientById(patientId)).thenReturn(Optional.of(patient));

        ResponseEntity<Patient> response = patientController.getPatientById(patientId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).getPatientById(patientId);
    }

    @Test
    void testGetPatientById_NotFound() {
        Long patientId = 1L;
        when(patientService.getPatientById(patientId)).thenReturn(Optional.empty());

        ResponseEntity<Patient> response = patientController.getPatientById(patientId);

        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(patientService, times(1)).getPatientById(patientId);
    }

    @Test
    void testGetPatientByUser_Found() {
        User user = new User();
        Patient patient = new Patient();
        when(patientService.getPatientByIdUser(user)).thenReturn(Optional.of(patient));

        ResponseEntity<Patient> response = patientController.getPatientByIdUser(user);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(patient, response.getBody());
        verify(patientService, times(1)).getPatientByIdUser(user);
    }

    @Test
    void testGetPatientByUser_NotFound() {
        User user = new User();
        when(patientService.getPatientByIdUser(user)).thenReturn(Optional.empty());

        ResponseEntity<Patient> response = patientController.getPatientByIdUser(user);

        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(patientService, times(1)).getPatientByIdUser(user);
    }

    @Test
    void testGetAllPatients() {
        Patient patient = new Patient();
        List<Patient> patients = Collections.singletonList(patient);
        when(patientService.getAllPatients()).thenReturn(patients);

        ResponseEntity<List<Patient>> response = patientController.getAllPatients();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(patients, response.getBody());
        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    void testUpdatePatient_Success() {
        Long patientId = 1L;
        Patient patient = new Patient();
        Patient updatedPatient = new Patient();
        when(patientService.updatePatient(patientId, patient)).thenReturn(updatedPatient);

        ResponseEntity<Patient> response = patientController.updatePatient(patientId, patient);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(updatedPatient, response.getBody());
        verify(patientService, times(1)).updatePatient(patientId, patient);
    }

    @Test
    void testUpdatePatient_NotFound() {
        Long patientId = 1L;
        Patient patient = new Patient();
        when(patientService.updatePatient(patientId, patient)).thenThrow(new RuntimeException("Patient not found"));

        ResponseEntity<Patient> response = patientController.updatePatient(patientId, patient);

        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(patientService, times(1)).updatePatient(patientId, patient);
    }

    @Test
    void testDeletePatient() {
        Long patientId = 1L;

        ResponseEntity<Void> response = patientController.deletePatient(patientId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        verify(patientService, times(1)).deletePatient(patientId);
    }
}
