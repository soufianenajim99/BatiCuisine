package org.baticuisine.GUI;

import org.baticuisine.Models.Client;
import org.baticuisine.Services.ClientService;
import org.baticuisine.Services.ServicesInterfaces.ClientServiceInterface;

import java.util.Optional;
import java.util.Scanner;

public class ClientMenu {
    private Scanner scanner = new Scanner(System.in);
    private ClientServiceInterface clientService = new ClientService();
        public Client findOrCreateClient() {

            System.out.println("--- Recherche de client ---");
            System.out.println("1. Chercher un client existant");
            System.out.println("2. Ajouter un nouveau client");
            System.out.print("Choisissez une option : ");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                return searchExistingClient().get();
            } else if (option == 2) {
                return createNewClient();
            } else {
                System.out.println("Option non valide.");
                return null;
            }
        }

        private Optional<Client> searchExistingClient() {

            System.out.print("Entrez le nom du client : ");
            String name = scanner.nextLine();

            Optional<Client> clientS=clientService.getClientByName(name);
            if(clientS.isPresent()){
                System.out.println("Client trouvé : " + clientS.get());
            }else{
                System.out.println("Client non trouvé :/ ");
            }
            return clientS;
        }

        private Client createNewClient() {

            System.out.print("Entrez le nom du client : ");
            String name = scanner.nextLine();
            System.out.print("Entrez l'adresse du client : ");
            String address = scanner.nextLine();
            System.out.print("Entrez le numéro de téléphone du client : ");
            String phone = scanner.nextLine();
            System.out.print("Vous etes Professionel (n/o) : ");
            String pro = scanner.nextLine();
            boolean estpro;
            if (pro == "n"){
                estpro = false;
            }else {
                estpro = true;
            }
            Client client = new Client(name, address, phone,estpro);
            client.setId(clientService.addClient(client).getId());
            System.out.println("Client ajouté avec succès !");
            return client;
        }









}
