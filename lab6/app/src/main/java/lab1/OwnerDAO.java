package lab1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAO {

    public int createOwner(Owner owner) throws SQLException {
        String sql = "INSERT INTO owner (name, contact_info, address) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, owner.getName());
            stmt.setString(2, owner.getContactInfo());
            stmt.setString(3, owner.getAddress());
            stmt.executeUpdate();
    
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); 
            } else {
                throw new SQLException("Не вдалося створити власника, ID не було згенеровано.");
            }
        }
    }
    

    public Owner getOwnerById(int id) throws SQLException {
        String sql = "SELECT * FROM owner WHERE id = ?"; 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Owner.Builder()
                    .setId(rs.getInt("id")) 
                    .setName(rs.getString("name"))
                    .setContactInfo(rs.getString("contact_info"))
                    .setAddress(rs.getString("address"))
                    .build();
            }
        }
        return null;
    }
    

    public List<Owner> getAllOwners() throws SQLException {
        List<Owner> owners = new ArrayList<>();
        String sql = "SELECT * FROM owner";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Owner owner = new Owner.Builder()
                .setName(rs.getString("name"))
                .setContactInfo(rs.getString("contact_info"))
                .setAddress(rs.getString("address"))
                .build();
                owner.setId(rs.getInt("id"));
                owners.add(new Owner.Builder()
                    .setName(rs.getString("name"))
                    .setContactInfo(rs.getString("contact_info"))
                    .setAddress(rs.getString("address"))
                    .build());
            }
        }
        return owners;
    }


    public int getOwnerIdByName(String name) throws SQLException {
        String sql = "SELECT id FROM Owner WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    public void updateOwner(int id, Owner owner) throws SQLException {
        String sql = "UPDATE owner SET name = ?, contact_info = ?, address = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, owner.getName());
            stmt.setString(2, owner.getContactInfo());
            stmt.setString(3, owner.getAddress());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    public void deleteOwner(int id) throws SQLException {
        String sql = "DELETE FROM owner WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
