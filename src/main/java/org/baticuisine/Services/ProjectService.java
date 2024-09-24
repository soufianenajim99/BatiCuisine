package org.baticuisine.Services;

import org.baticuisine.Models.Client;
import org.baticuisine.Models.Project;
import org.baticuisine.Repositories.ProjectRepository;
import org.baticuisine.Services.ServicesInterfaces.ProjectServiceInterface;

import java.util.List;
import java.util.Optional;

public class ProjectService implements ProjectServiceInterface {
    private ProjectRepository projectRepository;

    public ProjectService(){
        this.projectRepository = new ProjectRepository();
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.saveProject(project);
    }

    @Override
    public Optional<Project> getProjectById(int id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void removeProject(int id) {
            projectRepository.delete(id);
    }

}
