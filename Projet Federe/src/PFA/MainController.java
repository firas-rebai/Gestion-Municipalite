package PFA;

import PFA.GestionPersonnel.GUIs.ListePersonnelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    public void switchToIntervention(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestionIntervention/GUIs/fxml/listeIntervention.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToListe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ListePersonnelController controller =  loader.getController();
        controller.initListe();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMateriel(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Pesonnel/GUIs/fxml/listepersonnel.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("liste Materiel");

    }

}
