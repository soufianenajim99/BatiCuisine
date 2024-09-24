package org.baticuisine.Models;

public class Composant {
    protected int id;
    protected String nom;

    protected Project project;
    public Composant() {}

    public Composant(String nom, Project project) {
        this.nom = nom;
        this.project = project;
    }

    public Composant(int id, String nom, Project project) {
        this.id = id;
        this.nom = nom;
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
                ", project=" + project +
                '}';
    }
}
