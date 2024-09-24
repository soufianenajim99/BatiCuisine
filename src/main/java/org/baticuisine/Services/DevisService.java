package org.baticuisine.Services;

import org.baticuisine.Models.Devis;
import org.baticuisine.Models.Materiel;
import org.baticuisine.Models.Personnel;
import org.baticuisine.Models.Project;
import org.baticuisine.Repositories.DevisRepository;
import org.baticuisine.Repositories.RepositoriesInterfaces.DevisRepositoryInterface;
import org.baticuisine.Services.ServicesInterfaces.DevisServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Override
    public Map<String, Double> calculateTotalCost(Project project, double tvaPercentage, double marginPercentage) {
    Map<String, Double> costDetails = new HashMap<>();

        double totalMaterialCost = project.getMaterielsList().stream().mapToDouble(Materiel::calculateTotalCost).sum();
        double totalMaterialCostWithTVA = tvaPercentage > 0 ? totalMaterialCost * (1 + tvaPercentage / 100) : totalMaterialCost;

        // Calculating total personnel costs
        double totalPersonnelCost = project.getPersonnelList().stream().mapToDouble(Personnel::calculatepersonelCost).sum();
        double totalPersonnelCostWithTVA = tvaPercentage > 0 ? totalPersonnelCost * (1 + tvaPercentage / 100) : totalPersonnelCost;

        // Total cost before profit margin
        double totalCostBeforeMargin = totalMaterialCost + totalPersonnelCost;
        double totalCostWithTVA = totalMaterialCostWithTVA + totalPersonnelCostWithTVA;

        // Applying profit margin
        double profitMargin = totalCostBeforeMargin * (marginPercentage / 100);
        double finalTotalCost = totalCostWithTVA + profitMargin;
    costDetails.put("totalMaterialCost", totalMaterialCost);
    costDetails.put("totalMaterialCostWithTVA", totalMaterialCostWithTVA);
    costDetails.put("totalPersonnelCost", totalPersonnelCost);
    costDetails.put("totalPersonnelCostWithTVA", totalPersonnelCostWithTVA);
    costDetails.put("totalCostBeforeMargin", totalCostBeforeMargin);
    costDetails.put("profitMargin", profitMargin);
    costDetails.put("finalTotalCost", finalTotalCost);
        return costDetails;
    }
}
