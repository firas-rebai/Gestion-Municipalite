package PFA.GestionIntervention.Modules;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.Materiel.ServiceMateriel.Vehicules;

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
    private ArrayList<Vehicules> vehicules;
    private ArrayList<OutilsUtilise> outilsUtilises;
    
    public Intervention(String nom, Date dateBedut, Date dateFin, float budget, String adresse, ArrayList<Personnel> equipe, ArrayList<Vehicules> vehicules, ArrayList<OutilsUtilise> outilsUtilises) {
        this.nom = nom;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.budget = budget;
        this.adresse = adresse;
        this.equipe = equipe;
        this.vehicules = vehicules;
        this.outilsUtilises = outilsUtilises;
    }
    
    public Intervention(int id, String nom, Date dateBedut, Date dateFin, float budget, String adresse, ArrayList<Personnel> equipe, ArrayList<Vehicules> vehicules, ArrayList<OutilsUtilise> outilsUtilises) {
        this.id = id;
        this.nom = nom;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.budget = budget;
        this.adresse = adresse;
        this.equipe = equipe;
        this.vehicules = vehicules;
        this.outilsUtilises = outilsUtilises;
    }
    
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
    
    public ArrayList<Personnel> getEquipe() {
        return equipe;
    }
    
    public void setEquipe(ArrayList<Personnel> equipe) {
        this.equipe = equipe;
    }
    
    public ArrayList<Vehicules> getVehicules() {
        return vehicules;
    }
    
    public void setVehicules(ArrayList<Vehicules> vehicules) {
        this.vehicules = vehicules;
    }
    
    public ArrayList<OutilsUtilise> getOutilsUtilises() {
        return outilsUtilises;
    }
    
    public void setOutilsUtilises(ArrayList<OutilsUtilise> outilsUtilises) {
        this.outilsUtilises = outilsUtilises;
    }
}


