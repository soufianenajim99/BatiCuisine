package org.baticuisine.Models;

public class Composant {
    protected int id;
    protected String nom;
    protected double tauxtva;
    protected Project project;
    public Composant() {}

    public Composant(String nom, double tauxtva, Project project) {
        this.nom = nom;
        this.tauxtva = tauxtva;
        this.project = project;
    }

    public Composant(int id, String nom, double tauxtva, Project project) {
        this.id = id;
        this.nom = nom;
        this.tauxtva = tauxtva;
        this.project = project;
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

    public double getTauxtva() {
        return tauxtva;
    }

    public void setTauxtva(double tauxtva) {
        this.tauxtva = tauxtva;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Composant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", tauxtva=" + tauxtva +
                ", project=" + project +
                '}';
    }
}
