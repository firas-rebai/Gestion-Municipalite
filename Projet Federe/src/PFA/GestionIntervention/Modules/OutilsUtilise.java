package PFA.GestionIntervention.Modules;

import PFA.Materiel.ModuleMateriel.Outil;

public class OutilsUtilise {
    public OutilsUtilise(Outil outils, int quantite) {
        this.outils = outils;
        this.quantite = quantite;
    }
    
    public Outil outils;
    public int quantite;
}
