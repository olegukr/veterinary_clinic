package org.factoriaf5.vcp.controller;

import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.model.Treatment;
import org.factoriaf5.vcp.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    @Autowired
    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) {
        Optional<Treatment> treatment = treatmentService.getTreatmentById(id);
        return treatment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Treatment> createTreatment(@RequestBody Treatment treatment) {
        Treatment createdTreatment = treatmentService.createTreatment(treatment);
        return ResponseEntity.ok(createdTreatment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatment) {
        Treatment updatedTreatment = treatmentService.updateTreatment(id, treatment);
        return updatedTreatment != null ? ResponseEntity.ok(updatedTreatment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTreatment(@PathVariable Long id) {
        try {
            treatmentService.deleteTreatment(id);
            return ResponseEntity.ok("Tratamiento eliminado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<Treatment>> getPatientByIdUser(@PathVariable Long patientId) {
        List<Treatment> treatments = treatmentService.getTreatmentByPatientID(patientId);
        return ResponseEntity.ok(treatments);
    }
}
