package org.factoriaf5.vcp.repository;

import java.util.List;

import org.factoriaf5.vcp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
}