package org.factoriaf5.vcp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.model.AppointmentSatus;
import org.factoriaf5.vcp.model.ConsultationType;
import org.factoriaf5.vcp.repository.AppointmentRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @InjectMocks
    AppointmentService service;

    @Mock
    AppointmentRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new AppointmentService(repository);
    }

    @Test
    void testFindAll() {

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
        when(repository.findAll()).thenReturn(appointments);
        List<Appointment> list = service.findAll();

        // Then
        assertThat(list.size(), equalTo(2));
        
        assertThat(list.get(0).getConsultation(), is(ConsultationType.EMERGENCY));
        assertThat(list.get(0).getAppointmentDate(), is(LocalDate.of(2024, 12, 01)));
        assertThat(list.get(0).getStatus(), is(AppointmentSatus.PENDING));






    }
}
