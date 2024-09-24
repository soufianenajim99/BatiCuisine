package org.baticuisine.Repositories.RepositoriesInterfaces;
import org.baticuisine.Models.Devis;

import java.util.List;
import java.util.Optional;
public interface DevisRepositoryInterface {
    Devis save(Devis devis);
    Optional<Devis> findById(int id);
    List<Devis> findAll();
    void delete(int id);
}
