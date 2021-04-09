package PFA.GestionEquipe.Modules;

public class Equipe {
    private String nom;
    private int ChefId;
    private int id;
    private String specialite;

    public Equipe(String nom, int chef, int id, String specialite) {
        this.nom = nom;
        ChefId = chef;
        this.id = id;
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Equip{" +
                "nom='" + nom + '\'' +
                ", Chef=" + ChefId +
                ", id=" + id +
                ", specialite='" + specialite + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getChef() {
        return ChefId;
    }

    public void setChef(int chef) {
        ChefId = chef;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
