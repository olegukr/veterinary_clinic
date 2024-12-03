package org.factoriaf5.vcp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.model.AppointmentSatus;
import org.factoriaf5.vcp.model.ConsultationType;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    public List<Appointment> findAll() {

        List<Appointment> appointments = new ArrayList<>();

        Appointment appointment1 = new Appointment( 
            LocalDate.of(2024, 12, 01), 
            ConsultationType.EMERGENCY, 
            "Headage", 
            AppointmentSatus.PENDING);

        Appointment appointment2 = new Appointment( 
            LocalDate.of(2024, 12, 02), 
            ConsultationType.STANDART, 
            "Headage", 
            AppointmentSatus.COMPLETED);

        appointments.add(appointment1);
        appointments.add(appointment2);

        return appointments;
    }

}
