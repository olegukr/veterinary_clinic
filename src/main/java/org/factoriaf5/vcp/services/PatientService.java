package org.factoriaf5.vcp.services;

import org.apache.catalina.User;
import org.factoriaf5.vcp.model.Patient;
import org.factoriaf5.vcp.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getPatientByIdUser(Long userId) {
        return patientRepository.findByUserId(userId);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(updatedPatient.getName());
                    patient.setAge(updatedPatient.getAge());
                    patient.setBreed(updatedPatient.getBreed());
                    patient.setGender(updatedPatient.getGender());
                    patient.setImageUrl(updatedPatient.getImageUrl());
                    return patientRepository.save(patient);
                })
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}