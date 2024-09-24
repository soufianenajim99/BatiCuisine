package org.baticuisine.Repositories.RepositoriesInterfaces;

import org.baticuisine.Models.Materiel;
import org.baticuisine.Models.Project;

import java.util.List;
import java.util.Optional;

public interface MaterielRepositoryInterface {
    Materiel save(Materiel materiel);
    Optional<Materiel> findById(int id);
    List<Materiel> findAll();
    void delete(int id);
}
