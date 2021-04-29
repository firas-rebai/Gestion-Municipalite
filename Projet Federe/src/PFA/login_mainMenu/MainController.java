package PFA.login_mainMenu;


import PFA.GestionIntervention.GUIs.ListeInterventionController;
import PFA.GestionPersonnel.GUIs.ListePersonnelController;
import PFA.GestionTache.GUIs.ListeTacheController;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void switchToTache(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionTache/GUIs/fxml/listetache.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        ListeTacheController controller = loader.getController();
//        controller.initListe();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToIntervention(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionIntervention/GUIs/fxml/listeIntervention.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ListeInterventionController controller = loader.getController();
        controller.initListe();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPersonnel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionPersonnel/GUIs/fxml/listepersonnel.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("PFA/resources/style.css")).toExternalForm());
        ListePersonnelController controller =  loader.getController();
        controller.initListe();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMateriel(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../MaterielFiras/GUIsMateriel/Fxmls/ChoixVehiculeOutils.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();

    }
    
    public void switchToDoleance(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionDoleances/GUIs/Fxmls/ListeDoleance.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToCompte() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionCompte/GUIs/fxml/listecompte.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        JMetro jMetro = new JMetro(Style.LIGHT);
        Scene scene = new Scene(root);
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    public void switchToDemande(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GestionDemandes/GUIs/fxml/ListeDemande.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    Icon logoutIcon;
    
    public void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logoutIcon.setContent(MaterialDesignIcon.TIME_TO_LEAVE);
    
    }
}
