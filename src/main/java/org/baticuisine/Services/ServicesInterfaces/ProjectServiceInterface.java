package org.baticuisine.Services.ServicesInterfaces;

import org.baticuisine.Models.Client;
import org.baticuisine.Models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectServiceInterface {
    void createProject(Project project, Client client);
    Optional<Project> getProjectById(int id);
    List<Project> getAllProjects();
    void removeProject(int id);
}
