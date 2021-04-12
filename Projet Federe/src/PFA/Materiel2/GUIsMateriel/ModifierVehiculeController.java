package PFA.Materiel2.GUIsMateriel;

import Materiel.ModuleMateriel.MaterielVehicule;
import Materiel.ServiceMateriel.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Clob;
import java.sql.Date;
import java.time.LocalDate;
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




        if (!Pattern.matches(matriculePattern, tfmatricule.getText())|| tfmatricule.getText().isEmpty()) {
            lbmatricule.setVisible(true);
        }else{
            lbmatricule.setVisible(false);
        }



        if (verif  &&
                Pattern.matches(prixPattern, tfprix.getText())&&
                Pattern.matches(nomPattern, tfnom.getText())
                && Pattern.matches(modelPattern, tfmodel.getText()) &&
                Pattern.matches(matriculePattern, tfmatricule.getText()) && event.getSource()==btnmodifier) {
             //String d = lblbb.getText();
             //java.sql.Date sqlDate = Date.valueOf(d);
           // String i= datedate.getValue().toString();
            MaterielVehicule p = new MaterielVehicule(Integer.parseInt(lbloo.getText()),
                    Integer.parseInt(tfmatricule.getText()),tfmodel.getText(),
                    Double.parseDouble(tfprix.getText())
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
        lbloo.setText(String.valueOf(p.getId()));

      //String date = p.getDateDachat().toString();

        lblbb.setText(p.getDateDachat());
    }

    public void retour2() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();

    }

}
