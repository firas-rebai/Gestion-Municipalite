package PFA;

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


    public void switchToListe(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Pesonnel/GUIs/fxml/listepersonnel.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("liste Personnel");
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
