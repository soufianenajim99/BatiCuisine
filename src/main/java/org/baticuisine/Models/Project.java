package org.baticuisine.Models;

import org.baticuisine.Enums.EtatProject;

import java.util.ArrayList;

public class Project {
    private int id;
    private String nomProjet;
    private double surface;
    private double margeBeneficiaire;
    private double coutTotal;
    private EtatProject etatProjet;
    private Client client;
    private ArrayList<Materiel> materielsList;
    private ArrayList<Personnel> personnelList;
    private Devis devis;

    public Project() {
        this.materielsList = new ArrayList<>();
        this.personnelList = new ArrayList<>();
    }

    public Project(String nomProjet, double surface, double margeBeneficiaire, double coutTotal, EtatProject etatProjet, Client client, Devis devis) {
        this();
        this.nomProjet = nomProjet;
        this.surface = surface;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
        this.devis = devis;
    }

    public Project(int id, String nomProjet, double margeBeneficiaire, double coutTotal,EtatProject etatProjet,double surface) {
        this();
        this.id = id;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.surface = surface;
    }

    public Project(int id, String nomProjet, double margeBeneficiaire,double coutTotal,EtatProject etatProjet,double surface,   Client client) {
        this();
        this.id = id;
        this.nomProjet = nomProjet;
        this.surface = surface;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProject getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(EtatProject etatProjet) {
        this.etatProjet = etatProjet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }
    public void addMateriel(Materiel materiel) {
        this.materielsList.add(materiel);
    }
    public void removeMateriel(Materiel materiel) {
        this.materielsList.remove(materiel);
    }
    public ArrayList<Materiel> getMaterielsList() {
        return materielsList;
    }
    public void setMaterielsList(ArrayList<Materiel> materielsList) {
        this.materielsList = materielsList;
    }
    public void addPersonnel(Personnel personnel) {
        this.personnelList.add(personnel);
    }
    public void removePersonnel(Personnel personnel) {
        this.personnelList.remove(personnel);
    }
    public ArrayList<Personnel> getPersonnelList() {
        return personnelList;
    }
    public void setPersonnelList(ArrayList<Personnel> personnelList) {
        this.personnelList = personnelList;
    }

    @Override
    public String toString() {
        String result =
                "Project{" +
                        "id=" + id +
                        ", nomProjet='" + nomProjet + '\'' +
                        ", surface=" + surface +
                        ", margeBeneficiaire=" + margeBeneficiaire +
                        ", coutTotal=" + coutTotal +
                        ", etatProjet=" + etatProjet +
                        ", client=" + client;


        result += ", has " + materielsList.size() + " Materials";
        for (int i = 0; i < materielsList.size(); i++) {
            result += "\n  Material " + (i + 1) + ": " + materielsList.get(i).getNom();
        }


        result += ", has " + personnelList.size() + " Personnel";
        for (int i = 0; i < personnelList.size(); i++) {
            result += "\n  Personnel " + (i + 1) + ": " + personnelList.get(i).getNom();
        }

        result += ", devis=" + devis +
                "}";

        return result;
    }
}
