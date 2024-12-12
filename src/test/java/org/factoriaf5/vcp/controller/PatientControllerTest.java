package org.factoriaf5.vcp.controller;

import org.factoriaf5.vcp.model.GenderType;
import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.services.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    void shouldCreatePatient() throws Exception {
        Patient patient = new Patient("Max", 1L, 5, "Golden Retriever", GenderType.M, "http://example.com/max.jpg");
        Mockito.when(patientService.createPatient(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Max",
                                    "idUser": 1,
                                    "age": 5,
                                    "breed": "Golden Retriever",
                                    "gender": "M",
                                    "imageUrl": "http://example.com/max.jpg"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Max"))
                .andExpect(jsonPath("$.age").value(5))
                .andExpect(jsonPath("$.breed").value("Golden Retriever"));
    }

    @Test
    void shouldGetPatientById() throws Exception {
        Patient patient = new Patient("Max", 1L, 5, "Golden Retriever", GenderType.M, "http://example.com/max.jpg");
        Mockito.when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/api/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Max"))
                .andExpect(jsonPath("$.breed").value("Golden Retriever"));
    }

    @Test
    void shouldReturnNotFoundWhenPatientDoesNotExist() throws Exception {
        Mockito.when(patientService.getPatientById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/patients/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllPatients() throws Exception {
        Patient patient1 = new Patient("Max", 1L, 5, "Golden Retriever", GenderType.M, "http://example.com/max.jpg");
        Patient patient2 = new Patient("Daisy", 2L, 3, "Bulldog", GenderType.W, "http://example.com/daisy.jpg");
        Mockito.when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient1, patient2));

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name").value("Max"))
                .andExpect(jsonPath("$[1].name").value("Daisy"));
    }

    @Test
    void shouldUpdatePatient() throws Exception {
        Patient updatedPatient = new Patient("Max", 1L, 6, "Golden Retriever", GenderType.M, "http://example.com/max-updated.jpg");
        Mockito.when(patientService.updatePatient(eq(1L), any(Patient.class))).thenReturn(updatedPatient);

        mockMvc.perform(put("/api/patients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Max",
                                    "idUser": 1,
                                    "age": 6,
                                    "breed": "Golden Retriever",
                                    "gender": "M",
                                    "imageUrl": "http://example.com/max-updated.jpg"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(6))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/max-updated.jpg"));
    }

    @Test
    void shouldDeletePatient() throws Exception {
        mockMvc.perform(delete("/api/patients/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(patientService, Mockito.times(1)).deletePatient(1L);
    }
}