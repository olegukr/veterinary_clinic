package org.factoriaf5.vcp.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.model.AppointmentSatus;
import org.factoriaf5.vcp.model.ConsultationType;
import org.factoriaf5.vcp.services.AppointmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AppointmentConroller.class)
@AutoConfigureMockMvc(addFilters=false) //disable security
public class AppointmentConrollerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AppointmentService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("Should return list of appointments")
    void testIndex() throws Exception {

        // Given
        // 1. Service
        // 2. Mock Http
        // 2. List appointments

        List<Appointment> appointments = new ArrayList<>();

        Appointment appointment1 = new Appointment( 
            LocalDate.of(2024, 12, 01), 
            ConsultationType.EMERGENCY, 
            "Headage", 
            AppointmentSatus.PENDING);

        Appointment appointment2 = new Appointment( 
            LocalDate.of(2024, 12, 02), 
            ConsultationType.STANDARD, 
            "Headage", 
            AppointmentSatus.COMPLETED);

        appointments.add(appointment1);
        appointments.add(appointment2);

        // When
        when(service.findAll()).thenReturn(appointments);
        
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/appointments")
        .accept(MediaType.APPLICATION_JSON)
        .content("application/json"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();
        
        // then
        assertThat(response.getStatus(), is(200));

        



        

    }
}
