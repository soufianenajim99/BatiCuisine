package org.baticuisine.GUI;

import org.baticuisine.Models.Materiel;
import org.baticuisine.Models.Project;
import org.baticuisine.Services.MaterielService;
import org.baticuisine.Services.ServicesInterfaces.MaterielServiceInterface;

import java.util.Scanner;

public class MaterielMenu {
    private static Scanner scanner = new Scanner(System.in);
    private final MaterielServiceInterface materielService = new MaterielService();

public void addingMoreMateriels(Project project){
    while (true) {
        System.out.println("--- Ajout des matériaux ---");
        System.out.print("Entrez le nom du matériau : ");
        String materialName = scanner.nextLine();

        System.out.print("Entrez la quantité de ce matériau (en m² ou litres) : ");
        double quantity = scanner.nextDouble();


        System.out.print("Entrez le coût unitaire de ce matériau (€) : ");
        double unitCost = scanner.nextDouble();

        System.out.print("Entrez le coût de transport de ce matériau (€) : ");
        double transportCost = scanner.nextDouble();

        System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
        double qualityFactor = scanner.nextDouble();
        scanner.nextLine();  // consume newline

        Materiel material = new Materiel(materialName,project, unitCost,quantity , transportCost, qualityFactor);
        material.setId(materielService.addMateriel(material).getId());
        project.addMateriel(material);
        System.out.println("Matériau ajouté avec succès !");
        System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
        String addMoreMaterial = scanner.nextLine();
        if (addMoreMaterial.equalsIgnoreCase("n")) {
            break;
        }
    }
}
}
