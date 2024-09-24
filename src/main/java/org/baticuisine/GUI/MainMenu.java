package org.baticuisine.GUI;

import java.util.Scanner;

public class MainMenu {


        private final ClientMenu clientMenu;
        private final ProjectMenu projectMenu;
        private Scanner scanner = new Scanner(System.in);

        public MainMenu() {

            this.clientMenu = new ClientMenu();
            this.projectMenu = new ProjectMenu();
        }

        public void display() {

            int choice;

            do {
                System.out.println("=== Menu Principal ===");
                System.out.println("1. Créer un nouveau projet");
                System.out.println("2. Afficher les projets existants");
                System.out.println("3. Calculer le coût d'un projet");
                System.out.println("4. Quitter");
                System.out.print("Choisissez une option : ");

                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        projectMenu.createProject();
                        break;
                    case 2:
                        projectMenu.showProjects();
                        break;
                    case 3:
                       projectMenu.showProjectsCost();
                        break;
                    case 4:
                        System.out.println("Merci d'avoir utilisé l'application.");
                        break;
                    default:
                        System.out.println("Option non valide, veuillez réessayer.");
                }
            } while (choice != 4);
        }


}
