package PFA.GestionTache.Module;

import PFA.GestionPersonnel.Modules.Personnel;

public class Tache {
    private int id;
    private String nom;
    private String description;
    private Personnel personnel;
    
    public Tache(String nom, String description, Personnel personnel) {
        this.nom = nom;
        this.description = description;
        this.personnel = personnel;
    }
    
    public Tache(int id, String nom, String description, Personnel personnel) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.personnel = personnel;
    }
    
    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", personnel=" + personnel +
                '}';
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Personnel getPersonnel() {
        return personnel;
    }
    
    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
    
    
}
