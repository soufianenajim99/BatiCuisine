package org.baticuisine.Repositories;

import org.baticuisine.Enums.EtatProject;
import org.baticuisine.Models.Client;
import org.baticuisine.Models.Project;
import org.baticuisine.Repositories.RepositoriesInterfaces.ClientRepositoryInterface;
import org.baticuisine.Repositories.RepositoriesInterfaces.ProjectRepositoryInterface;
import org.baticuisine.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository implements ProjectRepositoryInterface {
    private Connection connection;
    private ClientRepositoryInterface clientRepository;
    public ProjectRepository() {
        this.connection = DatabaseConnection.getConnection();
        this.clientRepository = new ClientRepository();
    }

    @Override
    public Project saveProject(Project project) {
        String query;
        if (this.findById(project.getId()).isPresent()) {
            query = "UPDATE projects SET nomprojet = ?, margebeneficiaire = ?,couttotal = ? ,client_id = ?, projectstatus = ?, surface = ? WHERE id = ? ";
        } else {
            query = "INSERT INTO projects (nomprojet, margebeneficiaire, couttotal,client_id,projectstatus, surface) VALUES (?, ?, ?, ?,?,?)";
        }
        try(PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
             ps.setString(1,project.getNomProjet());
             ps.setDouble(2,project.getMargeBeneficiaire());
             ps.setDouble(3,project.getCoutTotal());
             ps.setInt(4,project.getClient().getId());
             ps.setObject(5, EtatProject.EN_COURS, java.sql.Types.OTHER);
             ps.setDouble(6,project.getSurface());
            if (project.getId() != 0) {
                ps.setInt(7, project.getId());
            }
            int affectedRows=ps.executeUpdate();
            if (affectedRows > 0 && project.getId() == 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        project.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Failed to obtain the project ID.");
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
        return project;

    }

    @Override
    public Optional<Project> findById(int id) {
        String query = "SELECT * FROM projects WHERE id = ?";
        Optional<Project> project = Optional.empty();

        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Client client = clientRepository.findById(rs.getInt("clientid"));
                project = Optional.of(new Project(
                            rs.getInt("id"),
                            rs.getString("nomprojet"),
                            rs.getDouble("margebeneficiaire"),
                            rs.getDouble("couttotal"),
                            EtatProject.valueOf(rs.getString("etatprojet")),
                            rs.getDouble("surface"),
                            client

                    ));
                }
                return project;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Project> findAll() {
        String query = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Client client = clientRepository.findById(rs.getInt("clientid"));
                Project project = new Project(
                        rs.getInt("id"),
                        rs.getString("nomprojet"),
                        rs.getDouble("margebeneficiaire"),
                        rs.getDouble("couttotal"),
                        EtatProject.valueOf(rs.getString("etatprojet")),
                        rs.getDouble("surface"),
                        client
                );
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching projects: " + e.getMessage());
        }
        return projects;
    }

    @Override
    public void delete(int id) {
        String query = "UPDATE projects SET projectstatus = ANNULE WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating project status to canceled: " + e.getMessage());
        }
    }

}
