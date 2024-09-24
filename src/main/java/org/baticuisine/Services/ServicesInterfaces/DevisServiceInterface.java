package org.baticuisine.Services.ServicesInterfaces;

import org.baticuisine.Models.Devis;
import org.baticuisine.Models.Project;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DevisServiceInterface {
    Devis createDevis(Devis devis);
    Optional<Devis> getDevisById(int id);
    List<Devis> getAllDevis();
    void removeDevis(int id);
    Map<String, Double> calculateTotalCost(Project project, double tvaPercentage, double marginPercentage);
}
