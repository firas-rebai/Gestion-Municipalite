package com.pfa.modules.Personnel;

public class Personnel {
    private int id;
    private String nom;
    private String prenom;
    private int CIN;
    private String email;
    private int numTel;
    private float salaire;
    private String poste;
    private String equip;
    private String dateNaissaince;

    public Personnel(int id, String nom, String prenom, int CIN, String email, int numTel, float salaire, String poste, String equip, String dateNaissaince) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.email = email;
        this.numTel = numTel;
        this.salaire = salaire;
        this.poste = poste;
        this.equip = equip;
        this.dateNaissaince = dateNaissaince;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getDateNaissaince() {
        return dateNaissaince;
    }

    public void setDateNaissaince(String dateNaissaince) {
        this.dateNaissaince = dateNaissaince;
    }
}
