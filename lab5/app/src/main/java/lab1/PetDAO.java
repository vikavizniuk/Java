package lab1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    public int createPet(Pet pet) throws SQLException {
        String sql = "INSERT INTO pet (name, birth_date, species, owner_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pet.getName());
            stmt.setDate(2, Date.valueOf(pet.getBirthDate())); 
            stmt.setString(3, pet.getSpecies());
            stmt.setInt(4, pet.getOwner().getId());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); 
            } else {
                throw new SQLException("Не вдалося створити тваринку, ID не було згенеровано.");
            }
        }
    }

    public Pet getPetById(int id) throws SQLException {
        String sql = "SELECT * FROM pet WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Owner owner = new OwnerDAO().getOwnerById(rs.getInt("owner_id")); 
                return new Pet.Builder()
                    .setName(rs.getString("name"))
                    .setBirthDate(rs.getDate("birth_date").toLocalDate()) 
                    .setSpecies(rs.getString("species"))
                    .setOwner(owner)
                    .build();
            }
        }
        return null;
    }

    public List<Pet> getAllPets() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Owner owner = new OwnerDAO().getOwnerById(rs.getInt("owner_id")); 
                Pet pet = new Pet.Builder()
                    .setName(rs.getString("name"))
                    .setBirthDate(rs.getDate("birth_date").toLocalDate()) 
                    .setSpecies(rs.getString("species"))
                    .setOwner(owner)
                    .build();
                pet.setId(rs.getInt("id"));
                pets.add(pet);
            }
        }
        return pets;
    }

    public void updatePet(int id, Pet pet) throws SQLException {
        String sql = "UPDATE pet SET name = ?, birth_date = ?, species = ?, owner_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pet.getName());
            stmt.setDate(2, Date.valueOf(pet.getBirthDate())); 
            stmt.setString(3, pet.getSpecies());
            stmt.setInt(4, pet.getOwner().getId()); 
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }

    public void deletePet(int id) throws SQLException {
        String sql = "DELETE FROM pet WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
