package org.factoriaf5.vcp.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.factoriaf5.vcp.dto.AppointmentDto;
import org.factoriaf5.vcp.model.Appointment;
import org.factoriaf5.vcp.model.AppointmentStatus;
import org.factoriaf5.vcp.model.ConsultationType;
import org.factoriaf5.vcp.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    

    public AppointmentService(AppointmentRepository repository) {
        this.appointmentRepository = repository;
    }

    // Retrieve all appointments
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

     // Save a new appointment
     public Appointment save(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment(appointmentDto);
        return appointmentRepository.save(appointment);
    }

    // Get an appointment by ID
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Update an existing appointment
    public Appointment updateAppointment(Long id, AppointmentDto updatedAppointmentDto) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setAppointmentDate(updatedAppointmentDto.appointmentDate());
                    appointment.setConsultation(updatedAppointmentDto.consultation());
                    appointment.setReason(updatedAppointmentDto.reason());
                    appointment.setStatus(updatedAppointmentDto.status());
                    return appointmentRepository.save(appointment);
                })
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
    }

    
    // Delete an appointment by ID
    public void deleteById(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Appointment not found with ID: " + id);
        }
    }

    public Appointment partialUpdateAppointment(Long id, Map<String, Object> updates) {
    return appointmentRepository.findById(id)
            .map(appointment -> {
                updates.forEach((key, value) -> {
                    switch (key) {
                        case "appointmentDate":
                            appointment.setAppointmentDate(LocalDate.parse(value.toString()));
                            break;
                        case "consultation":
                            appointment.setConsultation(ConsultationType.valueOf(value.toString()));
                            break;
                        case "reason":
                            appointment.setReason(value.toString());
                            break;
                        case "status":
                            appointment.setStatus(AppointmentStatus.valueOf(value.toString()));
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid field: " + key);
                    }
                });
                return appointmentRepository.save(appointment);
            })
            .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
    }

}
