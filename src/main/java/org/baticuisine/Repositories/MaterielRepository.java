package org.baticuisine.Repositories;

import org.baticuisine.Models.Materiel;
import org.baticuisine.Models.Project;
import org.baticuisine.Repositories.RepositoriesInterfaces.MaterielRepositoryInterface;
import org.baticuisine.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterielRepository implements MaterielRepositoryInterface {
    private Connection connection;

    public MaterielRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Materiel save(Materiel materiel) {
        String query;
        if (findById(materiel.getId()).isEmpty()) {
            query = "INSERT INTO materiels (nom, project_id, coutunitaire, quantite, couttransport, coefficientqualite) VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            query = "UPDATE materiels SET nom = ?, project_id = ?, coutunitaire = ?, quantite = ?, couttransport = ?, coefficientqualite = ? WHERE id = ?";
        }
        try (PreparedStatement stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, materiel.getNom());
            stmt.setInt(2, materiel.getProject().getId());
            stmt.setDouble(3, materiel.getCoutUnitaire());
            stmt.setDouble(4, materiel.getQuantite());
            stmt.setDouble(5, materiel.getCoutTransport());
            stmt.setDouble(6, materiel.getCoefficientQualite());

            if (materiel.getId() != 0) {
                stmt.setInt(7, materiel.getId());
            }
            int affectedRows =stmt.executeUpdate();
            if (affectedRows > 0 && materiel.getId() == 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        materiel.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Failed to obtain the project ID.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving materiel: " + e.getMessage());
        }
        return materiel;
    }

    @Override
    public Optional<Materiel> findById(int id) {
        String query = "SELECT * FROM materiels WHERE id = ?";
        Materiel materiel = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                materiel = new Materiel(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        // Assuming ProjectRepository exists to retrieve a project by id
                        new ProjectRepository().findById(rs.getInt("project_id")).orElse(null),
                        rs.getDouble("coutunitaire"),
                        rs.getDouble("quantite"),
                        rs.getDouble("coutTransport"),
                        rs.getDouble("coefficientqualite")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding materiel: " + e.getMessage());
        }
        return Optional.ofNullable(materiel);
    }

    @Override
    public List<Materiel> findAll() {
        String query = "SELECT * FROM materiels";
        List<Materiel> materiels = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Materiel materiel = new Materiel(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        new ProjectRepository().findById(rs.getInt("project_id")).orElse(null),
                        rs.getDouble("coutunitaire"),
                        rs.getDouble("quantite"),
                        rs.getDouble("coutTransport"),
                        rs.getDouble("coefficientqualite")
                );
                materiels.add(materiel);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching materiels: " + e.getMessage());
        }
        return materiels;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM materiels WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting materiel: " + e.getMessage());
        }
    }
}
