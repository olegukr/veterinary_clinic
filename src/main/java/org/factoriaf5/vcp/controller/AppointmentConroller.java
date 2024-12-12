package org.factoriaf5.vcp.controller;

import java.util.List;

import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.services.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "${api-endpoint}/appointments")
public class AppointmentConroller {

    
    private AppointmentService service;


    public AppointmentConroller(AppointmentService service) {
        this.service = service;
    }


    @GetMapping("")
    public List<Appointment> index() {

        return service.findAll();
        
    }
    
}
