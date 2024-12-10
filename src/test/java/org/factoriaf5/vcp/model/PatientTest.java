package org.factoriaf5.vcp.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PatientTest {

    @Test
    public void testConstructorAndGetters() {
        String name = "Buddy";
        Long idUser = 1L;
        int age = 5;
        String breed = "Golden Retriever";
        GenderType gender = GenderType.M;
        String imageUrl = "https://example.com/images/buddy.jpg";

        Patient patient = new Patient(name, idUser, age, breed, gender, imageUrl);

        assertThat(patient.getName()).isEqualTo(name);
        assertThat(patient.getIdUser()).isEqualTo(idUser);
        assertThat(patient.getAge()).isEqualTo(age);
        assertThat(patient.getBreed()).isEqualTo(breed);
        assertThat(patient.getGender()).isEqualTo(gender);
        assertThat(patient.getImageUrl()).isEqualTo(imageUrl);
    }

    @Test
    public void testSetters() {
 
        Patient patient = new Patient();
        String name = "Lucy";
        Long idUser = 2L;
        int age = 3;
        String breed = "Chihuahua";
        GenderType gender = GenderType.W;
        String imageUrl = "https://example.com/images/lucy.jpg";

        patient.setName(name);
        patient.setIdUser(idUser);
        patient.setAge(age);
        patient.setBreed(breed);
        patient.setGender(gender);
        patient.setImageUrl(imageUrl);

        assertThat(patient.getName()).isEqualTo(name);
        assertThat(patient.getIdUser()).isEqualTo(idUser);
        assertThat(patient.getAge()).isEqualTo(age);
        assertThat(patient.getBreed()).isEqualTo(breed);
        assertThat(patient.getGender()).isEqualTo(gender);
        assertThat(patient.getImageUrl()).isEqualTo(imageUrl);
    }

    @Test
    public void testIdCounter() {
        Patient.setIdCounter(0);
        Patient patient1 = new Patient("Buddy", 1L, 5, "Golden Retriever", GenderType.M, "https://example.com/images/buddy.jpg");
        Patient patient2 = new Patient("Lucy", 2L, 3, "Chihuahua", GenderType.W, "https://example.com/images/lucy.jpg");

        long id1 = patient1.getId();
        long id2 = patient2.getId();

        assertThat(id1).isEqualTo(1L);
        assertThat(id2).isEqualTo(2L);
    }

    @Test
    public void testDefaultConstructor() {
        
        Patient patient = new Patient();

        assertThat(patient.getName()).isNull();
        assertThat(patient.getIdUser()).isNull();
        assertThat(patient.getAge()).isEqualTo(0);
        assertThat(patient.getBreed()).isNull();
        assertThat(patient.getGender()).isNull();
        assertThat(patient.getImageUrl()).isNull();
    }
}
