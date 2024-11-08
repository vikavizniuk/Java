package lab1;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lab1.services.OwnerService;
import lab1.services.VeterinarianService;
import lab1.services.PetService;
import lab1.services.AppointmentService;

public class App {
    public static void main(String[] args) {

        OwnerDAO ownerDAO = new OwnerDAO();
        PetDAO petDAO = new PetDAO();
        VeterinarianDAO vetDAO = new VeterinarianDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();

        OwnerService ownerService = new OwnerService();
        PetService petService = new PetService();
        VeterinarianService vetService = new VeterinarianService();
        AppointmentService appointmentService = new AppointmentService();

        try {
            Owner owner = new Owner.Builder()
                    .setName("Йосип Бойчук")
                    .setContactInfo("josyp@example.com")
                    .setAddress("Чернівці, вул. Богдана Хмельницького, 112")
                    .build();
            int ownerId = ownerDAO.createOwner(owner); 
            owner.setId(ownerId);
            Optional<Owner> fetchedOwnerOpt = ownerService.getOwnerById(ownerId);
            if (fetchedOwnerOpt.isPresent()) {
                Owner fetchedOwner = fetchedOwnerOpt.get();
                System.out.println("Вибраний власник: " + fetchedOwner);


                Pet pet = new Pet.Builder()
                        .setName("Юмі")
                        .setBirthDate(LocalDate.of(2020, 5, 4))
                        .setSpecies("Собака")
                        .setOwner(fetchedOwner)
                        .build();
                int petId = petDAO.createPet(pet); 
                pet.setId(petId);
                Optional<Pet> fetchedPetOpt = petService.getPetById(petId);
                if (fetchedPetOpt.isPresent()) {
                    Pet fetchedPet = fetchedPetOpt.get();
                    System.out.println("Вибрана тваринка: " + fetchedPet);

                    
                    Veterinarian vet = new Veterinarian.Builder()
                            .setName("Павло Мельник")
                            .setSpeciality(Speciality.Eyes)
                            .build();
                    int vetId = vetDAO.createVeterinarian(vet); 
                    vet.setId(vetId);
                    Optional<Veterinarian> fetchedVetOpt = vetService.getVeterinarianById(vetId);
                    if (fetchedVetOpt.isPresent()) {
                        Veterinarian fetchedVet = fetchedVetOpt.get();
                        System.out.println("Вибраний ветеринар: " + fetchedVet);

                        
                        Appointment appointment = new Appointment.Builder()
                            .setPet(pet)
                            .setVeterinarian(vet)
                            .setDateTime(LocalDateTime.of(2022, 3, 3, 12, 30))
                            .build();
                        int appointmentId = appointmentDAO.createAppointment(appointment); 
                        appointment.setId(appointmentId);
                        Optional<Appointment> retrievedAppointmentOpt = appointmentService.getAppointmentById(appointmentId);
                        if (retrievedAppointmentOpt.isPresent()) {
                            Appointment retrievedAppointment = retrievedAppointmentOpt.get();
                            System.out.println("Вибраний запис: " + retrievedAppointment);

            
                            // Owner updatedOwner = new Owner.Builder()
                            //         .setName("Василь Симоненко")
                            //         .setContactInfo("vasil@gmail.com")
                            //         .setAddress("Чернівці, вул. Лесі Українки, 2")
                            //         .build();
                            // ownerService.updateOwner(ownerId, updatedOwner);
                            // System.out.println("Оновлений власник: " + ownerService.getOwnerById(ownerId).orElse(null));

                            // petService.deletePet(petId);
                            // System.out.println("Тваринку видалено.");

                            // appointment = new Appointment.Builder()
                            //     .setPet(fetchedPet)
                            //     .setVeterinarian(fetchedVet)
                            //     .setDateTime(LocalDateTime.of(2023, 12, 1, 14, 0))
                            //     .build();
                            // appointmentService.updateAppointment(appointmentId, appointment);
                            // System.out.println("Оновлений запис: " + appointmentService.getAppointmentById(appointmentId).orElse(null));

                            // List<Appointment> appointments = appointmentService.getAllAppointments();
                            // System.out.println("Всі записи:");
                            // for (Appointment app : appointments) {
                            //     System.out.println(app);
                            // }

                            // appointmentService.deleteAppointment(appointmentId);
                            // System.out.println("Запис видалено.");
                        } else {
                            System.out.println("Запис на прийом не знайдено.");
                        }
                    } else {
                        System.out.println("Ветеринара з таким ID не знайдено.");
                    }
                } else {
                    System.out.println("Тваринку з таким ID не знайдено.");
                }
            } else {
                System.out.println("Власника з таким ID не знайдено.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object getGreeting() {
        throw new UnsupportedOperationException("Unimplemented method 'getGreeting'");
    }
}
