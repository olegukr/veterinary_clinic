package org.factoriaf5.vcp.repository;
  
//import org.apache.catalina.User;
import org.factoriaf5.vcp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Optional<Patient> findByUser(User User);
    List<Patient> findByUserId(Long userId);
    //Optional<Patient> findByUserId(Long userId);
}
