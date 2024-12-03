package org.factoriaf5.vcp.controller;

import java.util.List;

import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.services.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppointmentConroller {

    
    private AppointmentService service;


    public AppointmentConroller(AppointmentService service) {
        this.service = service;
    }


    @GetMapping("/appointments")
    public List<Appointment> index() {

        return service.findAll();
        
    }
    
}
