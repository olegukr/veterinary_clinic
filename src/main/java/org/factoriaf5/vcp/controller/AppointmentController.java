package org.factoriaf5.vcp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.factoriaf5.vcp.dto.AppointmentDto;
import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// @RequestMapping("${api-endpoint}/appointments")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    
    private final AppointmentService appointmentService;


    public AppointmentController(AppointmentService service) {
        this.appointmentService = service;
    }

    // Get all appointments
    @GetMapping
    public ResponseEntity<?> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.findAll();
            return ResponseEntity.ok(appointments); // Return 200 OK with the list of appointments
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving appointments: " + e.getMessage());
        }
    }

    // Get an appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.findById(id)
                .map(ResponseEntity::ok) // Return 200 OK with the appointment
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 Not Found
    }


    @PostMapping("")
    public ResponseEntity<?> store(@RequestBody AppointmentDto entity) {
        // Validate required fields
        if (entity.appointmentDate() == null || entity.appointmentDate().isEqual(LocalDate.of(1975, 1, 1))) {
            return ResponseEntity.badRequest().body("Invalid or missing appointment date.");
        }
        if (entity.reason() == null || entity.reason().isEmpty()) {
            return ResponseEntity.badRequest().body("Reason must be provided.");
        }
        if (entity.consultation() == null) {
            return ResponseEntity.badRequest().body("Consultation type must be specified.");
        }

        // Try to save the appointment
        try {
            Appointment appointment = appointmentService.save(entity);
            return ResponseEntity.status(201).body(appointment); // Return 201 Created with the saved appointment
        } catch (Exception e) {
            // Log the exception if necessary and return a proper error message
            return ResponseEntity.status(500).body("Error saving appointment: " + e.getMessage());
        }
    }

    // Update an appointment
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(
        @PathVariable Long id, 
        @RequestBody AppointmentDto updatedAppointmentDto) {

        // Validate required fields
        if (updatedAppointmentDto.appointmentDate() == null 
        || updatedAppointmentDto.appointmentDate().isEqual(LocalDate.of(1975, 1, 1))) {
            return ResponseEntity.badRequest().body("Invalid or missing appointment date.");
        }

        if (updatedAppointmentDto.reason() == null 
        || updatedAppointmentDto.reason().isEmpty()) {
            return ResponseEntity.badRequest().body("Reason must be provided.");
        }
        
        if (updatedAppointmentDto.consultation() == null) {
            return ResponseEntity.badRequest().body("Consultation type must be specified.");
        }

        try {
            Appointment updatedAppointment = appointmentService.updateAppointment(id, updatedAppointmentDto);
            return ResponseEntity.ok(updatedAppointment); // Return 200 OK with the updated appointment
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage()); // 404 Not Found if the appointment does not exist
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating appointment: " + e.getMessage());
        }
    }

    // Delete an appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteById(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Appointment not found with ID: " + id); // 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting appointment: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partiallyUpdateAppointment(
            @PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            Appointment updatedAppointment = appointmentService.partialUpdateAppointment(id, updates);
            return ResponseEntity.ok(updatedAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("error", "Appointment not found", "id", id.toString()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error updating appointment", "message", e.getMessage()));
        }
    }


}
