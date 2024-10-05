package lab1;

import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {

    public static List<Appointment> findAppointmentsByVeterinarian(List<Appointment> appointments, Veterinarian veterinarian) {
        return appointments.stream()
                           .filter(appointment -> appointment.getVeterinarian().equals(veterinarian))
                           .collect(Collectors.toList());
    }
}
