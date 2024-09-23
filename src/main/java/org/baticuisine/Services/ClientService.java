package org.baticuisine.Services;

import org.baticuisine.Models.Client;
import org.baticuisine.Repositories.ClientRepository;

import java.util.List;

public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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

    public void removeClient(int id) {
        clientRepository.delete(id);
    }
}
