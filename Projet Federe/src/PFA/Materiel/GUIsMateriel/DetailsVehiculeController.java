package PFA.Materiel.GUIsMateriel;

import Materiel.ModuleMateriel.MaterielVehicule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class DetailsVehiculeController {
    @FXML
    Label lbid,lbmodel,lbnom,lbquantité,lbmatricule,lbprix,lbdate;
    @FXML
    Button retour;

    public void retour3() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }

    public void initData(MaterielVehicule p) {
        lbnom.setText(p.getNom());
        lbmodel.setText(p.getModel());
        lbdate.setText(p.getDateDachat());
        lbid.setText(String.valueOf(p.getId()));
        lbprix.setText(String.valueOf(p.getPrix()));
        lbquantité.setText(String.valueOf(p.getQuantité()));
        lbmatricule.setText(String.valueOf(p.getMatricule()));

    }

}
