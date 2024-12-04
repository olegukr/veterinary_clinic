package org.factoriaf5.vcp.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {
    
    private static int idCounter = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    @Column(nullable = false) 
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType gender;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String imageUrl;

   
public Patient(Long idPatient, String name, User user, int age, String breed, GenderType gender, String imageUrl) {
    this.id = idCounter++;
    this.name = name;
    this.user = user;
    this.age = age;
    this.breed = breed;
    this.gender = gender;
    this.imageUrl = imageUrl;
}


public User getUser() {
    return user;
}


public static int getIdCounter() {
    return idCounter;
}


public long getId() {
    return id;
}


public String getName() {
    return name;
}


public int getAge() {
    return age;
}


public String getBreed() {
    return breed;
}


public GenderType getGender() {
    return gender;
}


public String getImageUrl() {
    return imageUrl;
}


public static void setIdCounter(int idCounter) {
    Patient.idCounter = idCounter;
}


public void User(User user) {
    this.user = user;
}

public void setName(String name) {
    this.name = name;
}


public void setAge(int age) {
    this.age = age;
}


public void setBreed(String breed) {
    this.breed = breed;
}


public void setGender(GenderType gender) {
    this.gender = gender;
}


public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
}

// Методи
    public void addPatient(Patient patient) {
        // ...
    }

    // public Patient getPatientById(Long id) {
    //     // ...
    //     return new Patient(); 
    //     }

    public void updatePatient(Patient patient) {
        // ...
    }

    public void deletePatient(Long id) {
        // ...
    }

    public String uploadPhoto(Long patientId, MultipartFile photo) {
        // завантаження фото
        return "URL photo"; 
    }



}