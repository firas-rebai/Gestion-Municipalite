package PFA.Materiel2.ModuleMateriel;

public class MaterielOutils {
    private int id;
    private int quantité;
    private Double prix;
    private  String nom;

    public MaterielOutils(int id, int quantité, Double prix, String nom) {
        this.id = id;
        this.quantité = quantité;
        this.prix = prix;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "MaterielOutils{" +
                "idOutils=" + id +
                ", quantitéOutils=" + quantité +
                ", prixOutils=" + prix +
                ", nomOutils='" + nom + '\'' +
                '}';
    }
}

