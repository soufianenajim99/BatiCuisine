package org.baticuisine.Models;

import java.time.LocalDate;

public class Devis {
    private int id;
    private double montantEstime;
    private LocalDate startDate;
    private LocalDate dateValidite;
    private Project project;
    private boolean accepte;
    public Devis() {
    }

    public Devis(double montantEstime,Project project, LocalDate startDate, LocalDate dateValidite, boolean accepte) {
        this.montantEstime = montantEstime;
        this.startDate = startDate;
        this.dateValidite = dateValidite;
        this.project = project;
        this.accepte = accepte;
    }

    public Devis(int id, double montantEstime,Project project, LocalDate startDate, LocalDate dateValidite, boolean accepte) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.startDate = startDate;
        this.dateValidite = dateValidite;
        this.project = project;
        this.accepte = accepte;
    }

    public int getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(LocalDate dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", montantEstime=" + montantEstime +
                ", startDate=" + startDate +
                ", dateValidite=" + dateValidite +
                ", accepte=" + accepte +
                '}';
    }
}
