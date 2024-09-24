package org.baticuisine.Repositories.RepositoriesInterfaces;

import org.baticuisine.Models.Personnel;
import org.baticuisine.Models.Project;

import java.util.List;
import java.util.Optional;

public interface PersonnelRepositoryInterface {
    Personnel save(Personnel personnel);
    Optional<Personnel> findById(int id);
    List<Personnel> findAll();
    void delete(int id);
}
