package org.baticuisine.Repositories.RepositoriesInterfaces;

import org.baticuisine.Models.Client;

import java.util.List;

public interface ClientRepositoryInterface {
    void save(Client client);      // Create/Update
    Client findById(int id);        // Read
    List<Client> findAll();         // Read All
    void delete(int id);
}
