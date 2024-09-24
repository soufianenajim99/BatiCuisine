package org.baticuisine.Services;

import org.baticuisine.Models.Materiel;
import org.baticuisine.Models.Project;
import org.baticuisine.Repositories.MaterielRepository;
import org.baticuisine.Repositories.RepositoriesInterfaces.MaterielRepositoryInterface;
import org.baticuisine.Services.ServicesInterfaces.MaterielServiceInterface;

import java.util.List;
import java.util.Optional;

public class MaterielService implements MaterielServiceInterface {
    private final MaterielRepositoryInterface materielRepository;

    public MaterielService() {
        this.materielRepository = new MaterielRepository();
    }

    @Override
    public Materiel addMateriel(Materiel materiel) {
        return materielRepository.save(materiel);
    }

    @Override
    public Optional<Materiel> getMaterielById(int id) {
        return materielRepository.findById(id);
    }

    @Override
    public List<Materiel> getAllMateriels() {
        return materielRepository.findAll();
    }

    @Override
    public void removeMateriel(int id) {
        materielRepository.delete(id);
    }
}
