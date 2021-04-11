package PFA.fxmlLoader;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class loader {
    public static Object loadStage(String URL, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(loader.class.getResource(URL));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return loader.getController();
    }
    
    public static Object loadNewStage(String URL) throws IOException {
        FXMLLoader loader = new FXMLLoader(loader.class.getResource(URL));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return loader.getController();
    }
    
}
