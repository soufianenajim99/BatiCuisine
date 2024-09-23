package org.baticuisine.Repositories;

import org.baticuisine.Models.Client;
import org.baticuisine.Repositories.RepositoriesInterfaces.ClientRepositoryInterface;
import org.baticuisine.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements ClientRepositoryInterface {
    private Connection connection;

    public ClientRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void save(Client client) {
        String query;
        if (this.findById(client.getId()) == null) {
            query = "INSERT INTO clients (nom, adresse, telephone, estprofessionnel) VALUES (?, ?, ?, ?)";
        } else {
            query = "UPDATE clients SET nom = ?, adresse = ?, telephone = ?, estprofessionnel = ? WHERE id = ?";
        }
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getAdresse());
            stmt.setString(3, client.getTelephone());
            stmt.setBoolean(4, client.isEstProfessionnel());
            if (client.getId() != 0) {
                stmt.setInt(5, client.getId());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving client: " + e.getMessage());
        }
    }

    @Override
    public Client findById(int id) {
        String query = "SELECT * FROM clients WHERE id = ?";
        Client client = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("estprofessionnel")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding client: " + e.getMessage());
        }
        return client;
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        String query = "SELECT * FROM clients WHERE email = ?";
        Optional<Client> client = Optional.empty();
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = Optional.of(new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("estprofessionnel")
                ));
            }
        }catch (SQLException e) {
            System.out.println("Error finding client: " + e.getMessage());
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        String query = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getBoolean("estprofessionnel")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching clients: " + e.getMessage());
        }
        return clients;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
    }

}
