package PFA.MaterielFiras.GUIsMateriel;

import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import PFA.MaterielFiras.ServiceMateriel.Vehicules;
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
    String matriculePattern = "[0-9]{4}";

    boolean verif = true;

    public void ModifierButton(ActionEvent event) throws IOException {tfnom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
        if (!newPropertyValue) {
            lbnom.setVisible(!Pattern.matches(nomPattern, tfnom.getText())|| tfnom.getText().isEmpty());
        }
    });
    
        tfmodel.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                tfmodel.setVisible(!Pattern.matches(modelPattern, tfmodel.getText()) || tfmodel.getText().isEmpty());
            }
        });
    
    
        tfmatricule.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbmatricule.setVisible(!Pattern.matches(matriculePattern, tfmatricule.getText())|| tfmatricule.getText().isEmpty());
            }
        });

        if (verif  &&
                Pattern.matches(nomPattern, tfnom.getText())
                && Pattern.matches(modelPattern, tfmodel.getText()) &&
                Pattern.matches(matriculePattern, tfmatricule.getText()) && event.getSource()==btnmodifier) {
             //String d = lblbb.getText();
             //java.sql.Date sqlDate = Date.valueOf(d);
           // String i= datedate.getValue().toString();
            Vehicule p = new Vehicule(id,
                    Integer.parseInt(tfmatricule.getText()),tfmodel.getText()
                    ,tfnom.getText());
            Vehicules.Modifier(p);
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
    public void initData(Vehicule p) {
        tfnom.setText(p.getNom());
        tfmodel.setText(p.getModel());
        tfmatricule.setText(String.valueOf(p.getMatricule()));
        lbloo.setText(String.valueOf(p.getId()));
        id = p.getId();
      //String date = p.getDateDachat().toString();

    }

    public void retour2() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();

    }

}
