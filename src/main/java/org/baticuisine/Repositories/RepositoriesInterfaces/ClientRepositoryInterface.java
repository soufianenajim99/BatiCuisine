package org.baticuisine.Repositories.RepositoriesInterfaces;

import org.baticuisine.Models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepositoryInterface {
    void save(Client client);      // Create/Update
    Client findById(int id);        // Read
    Optional<Client> findByName(String name);
    List<Client> findAll();         // Read All
    void delete(int id);
}
