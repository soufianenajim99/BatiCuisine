package org.baticuisine.GUI;

import org.baticuisine.Enums.EtatProject;
import org.baticuisine.Helpers.Helpers;
import org.baticuisine.Models.*;
import org.baticuisine.Services.ClientService;
import org.baticuisine.Services.ProjectService;
import org.baticuisine.Services.ServicesInterfaces.ClientServiceInterface;
import org.baticuisine.Services.ServicesInterfaces.ProjectServiceInterface;

import java.util.Scanner;

public class ProjectMenu {

    private Scanner scanner = new Scanner(System.in);
        private final ClientMenu clientMenu;
        private final MaterielMenu materielMenu;
        private final PersonnelMenu personnelMenu;
    private final ProjectServiceInterface projectService = new ProjectService();
        public ProjectMenu() {
            this.clientMenu = new ClientMenu();
            this.materielMenu = new MaterielMenu();
            this.personnelMenu = new PersonnelMenu();
        }

        public void createProject() {
            System.out.println("--- Création d'un Nouveau Projet ---");
            Client client = clientMenu.findOrCreateClient();
            if (client == null) {
                System.out.println("Erreur lors de la recherche ou de la création du client.");
                return;
            }

            System.out.print("Entrez le nom du projet : ");
            String projectName = scanner.nextLine();

            System.out.print("Entrez la surface de la cuisine  : ");
            double surface = scanner.nextDouble();

            Project project = new Project(projectName, surface, client);
            // Adding Materials
            project.setId(projectService.createProject(project).getId());
            materielMenu.addingMoreMateriels(project);
            personnelMenu.addingMorePersonels(project);
            System.out.println("Projet créé avec succès : " + projectName);
        }

        public void showProjects() {
            // For simplicity, display a static message. You can later integrate with a real project repository.
            System.out.println("--- Afficher les Projets Existant ---");
            System.out.println("Aucun projet à afficher pour le moment.");
        }

        public void calculateProjectCost(Project project) {
            System.out.println("--- Calcul du coût total ---");

            // Ask for VAT
            boolean applyTVA = Helpers.askYesNo("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
            double tvaPercentage = 0.0;
            if (applyTVA) {
                System.out.print("Entrez le pourcentage de TVA (%) : ");
                tvaPercentage = scanner.nextDouble();
                project.setTauxtva(tvaPercentage);
            }

            // Ask for profit margin
            boolean applyMargin = Helpers.askYesNo("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
            double marginPercentage = 0.0;
            if (applyMargin) {
                System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
                marginPercentage = scanner.nextDouble();
                project.setMargeBeneficiaire(marginPercentage);
            }

            // Calculating total material costs
            double totalMaterialCost = project.getMaterielsList().stream().mapToDouble(Materiel::calculateTotalCost).sum();
            double totalMaterialCostWithTVA = applyTVA ? totalMaterialCost * (1 + tvaPercentage / 100) : totalMaterialCost;

            // Calculating total labor costs
            double totalpersonelCost = project.getPersonnelList().stream().mapToDouble(Personnel::calculatepersonelCost).sum();
            double totalpersonelCostWithTVA = applyTVA ? totalpersonelCost * (1 + tvaPercentage / 100) : totalpersonelCost;

            // Total cost before profit margin
            double totalCostBeforeMargin = totalMaterialCost + totalpersonelCost;
            double totalCostWithTVA = totalMaterialCostWithTVA + totalpersonelCostWithTVA;

            // Applying profit margin
            double profitMargin = totalCostBeforeMargin * (marginPercentage / 100);
            double finalTotalCost = totalCostWithTVA + profitMargin;

            // Display the breakdown of costs
            displayCostBreakdown(project,tvaPercentage,marginPercentage, totalMaterialCost, totalMaterialCostWithTVA, totalpersonelCost, totalpersonelCostWithTVA, totalCostBeforeMargin, profitMargin, finalTotalCost);

            // Save the project devis
            saveDevis(project, finalTotalCost);
        }

    private void saveDevis(Project project, double finalTotalCost) {
        System.out.println("--- Enregistrement du Devis ---");
        System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        String dateEmission = scanner.next();
        System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
        String dateValidite = scanner.next();

        boolean save = Helpers.askYesNo("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        if (save) {
            project.setCoutTotal(finalTotalCost);
            project.setEtatProjet(EtatProject.TERMINE);
            Devis devis = new Devis(finalTotalCost,project,dateEmission,dateValidite,true);
            project.setDateEmission(dateEmission);
            project.setDateValidite(dateValidite);
            // Assuming projectService is handling the save operation.
            projectService.saveProject(project);
            System.out.println("Devis enregistré avec succès !");
        } else {
            System.out.println("Devis non enregistré.");
        }
    }




    private void displayCostBreakdown(Project project,double tvaPercentage,double marginPercentage, double totalMaterialCost, double totalMaterialCostWithTVA,
                                      double totalLaborCost, double totalLaborCostWithTVA, double totalCostBeforeMargin,
                                      double profitMargin, double finalTotalCost) {
        System.out.println("--- Résultat du Calcul ---");
        System.out.println("Nom du projet : " + project.getNomProjet());
        System.out.println("Client : " + project.getClient().getNom());
        System.out.println("Adresse du chantier : " + project.getClient().getAdresse());
        System.out.println("Surface : " + project.getSurface() + " m²");

        // Material Costs
        System.out.println("--- Détail des Coûts ---");
        System.out.println("1. Matériaux :");
        project.getMaterielsList().forEach(material -> {
            System.out.printf("- %s : %.2f € (quantité : %.2f, coût unitaire : %.2f €, qualité : %.1f, transport : %.2f €)%n",
                    material.getNom(), material.calculateTotalCost(), material.getQuantite(), material.getCoutUnitaire(),
                    material.getCoefficientQualite(), material.getCoutTransport());
        });
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**%n", totalMaterialCost);
        System.out.printf("**Coût total des matériaux avec TVA (%.1f%%) : %.2f €**%n", tvaPercentage, totalMaterialCostWithTVA);

        // Labor Costs
        System.out.println("2. Main-d'œuvre :");
        project.getPersonnelList().forEach(personnel -> {
            System.out.printf("- %s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %.2f h, productivité : %.1f)%n",
                    personnel.getNom(), personnel.calculatepersonelCost(), personnel.getTauxHoraire(), personnel.getHeuresTravail(),
                    personnel.getProductiviteOuvrier());
        });
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**%n", totalLaborCost);
        System.out.printf("**Coût total de la main-d'œuvre avec TVA (%.1f%%) : %.2f €**%n", tvaPercentage, totalLaborCostWithTVA);

        // Final Costs
        System.out.printf("3. Coût total avant marge : %.2f €%n", totalCostBeforeMargin);
        System.out.printf("4. Marge bénéficiaire (%.1f%%) : %.2f €%n", marginPercentage, profitMargin);
        System.out.printf("**Coût total final du projet : %.2f €**%n", finalTotalCost);
    }









}
