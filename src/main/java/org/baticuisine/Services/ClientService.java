package org.baticuisine.Services;

import org.baticuisine.Models.Client;
import org.baticuisine.Repositories.ClientRepository;
import org.baticuisine.Services.ServicesInterfaces.ClientServiceInterface;

import java.util.List;
import java.util.Optional;

public class ClientService implements ClientServiceInterface {
    private ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientByName(String name){
        return clientRepository.findByName(name);
    }

    public void removeClient(int id) {
        clientRepository.delete(id);
    }
}
