package lab1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public int createAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointment (pet_id, veterinarian_id, appointment_date_time) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, appointment.getPet().getId());
            stmt.setInt(2, appointment.getVeterinarian().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(appointment.getDateTime()));
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); 
            } else {
                throw new SQLException("Не вдалося створити призначення, ID не було згенеровано.");
            }
        }
    }

    public Appointment getAppointmentById(int id) throws SQLException {
        String sql = "SELECT * FROM appointment WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pet pet = new PetDAO().getPetById(rs.getInt("pet_id"));
                Veterinarian vet = new VeterinarianDAO().getVeterinarianById(rs.getInt("veterinarian_id"));

                return new Appointment.Builder()
                        .setPet(pet)
                        .setVeterinarian(vet)
                        .setDateTime(rs.getTimestamp("appointment_date_time").toLocalDateTime())
                        .build()
                        .setId(rs.getInt("id"));
            }
        }
        return null;
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pet pet = new PetDAO().getPetById(rs.getInt("pet_id"));
                Veterinarian vet = new VeterinarianDAO().getVeterinarianById(rs.getInt("veterinarian_id"));

                Appointment appointment = new Appointment.Builder()
                        .setPet(pet)
                        .setVeterinarian(vet)
                        .setDateTime(rs.getTimestamp("appointment_date_time").toLocalDateTime())
                        .build()
                        .setId(rs.getInt("id"));

                appointments.add(appointment);
            }
        }
        return appointments;
    }

    public void updateAppointment(int id, Appointment appointment) throws SQLException {
        String sql = "UPDATE appointment SET pet_id = ?, veterinarian_id = ?, appointment_date_time = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getPet().getId());
            stmt.setInt(2, appointment.getVeterinarian().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(appointment.getDateTime()));
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    public void deleteAppointment(int id) throws SQLException {
        String sql = "DELETE FROM appointment WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
