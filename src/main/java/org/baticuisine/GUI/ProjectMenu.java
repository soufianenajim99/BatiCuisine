package org.baticuisine.GUI;

import org.baticuisine.Models.Client;
import org.baticuisine.Models.Project;
import org.baticuisine.Services.ClientService;
import org.baticuisine.Services.ProjectService;
import org.baticuisine.Services.ServicesInterfaces.ClientServiceInterface;
import org.baticuisine.Services.ServicesInterfaces.ProjectServiceInterface;

import java.util.Scanner;

public class ProjectMenu {

    private Scanner scanner = new Scanner(System.in);
        private final ClientMenu clientMenu;
    private ProjectServiceInterface projectService = new ProjectService();
        public ProjectMenu() {
            this.clientMenu = new ClientMenu();
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
            projectService.createProject(project,client);
            System.out.println("Projet créé avec succès : " + projectName);
        }

        public void showProjects() {
            // For simplicity, display a static message. You can later integrate with a real project repository.
            System.out.println("--- Afficher les Projets Existant ---");
            System.out.println("Aucun projet à afficher pour le moment.");
        }

        public void calculateProjectCost() {
            System.out.println("--- Calcul du coût d'un projet ---");
            System.out.println("Calcul non implémenté.");
        }







}
