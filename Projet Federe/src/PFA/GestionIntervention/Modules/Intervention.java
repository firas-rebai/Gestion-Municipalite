package PFA.GestionIntervention.Modules;

import PFA.GestionPersonnel.Modules.Personnel;

import java.util.ArrayList;
import java.util.Date;

public class Intervention {
    private int id;
    private String nom;
    private Date dateBedut;
    private Date dateFin;
    private float budget;
    private String adresse;
    private ArrayList<Personnel> equipe;
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
    
    public Date getDateBedut() {
        return dateBedut;
    }
    
    public void setDateBedut(Date dateBedut) {
        this.dateBedut = dateBedut;
    }
    
    public Date getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public float getBudget() {
        return budget;
    }
    
    public void setBudget(float budget) {
        this.budget = budget;
    }
    
    
}
