package org.factoriaf5.vcp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @Test
    public void testPatientConstructorAndGetters() {
   
        User user = new User("testUser", "password", UserType.USER, "1234567890");
        Patient patient = new Patient("Fluffy", user, 5, "Golden Retriever", GenderType.W, "http://example.com/image.jpg");

        assertEquals("Fluffy", patient.getName());
        assertEquals(user, patient.getUser());
        assertEquals(5, patient.getAge());
        assertEquals("Golden Retriever", patient.getBreed());
        assertEquals(GenderType.W, patient.getGender());
        assertEquals("http://example.com/image.jpg", patient.getImageUrl());
    }

    @Test
    public void testSetName() {

        Patient patient = new Patient();
        String newName = "Buddy";


        patient.setName(newName);

        assertEquals(newName, patient.getName());
    }

    @Test
    public void testSetAge() {
   
        Patient patient = new Patient();
        int newAge = 7;

        patient.setAge(newAge);

        assertEquals(newAge, patient.getAge());
    }

    @Test
    public void testSetBreed() {
  
        Patient patient = new Patient();
        String newBreed = "Labrador";

        patient.setBreed(newBreed);

        assertEquals(newBreed, patient.getBreed());
    }

    @Test
    public void testSetGender() {
        Patient patient = new Patient();
        GenderType newGender = GenderType.M;
    
        patient.setGender(newGender);
    
        assertEquals(newGender, patient.getGender());
    }

    @Test
    public void testSetImageUrl() {
    
        Patient patient = new Patient();
        String newImageUrl = "http://example.com/new_image.jpg";

        patient.setImageUrl(newImageUrl);

        assertEquals(newImageUrl, patient.getImageUrl());
    }

    @Test
    public void testSetUser() {
     
        Patient patient = new Patient();
        User user = new User("testUser", "password", UserType.USER, "1234567890");

        patient.setUser(user);

        assertEquals(user, patient.getUser());
    }
}
