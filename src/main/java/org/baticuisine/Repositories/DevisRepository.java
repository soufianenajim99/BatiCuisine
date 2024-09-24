package org.baticuisine.Repositories;

import org.baticuisine.Models.Devis;
import org.baticuisine.Repositories.RepositoriesInterfaces.DevisRepositoryInterface;
import org.baticuisine.Utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class DevisRepository implements DevisRepositoryInterface {
    private Connection connection;

    public DevisRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Devis save(Devis devis) {
        String query;
        if (findById(devis.getId()).isEmpty()) {
            query = "INSERT INTO devis (montantestime,project_id, dateemission, datevalidite, accepte) VALUES (?,?, ?, ?, ?)";
        } else {
            query = "UPDATE devis SET montantestime = ?,project_id=?, dateemission = ?, datevalidite = ?, accepte = ? WHERE id = ?";
        }
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, devis.getMontantEstime());
            stmt.setDouble(2, devis.getProject().getId());
            stmt.setDate(3, Date.valueOf(devis.getStartDate()));
            stmt.setDate(4, Date.valueOf(devis.getDateValidite()));
            stmt.setBoolean(5, devis.isAccepte());

            if (devis.getId() != 0) {
                stmt.setInt(6, devis.getId());
            }
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0 && devis.getId() == 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        devis.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Failed to obtain the Devis ID.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving devis: " + e.getMessage());
        }
        return devis;
    }

    @Override
    public Optional<Devis> findById(int id) {
        String query = "SELECT * FROM devis WHERE id = ?";
        Devis devis = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                devis = new Devis(
                        rs.getInt("id"),
                        rs.getDouble("montantestime"),
                        new ProjectRepository().findById(rs.getInt("project_id")).orElse(null),
                        rs.getDate("dateemission").toLocalDate(),
                        rs.getDate("datevalidite").toLocalDate(),
                        rs.getBoolean("accepte")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding devis: " + e.getMessage());
        }
        return Optional.ofNullable(devis);
    }

    @Override
    public List<Devis> findAll() {
        String query = "SELECT * FROM devis";
        List<Devis> devisList = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Devis devis = new Devis(
                        rs.getInt("id"),
                        rs.getDouble("montantestime"),
                        new ProjectRepository().findById(rs.getInt("project_id")).orElse(null),
                        rs.getDate("dateemission").toLocalDate(),
                        rs.getDate("datevalidite").toLocalDate(),
                        rs.getBoolean("accepte")
                );
                devisList.add(devis);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching devis: " + e.getMessage());
        }
        return devisList;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM devis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting devis: " + e.getMessage());
        }
    }
}
