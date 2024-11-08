package lab1;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class App {
    public static void main(String[] args) {
    
        OwnerDAO ownerDAO = new OwnerDAO();
        PetDAO petDAO = new PetDAO();
        VeterinarianDAO vetDAO = new VeterinarianDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();

        try {
            Owner owner = new Owner.Builder()
                    .setName("Іван Іваненко")
                    .setContactInfo("ivan@example.com")
                    .setAddress("Київ, вул. Хрещатик, 1")
                    .build();
            int ownerId = ownerDAO.createOwner(owner); 
            owner.setId(ownerId);

            Owner fetchedOwner = ownerDAO.getOwnerById(ownerId);
            System.out.println(fetchedOwner);
            
            Pet pet = new Pet.Builder()
                    .setName("Мурка")
                    .setBirthDate(LocalDate.of(2020, 5, 1))
                    .setSpecies("Кіт")
                    .setOwner(fetchedOwner) 
                    .build();
            int petId = petDAO.createPet(pet);
            pet.setId(petId);

            Pet fetchedPet = petDAO.getPetById(petId);
            System.out.println(fetchedPet);

            Veterinarian vet = new Veterinarian.Builder()
                    .setName("Д-р Анна Петрова")
                    .setSpeciality(Speciality.Head) 
                    .build();
            int vetId = vetDAO.createVeterinarian(vet);
            vet.setId(vetId);

            Veterinarian fetchedVet = vetDAO.getVeterinarianById(vetId);
            System.out.println(fetchedVet);

            Appointment appointment = new Appointment.Builder()
                .setPet(pet)
                .setVeterinarian(vet)
                .setDateTime(LocalDateTime.of(2020, 04, 3, 10, 30))
                .build();
            int appointmentId = appointmentDAO.createAppointment(appointment);
            appointment.setId(appointmentId);

            System.out.println("Appointment created: " + appointment);

            Appointment retrievedAppointment = appointmentDAO.getAppointmentById(appointmentId);
            System.out.println("Retrieved appointment: " + retrievedAppointment);
    



            // Owner updatedOwner = new Owner.Builder()
            //         .setName("Іван Петрович")
            //         .setContactInfo("ivan_updated@example.com")
            //         .setAddress("Київ, вул. Лесі Українки, 2")
            //         .build();
            // ownerDAO.updateOwner(ownerId, updatedOwner);
            // ownerDAO.deleteOwner(4);

            // petDAO.deletePet(3);

            // appointment = new Appointment.Builder()
            //     .setPet(pet)
            //     .setVeterinarian(vet)
            //     .setDateTime(LocalDateTime.of(2023, 12, 1, 14, 0))
            //     .build();
            // appointmentDAO.updateAppointment(appointmentId, appointment);
            // System.out.println("Updated appointment: " + appointmentDAO.getAppointmentById(appointmentId));

            // List<Appointment> appointments = appointmentDAO.getAllAppointments();
            // System.out.println("All appointments:");
            // for (Appointment app : appointments) {
            //     System.out.println(app);
            // }
            
            // appointmentDAO.deleteAppointment(appointmentId);
            // System.out.println("Appointment deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    

    }
    public Object getGreeting() {
        throw new UnsupportedOperationException("Unimplemented method 'getGreeting'");
    }
}