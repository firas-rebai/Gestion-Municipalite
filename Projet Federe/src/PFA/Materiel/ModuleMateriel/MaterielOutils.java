package PFA.Materiel.ModuleMateriel;

public class MaterielOutils {
    private int idOutils;
    private int quantitéOutils;
    private Double prixOutils;
    private  String nomOutils;

    public MaterielOutils(int idOutils, int quantitéOutils, Double prixOutils, String nomOutils) {
        this.idOutils = idOutils;
        this.quantitéOutils = quantitéOutils;
        this.prixOutils = prixOutils;
        this.nomOutils = nomOutils;
    }

    public int getIdOutils() {
        return idOutils;
    }

    public void setIdOutils(int idOutils) {
        this.idOutils = idOutils;
    }

    public int getQuantitéOutils() {
        return quantitéOutils;
    }

    public void setQuantitéOutils(int quantitéOutils) {
        this.quantitéOutils = quantitéOutils;
    }

    public Double getPrixOutils() {
        return prixOutils;
    }

    public void setPrixOutils(Double prixOutils) {
        this.prixOutils = prixOutils;
    }

    public String getNomOutils() {
        return nomOutils;
    }

    public void setNomOutils(String nomOutils) {
        this.nomOutils = nomOutils;
    }

    @Override
    public String toString() {
        return "MaterielOutils{" +
                "idOutils=" + idOutils +
                ", quantitéOutils=" + quantitéOutils +
                ", prixOutils=" + prixOutils +
                ", nomOutils='" + nomOutils + '\'' +
                '}';
    }
}
