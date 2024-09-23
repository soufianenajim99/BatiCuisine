package org.baticuisine;

import org.baticuisine.Models.Client;
import org.baticuisine.Repositories.ClientRepository;
import org.baticuisine.Services.ClientService;
import org.baticuisine.Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {


        ClientRepository clientRepository = new ClientRepository();
        ClientService clientService = new ClientService(clientRepository);

        // Add a new client
        Client newClient = new Client("John Doe", "123 Elm Street", "555-1234", false);
        clientService.addClient(newClient);

        // Retrieve all clients
        List<Client> clients = clientService.getAllClients();
        clients.forEach(System.out::println);

        // Get client by ID
        Client client = clientService.getClientById(1);
        System.out.println(client);

        // Delete a client by ID
        clientService.removeClient(1);



}
}