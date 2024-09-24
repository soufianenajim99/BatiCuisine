package org.baticuisine.Repositories;

import org.baticuisine.Models.Personnel;
import org.baticuisine.Models.Project;
import org.baticuisine.Repositories.RepositoriesInterfaces.PersonnelRepositoryInterface;
import org.baticuisine.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class PersonnelRepository implements PersonnelRepositoryInterface {
    private Connection connection;

    public PersonnelRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Personnel save(Personnel personnel) {
        String query;
        if (findById(personnel.getId()).isEmpty()) {
            query = "INSERT INTO personnels (nom, project_id, tauxhoraire, heurestravail, productiviteouvrier) VALUES (?, ?, ?, ?, ?)";
        } else {
            query = "UPDATE personnels SET nom = ?, project_id = ?, tauxhoraire = ?, heurestravail = ?, productiviteouvrier = ? WHERE id = ?";
        }
        try (PreparedStatement stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, personnel.getNom());
            stmt.setInt(2, personnel.getProject().getId());
            stmt.setDouble(3, personnel.getTauxHoraire());
            stmt.setDouble(4, personnel.getHeuresTravail());
            stmt.setDouble(5, personnel.getProductiviteOuvrier());

            if (personnel.getId() != 0) {
                stmt.setInt(6, personnel.getId());
            }
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        personnel.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Failed to obtain the personnel ID.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving personnel: " + e.getMessage());
        }
        return personnel;
    }

    @Override
    public Optional<Personnel> findById(int id) {
        String query = "SELECT * FROM personnels WHERE id = ?";
        Personnel personnel = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                personnel = new Personnel(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        new ProjectRepository().findById(rs.getInt("project_id")).orElse(null),
                        rs.getDouble("tauxhoraire"),
                        rs.getDouble("heurestravail"),
                        rs.getDouble("productiviteouvrier")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding personnel: " + e.getMessage());
        }
        return Optional.ofNullable(personnel);
    }

    @Override
    public List<Personnel> findAll() {
        String query = "SELECT * FROM personnels";
        List<Personnel> personnels = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Personnel personnel = new Personnel(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        new ProjectRepository().findById(rs.getInt("project_id")).orElse(null),
                        rs.getDouble("tauxhoraire"),
                        rs.getDouble("heurestravail"),
                        rs.getDouble("productiviteouvrier")
                );
                personnels.add(personnel);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching personnels: " + e.getMessage());
        }
        return personnels;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM personnels WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting personnel: " + e.getMessage());
        }
    }
}
