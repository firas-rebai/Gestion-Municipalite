package PFA.GestionIntervention.Modules;

import java.util.Date;

public class Intervention {
    private int id;
    private String nom;
    private Date dateBedut;
    private Date dateFin;
    private float budget;
    
    public Intervention(String nom, Date dateBedut, Date dateFin, float budget) {
        this.nom = nom;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.budget = budget;
    }
    
    public Intervention(int id, String nom, Date dateBedut, Date dateFin, float budget) {
        this.id = id;
        this.nom = nom;
        this.dateBedut = dateBedut;
        this.dateFin = dateFin;
        this.budget = budget;
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
    
    @Override
    public String toString() {
        return "Intervention{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateBedut=" + dateBedut +
                ", dateFin=" + dateFin +
                ", budget=" + budget +
                '}';
    }
}
