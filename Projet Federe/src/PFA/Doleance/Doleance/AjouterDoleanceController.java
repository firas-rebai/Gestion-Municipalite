package PFA.Doleance.Doleance;

import Service.DoleanceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Module.ModuleDoleance;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class AjouterDoleanceController {
    @FXML
    Button btnajouter,btnannuler;
    @FXML
    TextField tfid,tfsujet;
    @FXML
    DatePicker date;
    @FXML
    TextArea tades;
    @FXML
    Label lblid,lbldate,lblsujet;

    String idPattern = "[0-9]*";
    String sujetPattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String descreptionPattern = "[a-zA-Z0-9-_]*[a-zA-Z0-9-_ ]*";
    String datePattern = "[0-9]*/[0-9]*/[0-9]*";

    boolean verif = true ;

    public void AjouterButton(ActionEvent event) throws IOException {


        if (!Pattern.matches(idPattern, tfid.getText()) || tfid.getText().isEmpty()) {
            lblid.setVisible(true);

        } else {
            lblid.setVisible(false);
        }


        if (!Pattern.matches(sujetPattern, tfsujet.getText()) || tfsujet.getText().isEmpty()) {
            lblsujet.setVisible(true);
        } else {
            lblsujet.setVisible(false);
        }


       /* if (date.getValue().toString().isEmpty()) {
            System.out.println();
            lbldate.setVisible(true);
        } else {
            lbldate.setVisible(false);
        }

        */


        if (verif && Pattern.matches(idPattern, tfid.getText()) &&
                Pattern.matches(sujetPattern, tfsujet.getText()) &&
                Pattern.matches(descreptionPattern, tades.getText()) &&
                !date.getValue().toString().isEmpty() && event.getSource() == btnajouter) {
            String i= date.getValue().toString();
            ModuleDoleance p = new ModuleDoleance(tades.getText(),Integer.parseInt(tfid.getText()),
                    tfsujet.getText(),i);
            DoleanceService.Ajouter(p);

            Stage stage = (Stage) btnajouter.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés d'ajout");
            alert.setHeaderText(null);
            alert.setContentText("l'ajout est fait avec succés");
            alert.show();


        }


    }

    public void retour() {
        Stage stage = (Stage) btnannuler.getScene().getWindow();
        stage.close();
    }

}
