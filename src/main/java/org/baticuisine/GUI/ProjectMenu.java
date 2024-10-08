package org.baticuisine.GUI;

import org.baticuisine.Enums.EtatProject;
import org.baticuisine.Helpers.Helpers;
import org.baticuisine.Models.*;
import org.baticuisine.Services.ClientService;
import org.baticuisine.Services.DevisService;
import org.baticuisine.Services.ProjectService;
import org.baticuisine.Services.ServicesInterfaces.ClientServiceInterface;
import org.baticuisine.Services.ServicesInterfaces.DevisServiceInterface;
import org.baticuisine.Services.ServicesInterfaces.ProjectServiceInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProjectMenu {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Scanner scanner = new Scanner(System.in);
        private final ClientMenu clientMenu;
        private final MaterielMenu materielMenu;
        private final PersonnelMenu personnelMenu;
    private final ProjectServiceInterface projectService = new ProjectService();
    private final DevisServiceInterface devisService = new DevisService();
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
            calculateProjectCost(project);
        }

        public void showProjects() {
       
            System.out.println("--- Afficher les Projets Existant ---");
            for (Project project : projectService.getAllProjects()) {
                System.out.println(project);
            }

        }
        public void showProjectsCost() {
            System.out.println("--- Afficher les Projets Existants ---");
            List<Project> projects = projectService.getAllProjects();
            for (int i = 0; i < projects.size(); i++) {
                System.out.println((i + 1) + ". " + projects.get(i).getNomProjet());
            }

            System.out.print("Choisissez un projet pour voir les détails (1-" + projects.size() + ") : ");
            int choice = scanner.nextInt();
            if (choice < 1 || choice > projects.size()) {
                System.out.println("Choix non valide.");
                return;
            }

            Project selectedProject = projects.get(choice - 1);
            System.out.printf("**Coût total du projet '%s' : %.2f €**%n", selectedProject.getNomProjet(), selectedProject.getCoutTotal());

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
            projectService.createProject(project);

            Map<String, Double> costDetails = devisService.calculateTotalCost(project, tvaPercentage, marginPercentage);


            // Display the breakdown of costs

            displayCostBreakdown(project,tvaPercentage,marginPercentage, costDetails.get("totalMaterialCost"),  costDetails.get("totalMaterialCostWithTVA"),
                    costDetails.get("totalPersonnelCost"), costDetails.get("totalPersonnelCostWithTVA"),
                    costDetails.get("totalCostBeforeMargin"), costDetails.get("profitMargin"), costDetails.get("finalTotalCost"));

            // Save the project devis
            saveDevis(project, costDetails.get("finalTotalCost"));
        }

    private void saveDevis(Project project, double finalTotalCost) {
        System.out.println("--- Enregistrement du Devis ---");
        System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        String dateEmission = scanner.next();
        LocalDate emissionDate = LocalDate.parse(dateEmission, formatter);
        System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
        String dateValidite = scanner.next();
        LocalDate validiteDate = LocalDate.parse(dateValidite, formatter);


        boolean save = Helpers.askYesNo("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        if (save) {
            project.setCoutTotal(finalTotalCost);
            project.setEtatProjet(EtatProject.TERMINE);
            Devis devis = new Devis(finalTotalCost,project,emissionDate,validiteDate,true);

            projectService.createProject(project);
            devisService.createDevis(devis);
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

        // Personnel Costs
        System.out.println("2. Personnel :");
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
