package org.baticuisine.Services;

import org.baticuisine.Models.Personnel;
import org.baticuisine.Repositories.PersonnelRepository;
import org.baticuisine.Repositories.RepositoriesInterfaces.PersonnelRepositoryInterface;
import org.baticuisine.Services.ServicesInterfaces.PersonnelServiceInterface;

import java.util.List;
import java.util.Optional;

public class PersonnelService implements PersonnelServiceInterface {
    private final PersonnelRepositoryInterface personnelRepository;

    public PersonnelService() {
        this.personnelRepository = new PersonnelRepository();
    }

    @Override
    public Personnel addPersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public Optional<Personnel> getPersonnelById(int id) {
        return personnelRepository.findById(id);
    }

    @Override
    public List<Personnel> getAllPersonnels() {
        return personnelRepository.findAll();
    }

    @Override
    public void removePersonnel(int id) {
        personnelRepository.delete(id);
    }
}
