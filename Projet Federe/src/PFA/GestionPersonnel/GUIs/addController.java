package PFA.GestionPersonnel.GUIs;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionPersonnel.Services.PersonnelServices;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class addController implements Initializable {
    String numPattern = "[2-9][0-9]{7}";
    String namePattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String salairePattern = "[0-9]{1,9}.[0-9]+";
    String cinPattern = "[0-9]{8}";
    String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
    private final String[] Poste = {"Agent(e) administratif(tive)", "Ouvrier(e)", "Technicien(ne) Principal(e)"};
    
    @FXML
    ChoiceBox<String> EquipPicker;
    @FXML
    ChoiceBox<String> PostePicker;
    @FXML
    Label nomErrorLabel, prenomErrorLabel, emailErrorLabel, posteErrorLabel, equipeErrorLabel, dateErrorLabel, cinErrorLabel, numErrorLabel, salaireErrorLabel, Remplir;
    @FXML
    TextField nom, prenom, email, CIN, num, salaire;
    @FXML
    DatePicker date;
    @FXML
    Button retour;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //fill Equipe and poste picker with their values
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select * from equipe");
            while (res.next()) {
                EquipPicker.getItems().add(res.getString("nom"));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        PostePicker.getItems().setAll(Poste);
        
        //Check if every text field is correct when it loses focus
        
        nom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                nomErrorLabel.setVisible(!Pattern.matches(namePattern, nom.getText()));
            }
        });
        
        
        EquipPicker.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                equipeErrorLabel.setVisible(EquipPicker.getSelectionModel().isEmpty());
            }
        });
        
        
        PostePicker.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                posteErrorLabel.setVisible(PostePicker.getSelectionModel().isEmpty());
            }
        });
        
        
        prenom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                prenomErrorLabel.setVisible(!Pattern.matches(namePattern, prenom.getText()));
            }
        });
        
        
        salaire.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                nomErrorLabel.setVisible(!Pattern.matches(salairePattern, salaire.getText()));
            }
        });
        
        
        email.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                emailErrorLabel.setVisible(!Pattern.matches(emailPattern, email.getText()));
            }
        });
        
        
        CIN.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                cinErrorLabel.setVisible(!Pattern.matches(cinPattern, CIN.getText()));
            }
        });
        
        
        num.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                numErrorLabel.setVisible(!Pattern.matches(numPattern, num.getText()));
            }
        });
    }
    
    
    public void AjouterButton() {
        boolean valid = true;
        if (EquipPicker.getSelectionModel().isEmpty() || PostePicker.getSelectionModel().isEmpty() || salaire.getText().isEmpty() || CIN.getText().isEmpty() || email.getText().isEmpty() || num.getText().isEmpty() || prenom.getText().isEmpty() || nom.getText().isEmpty() || date.getValue().toString().isEmpty()) {
            Remplir.setVisible(true);
            valid = false;
        } else Remplir.setVisible(false);
        
        if (valid && Pattern.matches(numPattern, num.getText()) && Pattern.matches(cinPattern, CIN.getText()) && Pattern.matches(emailPattern, email.getText()) && Pattern.matches(salairePattern, salaire.getText()) && Pattern.matches(namePattern, prenom.getText()) && Pattern.matches(namePattern, nom.getText()) && !PostePicker.getSelectionModel().isEmpty() && !EquipPicker.getSelectionModel().isEmpty()) {
            Personnel p = new Personnel(nom.getText(), prenom.getText(), Integer.parseInt(CIN.getText()), email.getText(), Integer.parseInt(num.getText()), Float.parseFloat(salaire.getText()), PostePicker.getValue(), EquipPicker.getValue(), date.getValue().toString());
            PersonnelServices.Ajouter(p);
        }
    }
    
    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
    
    
}
