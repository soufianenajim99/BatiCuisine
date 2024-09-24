package org.baticuisine.GUI;

import org.baticuisine.Models.Materiel;
import org.baticuisine.Models.Personnel;
import org.baticuisine.Models.Project;
import org.baticuisine.Services.MaterielService;
import org.baticuisine.Services.PersonnelService;
import org.baticuisine.Services.ServicesInterfaces.MaterielServiceInterface;
import org.baticuisine.Services.ServicesInterfaces.PersonnelServiceInterface;

import java.util.Scanner;

public class PersonnelMenu {
    private static Scanner scanner = new Scanner(System.in);
    private final PersonnelServiceInterface personnelService = new PersonnelService();

    public void addingMorePersonels(Project project){
        while (true) {
            System.out.println("--- Ajout des personnels ---");
            System.out.print("Entrez le nom du personnel : ");
            String personnelName = scanner.nextLine();

            System.out.print("Entrez le taux horraire du personnel : ");
            double tauxHoraire = scanner.nextDouble();


            System.out.print("Entrez les heures de travails du personnel : : ");
            double heuresTravail = scanner.nextDouble();

            System.out.print("Entrez la productivite de l'ouvrier : ");
            double productiviteOuvrier = scanner.nextDouble();

            scanner.nextLine();  // consume newline

            Personnel personnel = new Personnel(personnelName,project,tauxHoraire,heuresTravail,productiviteOuvrier);
            personnel.setId(personnelService.addPersonnel(personnel).getId());
           project.addPersonnel(personnel);

            System.out.println("Personnel ajouté avec succès !");
            System.out.print("Voulez-vous ajouter un autre personnel ? (y/n) : ");
            String addMoreMaterial = scanner.nextLine();
            if (addMoreMaterial.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

}
