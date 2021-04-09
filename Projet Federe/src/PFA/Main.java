package PFA;

import PFA.GestionPersonnel.GUIs.ListeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionPersonnel/GUIs/fxml/listepersonnel.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("brabi e5dem");
        Scene scene = new Scene(root, 1920, 1011);
        ListeController controller =  loader.getController();
        controller.initListe();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    

    public static void main(String[] args) {
        launch(args);
    }

}
