package lab1;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class App {
    public static void main(String[] args) {
        
        LocalDate birthDate1 = LocalDate.of(2021, 9, 10);
        LocalDate birthDate2 = LocalDate.of(2019, 5, 15);

        Owner owner = new Owner("Іван Іваненко", "ivan@example.com", "Київ, вул. Хрещатик, 1");

        Pet pet1 = new Pet("Барсик", birthDate1, "Кіт", owner);
        Pet pet2 = new Pet("Рекс", birthDate2, "Пес", owner);

        owner.addPet(pet1);
        owner.addPet(pet2);

        Veterinarian vet = new Veterinarian("Дмитро Коваленко", Speciality.Head);

        Appointment appointment = new Appointment.Builder()
            .setPet(pet1)
            .setVeterinarian(vet)
            .setDateTime(LocalDateTime.of(2024, 9, 10, 14, 0))
            .build();
            
        System.out.println("\nІнформація про запис на прийом:");
        System.out.println(appointment);

        System.out.println("\nПорівняння домашніх улюбленців:");
        System.out.println("pet1 дорівнює pet2? " + pet1.equals(pet2));

        System.out.println("\nHash-коди об'єктів:");
        System.out.println("Hash-код pet1: " + pet1.hashCode());
        System.out.println("Hash-код owner: " + owner.hashCode());

    }
    public Object getGreeting() {
        throw new UnsupportedOperationException("Unimplemented method 'getGreeting'");
    }
}