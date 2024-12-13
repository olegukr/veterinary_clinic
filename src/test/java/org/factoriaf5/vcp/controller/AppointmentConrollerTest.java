package org.factoriaf5.vcp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.factoriaf5.vcp.dto.AppointmentDto;
import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.model.AppointmentStatus;
import org.factoriaf5.vcp.model.ConsultationType;
import org.factoriaf5.vcp.model.GenderType;
import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.model.User;
import org.factoriaf5.vcp.model.UserType;
import org.factoriaf5.vcp.services.AppointmentService;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AppointmentController.class)
@AutoConfigureMockMvc(addFilters = false) // disable security
public class AppointmentConrollerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AppointmentService service;

    @Autowired
    ObjectMapper mapper;

    private Patient patient;

    @BeforeEach
    void setUp() {
        User user = new User("testUser", "password", UserType.USER, "1234567890");
        patient = new Patient("Buddy", user, 4, "Golden Retriever", GenderType.M, "https://example.com/images/buddy.jpg");
    }

    @Test
    @DisplayName("Should return list of appointments")
    void testIndex() throws Exception {
        // Given
        List<Appointment> appointments = new ArrayList<>();

        appointments.add(new Appointment(
                LocalDate.of(2024, 12, 1),
                ConsultationType.EMERGENCY,
                "Headache",
                AppointmentStatus.PENDING,
                patient
        ));

        appointments.add(new Appointment(
                LocalDate.of(2024, 12, 2),
                ConsultationType.STANDARD,
                "Routine health check-up",
                AppointmentStatus.COMPLETED,
                patient
        ));

        when(service.findAll()).thenReturn(appointments);

        // Act
        MockHttpServletResponse response = mockMvc.perform(get("/appointments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // Assert
        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), containsString("Routine health check-up"));
        assertThat(response.getContentAsString(), containsString("Headache"));
        assertThat(response.getContentAsString(), containsString(ConsultationType.EMERGENCY.toString()));
        assertThat(response.getContentAsString(), containsString(AppointmentStatus.PENDING.toString()));
    }

    @Test
    @DisplayName("Should create a new appointment")
    void testCreateAppointment() throws Exception {
        // Arrange
        AppointmentDto appointmentDto = new AppointmentDto(
                LocalDate.of(2024, 12, 1),
                ConsultationType.EMERGENCY,
                "Headache",
                AppointmentStatus.PENDING,
                patient
        );

        Appointment savedAppointment = new Appointment(
                LocalDate.of(2024, 12, 1),
                ConsultationType.EMERGENCY,
                "Headache",
                AppointmentStatus.PENDING,
                patient
        );

        when(service.save(appointmentDto)).thenReturn(savedAppointment);

        // Act
        MockHttpServletResponse response = mockMvc.perform(post("/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(appointmentDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse(); 

        // Assert
        assertThat(response.getStatus(), is(201));
        assertThat(response.getContentAsString(), containsString("Headache"));
        assertThat(response.getContentAsString(), containsString(ConsultationType.EMERGENCY.toString()));
        assertThat(response.getContentAsString(), containsString(AppointmentStatus.PENDING.toString()));
        assertThat(response.getContentAsString(), containsString(patient.getName()));
    }

    @Test
    @DisplayName("Should return appointment by ID")
    void testGetAppointmentById() throws Exception {
        // Arrange
        Appointment appointment = new Appointment(
                LocalDate.of(2024, 12, 1),
                ConsultationType.EMERGENCY,
                "Headache",
                AppointmentStatus.PENDING,
                patient
        );

        when(service.findById(1L)).thenReturn(Optional.of(appointment));

        // Act
        MockHttpServletResponse response = mockMvc.perform(get("/appointments/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // Assert
        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), containsString("Headache"));
        assertThat(response.getContentAsString(), containsString(ConsultationType.EMERGENCY.toString()));
        assertThat(response.getContentAsString(), containsString(AppointmentStatus.PENDING.toString()));
        assertThat(response.getContentAsString(), containsString(patient.getName()));
    }

    @Test
    @DisplayName("Should delete an appointment by ID")
    void testDeleteAppointment() throws Exception {
        mockMvc.perform(delete("/appointments/1"))
                .andExpect(status().isNoContent());
    }
}
