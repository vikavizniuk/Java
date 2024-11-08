package lab1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarianDAO {

    public int createVeterinarian(Veterinarian veterinarian) throws SQLException {
        String sql = "INSERT INTO veterinarian (name, speciality) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, veterinarian.getName());
            stmt.setString(2, veterinarian.getSpecialty().name()); 
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); 
            } else {
                throw new SQLException("Unable to create veterinarian; no ID was generated.");
            }
        }
    }

    public Veterinarian getVeterinarianById(int id) throws SQLException {
        String sql = "SELECT * FROM veterinarian WHERE id = ?"; 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Veterinarian.Builder()
                    .setId(rs.getInt("id"))
                    .setName(rs.getString("name"))
                    .setSpeciality(Speciality.valueOf(rs.getString("speciality")))
                    .build();
            }
        }
        return null;
    }

    public List<Veterinarian> getAllVeterinarians() throws SQLException {
        List<Veterinarian> veterinarians = new ArrayList<>();
        String sql = "SELECT * FROM veterinarian";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Veterinarian veterinarian = new Veterinarian.Builder()
                    .setId(rs.getInt("id"))
                    .setName(rs.getString("name"))
                    .setSpeciality(Speciality.valueOf(rs.getString("speciality"))) 
                    .build();
                veterinarians.add(veterinarian);
            }
        }
        return veterinarians;
    }

    public void updateVeterinarian(int id, Veterinarian veterinarian) throws SQLException {
        String sql = "UPDATE veterinarian SET name = ?, speciality = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veterinarian.getName());
            stmt.setString(2, veterinarian.getSpecialty().name());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    public void deleteVeterinarian(int id) throws SQLException {
        String sql = "DELETE FROM veterinarian WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
