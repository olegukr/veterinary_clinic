package org.factoriaf5.vcp.services;

import java.util.List;

import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private AppointmentRepository repository;
    

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }


    public List<Appointment> findAll() {


        // List<Appointment> appointments = new ArrayList<>();

        // Appointment appointment1 = new Appointment( 
        //     LocalDate.of(2024, 12, 01), 
        //     ConsultationType.EMERGENCY, 
        //     "Headage", 
        //     AppointmentSatus.PENDING);

        // Appointment appointment2 = new Appointment( 
        //     LocalDate.of(2024, 12, 02), 
        //     ConsultationType.STANDARD, 
        //     "Headage", 
        //     AppointmentSatus.COMPLETED);

        // appointments.add(appointment1);
        // appointments.add(appointment2);

        return repository.findAll();
    }

}
