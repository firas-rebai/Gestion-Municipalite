package PFA.demade.GUIs;

import Module.DemandeModu;
import Services.DemandeServ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AjouterDemandeController implements Initializable {
    @FXML
    TextField nom,prenom,cin,adresse,tel;
    @FXML
    Label lbnom,lbprenom,lbcin,lbadresse,lbtel,lbdate,lbtype,remplir;
    @FXML
    TextArea descreption;
    @FXML
    ChoiceBox<String> type;
    @FXML
    Button boutajouter,retour;
    @FXML
    DatePicker date;

    private final String[] Type = {"Voirie", "Degradation", "Construction"};
    String nompattern = "[a-zA-Z]*";
    String prenompattern = "[a-zA-Z]*";
    String cinpattern = "[0-9]{8}";
    String adressepattern = "[a-zA-Z0-9,;. ]*";
    //  String idpattern = "[0-9]*";
    String datepattern = "[0-9]{2}/-[0-9]{2}/-[0-9]*";
    // String descreptionpattern = "";
    String telpattern = "[0-9]{8}";
    boolean verif = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().setAll(Type);

        nom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbnom.setVisible(!Pattern.matches(nompattern, nom.getText())|| nom.getText().isEmpty());
            }
        });

        type.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbtype.setVisible(type.getSelectionModel().isEmpty());
            }
        });

        prenom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbprenom.setVisible(!Pattern.matches(prenompattern, prenom.getText()) || prenom.getText().isEmpty());
            }
        });


        cin.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbcin.setVisible(!Pattern.matches(cinpattern, cin.getText())|| cin.getText().isEmpty());
            }
        });

        adresse.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbadresse.setVisible(!Pattern.matches(adressepattern, adresse.getText())|| adresse.getText().isEmpty());
            }
        });

        tel.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbtel.setVisible(!Pattern.matches(telpattern, tel.getText())|| tel.getText().isEmpty());
            }
        });

    }


    public void ajouterDem(ActionEvent event) throws IOException{
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || cin.getText().isEmpty() || adresse.getText().isEmpty() ||
                type.getSelectionModel().isEmpty() || tel.getText().isEmpty()
                || date.getValue().toString().isEmpty() ) {
            remplir.setVisible(true);
            verif = false;
        } else {
            remplir.setVisible(false);
        }

        if (verif && Pattern.matches(nompattern, nom.getText())
                && Pattern.matches(prenompattern, prenom.getText()) &&
                Pattern.matches(cinpattern, cin.getText()) &&
                Pattern.matches(adressepattern, adresse.getText()) &&
                Pattern.matches(telpattern, tel.getText()) &&
                !type.getSelectionModel().isEmpty() &&
                !date.getValue().toString().isEmpty() &&
                event.getSource() == boutajouter) {

            DemandeModu p = new DemandeModu(nom.getText(),prenom.getText(),Integer.parseInt(cin.getText()),
                    adresse.getText(),Integer.parseInt(tel.getText()),date.getValue(),descreption.getText(),
                    type.getValue());


            DemandeServ.Ajouter(p);
            Stage stage = (Stage) boutajouter.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés d'ajout");
            alert.setHeaderText(null);
            alert.setContentText("l'ajout est fait avec succés");
            alert.show();




        }
    }



    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }


}

