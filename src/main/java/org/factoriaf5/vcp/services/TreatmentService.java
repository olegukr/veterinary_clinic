package org.factoriaf5.vcp.services;

import org.factoriaf5.vcp.model.Treatment;
import org.factoriaf5.vcp.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public Optional<Treatment> getTreatmentById(Long id) {
        return treatmentRepository.findById(id);
    }

    public Treatment createTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public Treatment updateTreatment(Long id, Treatment treatment) {
        if (treatmentRepository.existsById(id)) {
            treatment.setId(id);
            return treatmentRepository.save(treatment);
        } else {
            return null; // or throw an exception
        }
    }

    public void deleteTreatment(Long id) {
        treatmentRepository.deleteById(id);
    }
}
