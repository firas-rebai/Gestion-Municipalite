package PFA.GestionPersonnel.GUIs;


import PFA.GestionPersonnel.Modules.Personnel;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class DetailsController{

    @FXML
    Label IDD,nomD,prenomD,dateD,posteD,equipD,emailD,salaireD,CIND,numD;
    @FXML
    Button retour;
    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }


    public void initData(Personnel p) {
        nomD.setText(p.getNom());
        prenomD.setText(p.getPrenom());
        emailD.setText(p.getEmail());
        IDD.setText(String.valueOf(p.getId()));
        salaireD.setText(String.valueOf(p.getSalaire()));
        posteD.setText(p.getPoste());
        equipD.setText(p.getEquip());
        dateD.setText(p.getDateNaissaince());
        numD.setText(String.valueOf(p.getNumTel()));
        CIND.setText(String.valueOf(p.getCIN()));
    }

}
