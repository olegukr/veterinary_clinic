package org.factoriaf5.vcp.controller;

import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.model.Treatment;
import org.factoriaf5.vcp.services.TreatmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TreatmentController.class)
public class TreatmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TreatmentService treatmentService;

    private Treatment treatment;

    @BeforeEach
    void setUp() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Buddy");

        treatment = new Treatment("Vaccination", "Routine vaccination", LocalDate.now(), patient);
        treatment.setId(1L);
    }

    @Test
    void testGetAllTreatments() throws Exception {
        List<Treatment> treatments = Arrays.asList(treatment);
        Mockito.when(treatmentService.getAllTreatments()).thenReturn(treatments);

        mockMvc.perform(get("/api/treatments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].treatmentType").value("Vaccination"));
    }

    @Test
    void testGetTreatmentById() throws Exception {
        Mockito.when(treatmentService.getTreatmentById(1L)).thenReturn(Optional.of(treatment));

        mockMvc.perform(get("/api/treatments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.treatmentType").value("Vaccination"));
    }

    @Test
    void testCreateTreatment() throws Exception {
        Mockito.when(treatmentService.createTreatment(any(Treatment.class))).thenReturn(treatment);

        String treatmentJson = "{" +
                "\"treatmentType\": \"Vaccination\"," +
                "\"description\": \"Routine vaccination\"," +
                "\"treatmentDate\": \"2024-12-12\"," +
                "\"patient\": {\"id\": 1}" +
                "}";

        mockMvc.perform(post("/api/treatments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(treatmentJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.treatmentType").value("Vaccination"));
    }

    @Test
    void testUpdateTreatment() throws Exception {
        Mockito.when(treatmentService.updateTreatment(eq(1L), any(Treatment.class))).thenReturn(treatment);

        String treatmentJson = "{" +
                "\"treatmentType\": \"Updated Vaccination\"," +
                "\"description\": \"Updated description\"," +
                "\"treatmentDate\": \"2024-12-12\"," +
                "\"patient\": {\"id\": 1}" +
                "}";

        mockMvc.perform(put("/api/treatments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(treatmentJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.treatmentType").value("Vaccination"));
    }

    @Test
    void testDeleteTreatment() throws Exception {
        Mockito.doNothing().when(treatmentService).deleteTreatment(1L);

        mockMvc.perform(delete("/api/treatments/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tratamiento eliminado exitosamente"));
    }

    @Test
    void testGetTreatmentsByPatientId() throws Exception {
        Mockito.when(treatmentService.getTreatmentByPatientID(1L)).thenReturn(Arrays.asList(treatment));

        mockMvc.perform(get("/api/treatments/by-patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].treatmentType").value("Vaccination"));
    }
}

