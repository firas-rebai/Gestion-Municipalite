package PFA.MaterielFiras.GUIsMateriel;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import PFA.MaterielFiras.ServiceMateriel.Vehicules;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class ajouterVehiculeController implements Initializable {
    @FXML
    Label lbtitleajouter, lbidajouter, lbmodelajouter, lbmatriculeajouter, lbquantitéajouter, lbprixajouter, lbnomajouter,
            lbdateajouter;
    @FXML
    TextField tfid, tfmodel, tfmatricule, tfprix, tfnom;
    @FXML
    Button btnajouter;
    @FXML
    DatePicker datedate;
    @FXML
    AnchorPane anajouter;
    @FXML
    Label lbid, lbprix,lbmodel,lbnom,lbmatricule,lbquantité,lbdate;
    @FXML
    Button retour;

    String idPattern = "[0-9]*";
    String prixPattern="[0-9]*.[0-9]*";
    String nomPattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String modelPattern = "[a-zA-Z0-9-_ ]+";
    String matriculePattern = "[0-9]{4}";
    String datePattern = "[0-9]*-[0-9]*-[0-9]*";

    boolean verif = true;

    public void AjouterButton(ActionEvent event) throws IOException{

       /* LocalDate date = datedate.getValue();
        java.sql.Date sqlDate = Date.valueOf(date);
        */


           

           /* if(datedate.getValue().toString().isEmpty()&tfmatricule.getText().isEmpty()&&
                    tfquantité.getText().isEmpty()&&tfmodel.getText().isEmpty()&&tfnom.getText().isEmpty()&&
                    tfprix.getText().isEmpty()&&tfid.getText().isEmpty() && event.getSource()==btnajouter){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("valider tous les champs svp");
                alert.setHeaderText(null);
                alert.setContentText("svp entrer les informations necessaires");
                alert.show();
            }

            */




        if (verif &&
                Pattern.matches(nomPattern, tfnom.getText())
                && Pattern.matches(modelPattern, tfmodel.getText()) &&
                Pattern.matches(matriculePattern, tfmatricule.getText()) && event.getSource()==btnajouter) {
            Vehicule p = new Vehicule(Integer.parseInt(tfmatricule.getText()),tfmodel.getText(),tfnom.getText());
            Vehicules.Ajouter(p);
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
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        tfnom.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbnom.setVisible(!Pattern.matches(nomPattern, tfnom.getText())|| tfnom.getText().isEmpty());
            }
        });
    
        tfmodel.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbmodel.setVisible(!Pattern.matches(modelPattern, tfmodel.getText()) || tfmodel.getText().isEmpty());
            }
        });
    
    
        tfmatricule.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lbmatricule.setVisible(!Pattern.matches(matriculePattern, tfmatricule.getText())|| tfmatricule.getText().isEmpty());
            }
        });
        
    }
}