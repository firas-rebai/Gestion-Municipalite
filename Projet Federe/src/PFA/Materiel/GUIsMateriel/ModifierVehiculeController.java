package PFA.Materiel.GUIsMateriel;

import Materiel.ModuleMateriel.MaterielVehicule;
import Materiel.ServiceMateriel.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class ModifierVehiculeController {
    @FXML
    TextField tfid, tfmodel, tfmatricule, tfquantité, tfprix, tfnom;
    @FXML
    Button btnmodifier;
    @FXML
    DatePicker datedate;
    @FXML
    Button retour;
    @FXML
    Label lbid, lbprix,lbmodel,lbnom,lbmatricule,lbquantité,lbdate,lblbb,lbloo;

    String idPattern = "[0-9]*";
    String prixPattern="[0-9]*.[0-9]*";
    String nomPattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String modelPattern = "[a-zA-Z0-9-_]*[a-zA-Z0-9-_ ]*";
    String quantitéPattern = "[0-9]*";
    String matriculePattern = "[0-9]{4}";

    boolean verif = true;

    public void ModifierButton(ActionEvent event) throws IOException {





        if (!Pattern.matches(prixPattern, tfprix.getText()) || tfprix.getText().isEmpty()) {
            lbprix.setVisible(true);
        }else{
            lbprix.setVisible(false);
        }





        if (!Pattern.matches(nomPattern, tfnom.getText())|| tfnom.getText().isEmpty()) {
            lbnom.setVisible(true);
        }else{
            lbnom.setVisible(false);
        }



        if (!Pattern.matches(modelPattern, tfmodel.getText()) || tfmodel.getText().isEmpty()) {
            lbmodel.setVisible(true);
        }else{
            lbmodel.setVisible(false);
        }



        if (!Pattern.matches(quantitéPattern, tfquantité.getText()) || tfquantité.getText().isEmpty()) {
            lbquantité.setVisible(true);
        }else{
            lbquantité.setVisible(false);
        }


        if (!Pattern.matches(matriculePattern, tfmatricule.getText())|| tfmatricule.getText().isEmpty()) {
            lbmatricule.setVisible(true);
        }else{
            lbmatricule.setVisible(false);
        }



        if (verif  &&
                Pattern.matches(prixPattern, tfprix.getText())&&
                Pattern.matches(nomPattern, tfnom.getText())
                && Pattern.matches(modelPattern, tfmodel.getText()) &&
                Pattern.matches(quantitéPattern, tfquantité.getText()) &&
                Pattern.matches(matriculePattern, tfmatricule.getText()) && event.getSource()==btnmodifier) {
            MaterielVehicule p = new MaterielVehicule(Integer.parseInt(lbloo.getText()),
                    Integer.parseInt(tfmatricule.getText()),tfmodel.getText(),
                    Double.parseDouble(tfprix.getText()),Integer.parseInt(tfquantité.getText())
                    ,tfnom.getText(),lblbb.getText() );
            Vehicule.Modifier(p);
            Stage stage = (Stage) btnmodifier.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés de modification");
            alert.setHeaderText(null);
            alert.setContentText("modification faite avec succés");
            alert.show();

        }

    }
private int id;
    public void initData(MaterielVehicule p) {
        tfnom.setText(p.getNom());
        tfmodel.setText(p.getModel());
        tfprix.setText(String.valueOf(p.getPrix()));
        tfmatricule.setText(String.valueOf(p.getMatricule()));
        tfquantité.setText(String.valueOf(p.getQuantité()));
        lbloo.setText(String.valueOf(p.getId()));
        lblbb.setText(p.getDateDachat());
    }

    public void retour2() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();

    }

}
