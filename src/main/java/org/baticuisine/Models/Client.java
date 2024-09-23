package org.baticuisine.Models;

import java.util.ArrayList;

public class Client {
    private int id;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean estProfessionnel;
    private ArrayList<Project> projectsList;

    public Client(String nom, String adresse, String telephone, boolean estProfessionnel) {
        this();
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estProfessionnel = estProfessionnel;
    }

    public Client(int id, String nom, String adresse, String telephone, boolean estProfessionnel) {
        this();
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estProfessionnel = estProfessionnel;
    }

    public Client() {
        this.projectsList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isEstProfessionnel() {
        return estProfessionnel;
    }

    public void setEstProfessionnel(boolean estProfessionnel) {
        this.estProfessionnel = estProfessionnel;
    }

    public ArrayList<Project> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(ArrayList<Project> projectsList) {
        this.projectsList = projectsList;
    }
    public void addProject(Project project) {
        this.projectsList.add(project);
    }
    public void removeProject(Project project){
        this.projectsList.remove(project);
    }

    @Override
    public String toString() {
        String result =
         "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", estProfessionnel=" + estProfessionnel +

                 ", has " + projectsList.size() +" Projects";
        for(int i=0; i< projectsList.size(); i++){
            result = result + "\n Project "+(i+1)+" name :  "+projectsList.get(i).getNomProjet();
        };
        result = result + "}";
        return result ;
    }
}
