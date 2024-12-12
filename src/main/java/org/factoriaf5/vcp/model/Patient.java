package org.factoriaf5.vcp.model;

//import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

<<<<<<< HEAD
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
=======
@Entity
@Table(name="patients")
public class Patient {
    
    private static long idCounter = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private String name;
>>>>>>> VCP-25-add-attributes-to-User
    private int age;

    @Column(nullable = false)
    private String breed;

<<<<<<< HEAD
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType gender;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String imageUrl;

   
public Patient(Long idPatient, String name, User user, int age, String breed, GenderType gender, String imageUrl) {
    this.id = idCounter++;
=======
    @Enumerated(EnumType.STRING) 
    private GenderType gender;

    private String imageUrl;

public Patient() {
    }

public Patient(String name, Long idUser, int age, String breed, GenderType gender, String imageUrl) {
    this.id = generateId(); //idCounter++;
>>>>>>> VCP-25-add-attributes-to-User
    this.name = name;
    this.user = user;
    this.age = age;
    this.breed = breed;
    this.gender = gender;
    this.imageUrl = imageUrl;
}

private static synchronized long generateId() {
    return ++idCounter;
}

public User getUser() {
    return user;
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

<<<<<<< HEAD

public void User(User user) {
    this.user = user;
=======
public void setIdUser(Long idUser) {
    this.idUser = idUser;
>>>>>>> VCP-25-add-attributes-to-User
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