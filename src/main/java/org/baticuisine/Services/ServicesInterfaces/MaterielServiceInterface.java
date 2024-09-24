package org.baticuisine.Services.ServicesInterfaces;

import org.baticuisine.Models.Materiel;
import org.baticuisine.Models.Project;

import java.util.List;
import java.util.Optional;

public interface MaterielServiceInterface {
    Materiel addMateriel(Materiel materiel);
    Optional<Materiel> getMaterielById(int id);
    List<Materiel> getAllMateriels();
    void removeMateriel(int id);
}
