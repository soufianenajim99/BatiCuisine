package org.baticuisine.Repositories.RepositoriesInterfaces;

import org.baticuisine.Models.Client;
import org.baticuisine.Models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryInterface {
    Project saveProject(Project project);
    Optional<Project> findById(int id);
    List<Project> findAll();
    void delete(int id);
}
