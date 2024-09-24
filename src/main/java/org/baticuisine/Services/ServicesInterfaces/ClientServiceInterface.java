package org.baticuisine.Services.ServicesInterfaces;

import org.baticuisine.Models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServiceInterface {
    void addClient(Client client);
    Client getClientById(int id);
    List<Client> getAllClients();
    Optional<Client> getClientByName(String name);
    void removeClient(int id);
}
