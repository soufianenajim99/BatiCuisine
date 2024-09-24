package org.baticuisine.Services.ServicesInterfaces;

import org.baticuisine.Models.Devis;

import java.util.List;
import java.util.Optional;

public interface DevisServiceInterface {
    Devis createDevis(Devis devis);
    Optional<Devis> getDevisById(int id);
    List<Devis> getAllDevis();
    void removeDevis(int id);
}
