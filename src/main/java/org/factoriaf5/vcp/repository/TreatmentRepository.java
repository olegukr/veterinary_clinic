package org.factoriaf5.vcp.repository;

import java.util.List;

import org.factoriaf5.vcp.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByPatientId(Long patientId);
}
