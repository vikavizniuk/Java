package lab1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class App {
    public static void main(String[] args) {

        try{

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date birthDate1 = sdf.parse("10-09-2021");
        Date birthDate2 = sdf.parse("15-05-2019");

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
        }  catch (ParseException e) {
            System.err.println("Помилка розбору дати: " + e.getMessage());
        }
    }

    public Object getGreeting() {
        throw new UnsupportedOperationException("Unimplemented method 'getGreeting'");
    }
}

