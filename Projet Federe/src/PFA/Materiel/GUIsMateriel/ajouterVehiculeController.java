package PFA.Materiel.GUIsMateriel;

import PFA.Materiel.ModuleMateriel.Vehicule;
import PFA.Materiel.ServiceMateriel.Vehicules;

import java.io.IOException;

import PFA.Materiel.ModuleMateriel.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.regex.Pattern;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ajouterVehiculeController {
    @FXML
    Label lbtitleajouter, lbidajouter, lbmodelajouter, lbmatriculeajouter, lbquantitéajouter, lbprixajouter, lbnomajouter,
            lbdateajouter;
    @FXML
    TextField tfid, tfmodel, tfmatricule, tfquantité, tfprix, tfnom;
    @FXML
    Button btnajouter;
    @FXML
    DatePicker datedate;
    @FXML
    AnchorPane anajouter;
    @FXML
    Label lbid, lbprix, lbmodel, lbnom, lbmatricule, lbquantité, lbdate;
    @FXML
    Button retour;
    
    String idPattern = "[0-9]*";
    String prixPattern = "[0-9]*.[0-9]*";
    String nomPattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String modelPattern = "[a-zA-Z0-9-_]*[a-zA-Z0-9-_ ]*";
    String quantitéPattern = "[0-9]*";
    String matriculePattern = "[0-9]{4}";
    String datePattern = "[0-9]*-[0-9]*-[0-9]*";
    
    boolean verif = true;
    
    public void AjouterButton(ActionEvent event) throws IOException {
        
        
        if (!Pattern.matches(idPattern, tfid.getText()) || tfid.getText().isEmpty()) {
            lbid.setVisible(true);
            
        } else {
            lbid.setVisible(false);
        }
        
        
        if (!Pattern.matches(prixPattern, tfprix.getText()) || tfprix.getText().isEmpty()) {
            lbprix.setVisible(true);
        } else {
            lbprix.setVisible(false);
        }
        
        
        if (!Pattern.matches(nomPattern, tfnom.getText()) || tfnom.getText().isEmpty()) {
            lbnom.setVisible(true);
        } else {
            lbnom.setVisible(false);
        }
        
        
        if (!Pattern.matches(modelPattern, tfmodel.getText()) || tfmodel.getText().isEmpty()) {
            lbmodel.setVisible(true);
        } else {
            lbmodel.setVisible(false);
        }
        
        
        if (!Pattern.matches(quantitéPattern, tfquantité.getText()) || tfquantité.getText().isEmpty()) {
            lbquantité.setVisible(true);
        } else {
            lbquantité.setVisible(false);
        }
        
        
        if (!Pattern.matches(matriculePattern, tfmatricule.getText()) || tfmatricule.getText().isEmpty()) {
            lbmatricule.setVisible(true);
        } else {
            lbmatricule.setVisible(false);
        }
        
        
        if (!Pattern.matches(datePattern, datedate.getValue().toString()) || datedate.getValue().toString().isEmpty()) {
            System.out.println();
            lbdate.setVisible(true);
        } else {
            lbdate.setVisible(false);
        }

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
        
        
        if (verif && Pattern.matches(idPattern, tfid.getText()) &&
                Pattern.matches(prixPattern, tfprix.getText()) &&
                Pattern.matches(nomPattern, tfnom.getText())
                && Pattern.matches(modelPattern, tfmodel.getText()) &&
                Pattern.matches(quantitéPattern, tfquantité.getText()) &&
                Pattern.matches(matriculePattern, tfmatricule.getText())
                && !datedate.getValue().toString().isEmpty() && event.getSource() == btnajouter) {
            String i = datedate.getValue().toString();
            Vehicule p = new Vehicule(Integer.parseInt(tfid.getText()),
                    Integer.parseInt(tfmatricule.getText()), tfmodel.getText(),
                    Double.parseDouble(tfprix.getText()), Integer.parseInt(tfquantité.getText())
                    , tfnom.getText(), i);
            Vehicule.Ajouter(p);
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
}