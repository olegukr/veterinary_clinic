package org.factoriaf5.vcp.model;

//import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="patients")
public class Patient {
    
    private static long idCounter = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private String name;
    private int age;
    private String breed;

    @Enumerated(EnumType.STRING) 
    private GenderType gender;

    private String imageUrl;

public Patient() {
    }

public Patient(String name, Long idUser, int age, String breed, GenderType gender, String imageUrl) {
    this.id = generateId(); //idCounter++;
    this.name = name;
    this.idUser = idUser;
    this.age = age;
    this.breed = breed;
    this.gender = gender;
    this.imageUrl = imageUrl;
}

private static synchronized long generateId() {
    return ++idCounter;
}

public Long getIdUser() {
    return idUser;
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
}