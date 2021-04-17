package PFA.MaterielFiras.GUIsMateriel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoixVehiculeOutilsController {
    @FXML
    Button btnvehicules,btnoutils;

    public void SwitchToPrincipalListeVehicule(ActionEvent event) throws IOException {
        Parent root7 = FXMLLoader.load(getClass().getResource("Fxmls/ListeVehicule.fxml"));
        Stage stage7 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene7 = new Scene(root7);
        stage7.setScene(scene7);
        stage7.show();
        System.out.println("c'est l'interface des listes des Vehicules");
    }

    public void SwitchToPrincipalListeOutils(ActionEvent event) throws IOException {
        Parent root7 = FXMLLoader.load(getClass().getResource("Fxmls/ListeOutils.fxml"));
        Stage stage7 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene6 = new Scene(root7);
        stage7.setScene(scene6);
        stage7.show();
        System.out.println("c'est l'interface des listes des Outils");
    }
    
    public void retour(ActionEvent event) throws IOException {
        Parent root7 = FXMLLoader.load(getClass().getResource("../../mainMenu.fxml"));
        Stage stage7 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene7 = new Scene(root7);
        stage7.setScene(scene7);
        stage7.show();
    }

}
