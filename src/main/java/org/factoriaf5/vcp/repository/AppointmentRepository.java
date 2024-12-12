package org.factoriaf5.vcp.repository;

import org.factoriaf5.vcp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
