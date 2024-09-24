package org.baticuisine.Services.ServicesInterfaces;
import org.baticuisine.Models.Personnel;
import org.baticuisine.Models.Project;

import java.util.List;
import java.util.Optional;
public interface PersonnelServiceInterface {
    Personnel addPersonnel(Personnel personnel);
    Optional<Personnel> getPersonnelById(int id);
    List<Personnel> getAllPersonnels();
    void removePersonnel(int id);
}
