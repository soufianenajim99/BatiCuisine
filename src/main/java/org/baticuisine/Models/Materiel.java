package org.baticuisine.Models;

public class Materiel extends Composant{
    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;
    public Materiel() {}

    public Materiel(String nom, Project project, double coutUnitaire, double quantite, double coutTransport, double coefficientQualite) {
        super(nom, project);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public Materiel(int id, String nom, Project project, double coutUnitaire, double quantite, double coutTransport, double coefficientQualite) {
        super(id, nom, project);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    public double calculateTotalCost() {
        return (quantite * coutUnitaire * coefficientQualite) + coutTransport;
    }
    @Override
    public String toString() {
        return "Materiel{" +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", project=" + project +
                "coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                '}';
    }
}
