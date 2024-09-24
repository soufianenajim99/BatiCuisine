package org.baticuisine.Models;

public class Personnel extends Composant{
    private double tauxHoraire;
    private double heuresTravail;
    private double productiviteOuvrier;

    public Personnel() {
    }


    public Personnel(String nom, Project project, double tauxHoraire, double heuresTravail, double productiviteOuvrier) {
        super(nom, project);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public Personnel(int id, String nom, Project project, double tauxHoraire, double heuresTravail, double productiviteOuvrier) {
        super(id, nom, project);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public double calculatepersonelCost() {
        return tauxHoraire*productiviteOuvrier*heuresTravail;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", project=" + project +
                "tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                '}';
    }
}
