package org.factoriaf5.vcp.services;

import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.model.Treatment;
import org.factoriaf5.vcp.repository.TreatmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TreatmentServiceTest {

    @Mock
    private TreatmentRepository treatmentRepository;

    @InjectMocks
    private TreatmentService treatmentService;

    private Treatment treatment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Patient patient = new Patient();
        patient.setId(1L);
        treatment = new Treatment("Vacuna", "Vacunación anual", LocalDate.now(), patient);
        treatment.setId(1L);
    }

    @Test
    void testGetAllTreatments() {
        List<Treatment> treatments = Arrays.asList(treatment);
        when(treatmentRepository.findAll()).thenReturn(treatments);

        List<Treatment> result = treatmentService.getAllTreatments();

        assertEquals(1, result.size());
        assertEquals(treatment, result.get(0));
        verify(treatmentRepository, times(1)).findAll();
    }

    @Test
    void testGetTreatmentById() {
        when(treatmentRepository.findById(1L)).thenReturn(Optional.of(treatment));

        Optional<Treatment> result = treatmentService.getTreatmentById(1L);

        assertTrue(result.isPresent());
        assertEquals(treatment, result.get());
        verify(treatmentRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateTreatment() {
        when(treatmentRepository.save(treatment)).thenReturn(treatment);

        Treatment result = treatmentService.createTreatment(treatment);

        assertNotNull(result);
        assertEquals(treatment, result);
        verify(treatmentRepository, times(1)).save(treatment);
    }

    @Test
    void testUpdateTreatment() {
        when(treatmentRepository.existsById(1L)).thenReturn(true);
        when(treatmentRepository.save(treatment)).thenReturn(treatment);

        Treatment result = treatmentService.updateTreatment(1L, treatment);

        assertNotNull(result);
        assertEquals(treatment, result);
        verify(treatmentRepository, times(1)).existsById(1L);
        verify(treatmentRepository, times(1)).save(treatment);
    }

    @Test
    void testUpdateTreatmentNotFound() {
        when(treatmentRepository.existsById(1L)).thenReturn(false);

        Treatment result = treatmentService.updateTreatment(1L, treatment);

        assertNull(result);
        verify(treatmentRepository, times(1)).existsById(1L);
        verify(treatmentRepository, times(0)).save(treatment);
    }

    @Test
    void testDeleteTreatment() {
        doNothing().when(treatmentRepository).deleteById(1L);

        treatmentService.deleteTreatment(1L);

        verify(treatmentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetTreatmentByPatientID() {
        List<Treatment> treatments = Arrays.asList(treatment);
        when(treatmentRepository.findByPatientId(1L)).thenReturn(treatments);

        List<Treatment> result = treatmentService.getTreatmentByPatientID(1L);

        assertEquals(1, result.size());
        assertEquals(treatment, result.get(0));
        verify(treatmentRepository, times(1)).findByPatientId(1L);
    }
}

