package com.pfa.modules.Personnel;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToAjouter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("listepersonnel.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToListe(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ajouterpersonnel.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
