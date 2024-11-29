package org.factoriaf5.vcp.model;

import org.springframework.web.multipart.MultipartFile;

public class Patient {
    
    private static int idCounter = 1;

    private final long id;
    private String name;
    private Long idUser;
    private int age;
    private String breed;
    private GenderType gender;
    private String imageUrl;

   
public Patient(Long idPatient, String name, Long idUser, int age, String breed, GenderType gender, String imageUrl) {
    //Long idUser - ?
    this.id = idCounter++;
    this.name = name;
    this.idUser = idUser;
    this.age = age;
    this.breed = breed;
    this.gender = gender;
    this.imageUrl = imageUrl;
}


public Long getIdUser() {
    return idUser;
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


public void setIdUser(Long idUser) {
    this.idUser = idUser;
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