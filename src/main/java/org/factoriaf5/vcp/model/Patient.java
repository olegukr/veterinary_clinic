package org.factoriaf5.vcp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="patients")
public class Patient {
    
    private static long idCounter = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) 
    private GenderType gender;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Treatment> treatments;
    
    public Patient() {
        }

    public Patient(String name, User user, int age, String breed, GenderType gender, String imageUrl) {
        this.id = generateId(); //idCounter++;
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

    public void setUser(User user) {
        this.user = user;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}