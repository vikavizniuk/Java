package lab1;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class App {
    public static void main(String[] args) {
    

        Owner owner = new Owner.Builder()
                .setName("Іван Іваненко")
                .setContactInfo("ivan@example.com")
                .setAddress("Київ, вул. Хрещатик, 1")
                .build();

            Pet pet1 = new Pet.Builder()
                .setName("Барсик")
                .setBirthDate(LocalDate.of(2021, 9, 10))
                .setSpecies("Кіт")
                .setOwner(owner)
                .build();

            Pet pet2 = new Pet.Builder()
                .setName("Рекс")
                .setBirthDate(LocalDate.of(2019, 5, 15))
                .setSpecies("Пес")
                .setOwner(owner)
                .build();

        owner.addPet(pet1);
        owner.addPet(pet2);

        Veterinarian vet = new Veterinarian.Builder()
                .setName("Дмитро Коваленко")
                .setSpeciality(Speciality.Head)
                .build();

            Appointment appointment = new Appointment.Builder()
                .setPet(pet1)
                .setVeterinarian(vet)
                .setDateTime(LocalDateTime.of(2024, 11, 10, 14, 0))
                .build();
            
        System.out.println("\nІнформація про запис на прийом:");
        System.out.println(appointment);

    }
    public Object getGreeting() {
        throw new UnsupportedOperationException("Unimplemented method 'getGreeting'");
    }
}