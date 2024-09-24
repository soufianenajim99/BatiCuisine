package org.baticuisine.Services;

import org.baticuisine.Models.Devis;
import org.baticuisine.Repositories.DevisRepository;
import org.baticuisine.Repositories.RepositoriesInterfaces.DevisRepositoryInterface;
import org.baticuisine.Services.ServicesInterfaces.DevisServiceInterface;

import java.util.List;
import java.util.Optional;

public class DevisService implements DevisServiceInterface {
    private final DevisRepositoryInterface devisRepository;

    public DevisService() {
        this.devisRepository = new DevisRepository();
    }

    @Override
    public Devis createDevis(Devis devis) {
        return devisRepository.save(devis);
    }

    @Override
    public Optional<Devis> getDevisById(int id) {
        return devisRepository.findById(id);
    }

    @Override
    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }

    @Override
    public void removeDevis(int id) {
        devisRepository.delete(id);
    }
}
