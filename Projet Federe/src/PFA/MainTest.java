package PFA;


import PFA.GestionProjet.GUIs.ListeProjetController;
import com.gluonhq.charm.glisten.control.Icon;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainTest extends Application {
    @FXML
    Icon icon;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionProjet/GUIs/fxml/listeprojet.fxml"));
        Parent root = loader.load();
        ListeProjetController controller = loader.getController();
        controller.initDate();
        primaryStage.setTitle("brabi e5dem the project");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
