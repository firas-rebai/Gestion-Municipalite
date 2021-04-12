package PFA.Materiel2.ModuleMateriel;

import java.util.Date;
import java.time.LocalDate;
public class MaterielVehicule {
    private int id;
    private int matricule;
    private String model;
    private double prix ;
    private String nom;
    private String date;

    public MaterielVehicule(int id, int matricule, String model, double prix, String nom, String datedachat) {
        this.id = id;
        this.matricule = matricule;
        this.model = model;
        this.prix = prix;
        this.nom = nom;
        this.date = datedachat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateDachat() {
        return date;
    }

    public void setdatedachat(String datedachat) {
        this.date = datedachat;
    }

    @Override
    public String toString() {
        return "MaterielVehicule{" +
                "id=" + id +
                ", matricule=" + matricule +
                ", model='" + model + '\'' +
                ", prix=" + prix +
                ", nom='" + nom + '\'' +
                ", DateDachat='" + date + '\'' +
                '}';
    }
}
