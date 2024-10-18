package lab1;

import static org.testng.Assert.*;
import java.time.LocalDateTime;


import java.time.LocalDate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


class AppTest {
    
    @Test(dataProvider = "petProvider")
    public void testCompareTo(Pet pet1, Pet pet2, int expected) {
        assertEquals(pet1.compareTo(pet2), expected);
    }

    @DataProvider
    public Object[][] petProvider() {
        Owner owner = new Owner.Builder()
                        .setName("Іван Іваненко")
                        .setContactInfo("ivan@example.com")
                        .setAddress("Київ, вул. Хрещатик, 1")
                        .build();
        return new Object[][]{
            {new Pet.Builder()
                    .setName("Барсик")
                    .setBirthDate(LocalDate.of(2021, 9, 10))
                    .setSpecies("Кіт")
                    .setOwner(owner)
                    .build(),
            new Pet.Builder()
                    .setName("Рекс")
                    .setBirthDate(LocalDate.of(2020, 5, 15))
                    .setSpecies("Пес")
                    .setOwner(owner)
                    .build(), -1},
            {new Pet.Builder()
                    .setName("Барсик")
                    .setBirthDate(LocalDate.of(2021, 9, 10))
                    .setSpecies("Кіт")
                    .setOwner(owner)
                    .build(),
            new Pet.Builder()
                    .setName("Барсик")
                    .setBirthDate(LocalDate.of(2021, 9, 10))
                    .setSpecies("Кіт")
                    .setOwner(owner)
                    .build(), 0},
            {new Pet.Builder()
                    .setName("Рекс")
                    .setBirthDate(LocalDate.of(2020, 5, 15))
                    .setSpecies("Пес")
                    .setOwner(owner)
                    .build(),
            new Pet.Builder()
                    .setName("Барсик")
                    .setBirthDate(LocalDate.of(2021, 9, 10))
                    .setSpecies("Кіт")
                    .setOwner(owner)
                    .build(), 1}
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "invalidPetProvider")
    public void testInvalidPetCreation(LocalDate birthDate) {
        Owner owner = new Owner.Builder()
                        .setName("Іван Іваненко")
                        .setContactInfo("ivan@example.com")
                        .setAddress("Київ, вул. Хрещатик, 1")
                        .build();
        new Pet.Builder()
                .setName("Барсик")
                .setBirthDate(birthDate)
                .setSpecies("Кіт")
                .setOwner(owner)
                .build();
    }

    @DataProvider
    public Object[][] invalidPetProvider() {
        return new Object[][]{
            {LocalDate.of(2025, 1, 1)}, 
            {LocalDate.of(3000, 12, 31)} 
        };
    }

    @Test
    public void testValidOwnerCreation() {
        Owner owner = new Owner.Builder()
                        .setName("Іван Іваненко")
                        .setContactInfo("ivan@example.com")
                        .setAddress("Київ, вул. Хрещатик, 1")
                        .build();
        assertNotNull(owner);
        assertEquals(owner.getName(), "Іван Іваненко");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidOwnerEmail() {
        new Owner.Builder()
                .setName("Іван Іваненко")
                .setContactInfo("invalid_email")
                .setAddress("Київ, вул. Хрещатик, 1")
                .build();
    }

    @Test
    public void testValidAppointmentCreation() {
        Owner owner = new Owner.Builder()
                        .setName("Іван Іваненко")
                        .setContactInfo("ivan@example.com")
                        .setAddress("Київ, вул. Хрещатик, 1")
                        .build();
        Pet pet = new Pet.Builder()
                    .setName("Барсик")
                    .setBirthDate(LocalDate.of(2021, 9, 10))
                    .setSpecies("Кіт")
                    .setOwner(owner)
                    .build();
        Veterinarian vet = new Veterinarian.Builder()
                            .setName("Дмитро Коваленко")
                            .setSpeciality(Speciality.Head)
                            .build();

        Appointment appointment = new Appointment.Builder()
                                    .setPet(pet)
                                    .setVeterinarian(vet)
                                    .setDateTime(LocalDateTime.of(2024, 11, 10, 14, 0))
                                    .build();
        assertNotNull(appointment);
        assertEquals(appointment.getPet(), pet);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*в минулому.*")
    public void testInvalidAppointmentDate() {
        Owner owner = new Owner.Builder()
                        .setName("Іван Іваненко")
                        .setContactInfo("ivan@example.com")
                        .setAddress("Київ, вул. Хрещатик, 1")
                        .build();
        Pet pet = new Pet.Builder()
                    .setName("Барсик")
                    .setBirthDate(LocalDate.of(2021, 9, 10))
                    .setSpecies("Кіт")
                    .setOwner(owner)
                    .build();
        Veterinarian vet = new Veterinarian.Builder()
                            .setName("Дмитро Коваленко")
                            .setSpeciality(Speciality.Head)
                            .build();

        new Appointment.Builder()
            .setPet(pet)
            .setVeterinarian(vet)
            .setDateTime(LocalDateTime.of(1800, 9, 10, 14, 0))
            .build();
    }

}
