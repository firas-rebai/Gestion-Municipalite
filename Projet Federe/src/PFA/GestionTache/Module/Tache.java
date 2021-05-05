package PFA.GestionTache.Module;

import PFA.GestionPersonnel.Modules.Personnel;
import javafx.scene.control.CheckBox;

public class Tache {
    private int id;
    private String nom;
    private String description;
    private Personnel personnel;
    private String nomPersonnel = "Non Disponible";
    private CheckBox check;
    
    public CheckBox getCheck() {
        return check;
    }
    
    public void setCheck(CheckBox check) {
        this.check = check;
    }
    
    public String getNomPersonnel() {
        return nomPersonnel;
    }
    
    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }
    
    public Tache(String nom, String description, Personnel personnel) {
        this.nom = nom;
        this.description = description;
        this.personnel = personnel;
        if(!(personnel == null)) nomPersonnel = personnel.getNom();
    }
    
    public Tache(int id, String nom, String description, Personnel personnel) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.personnel = personnel;
        if(!(personnel == null)) nomPersonnel = personnel.getNom();
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
