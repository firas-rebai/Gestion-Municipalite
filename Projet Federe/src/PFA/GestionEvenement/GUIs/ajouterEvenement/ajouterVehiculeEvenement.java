package PFA.GestionEvenement.GUIs.ajouterEvenement;

import PFA.GestionEvenement.Modules.Evenement;
import PFA.GestionEvenement.Services.EvenementServ;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ajouterVehiculeEvenement{


    public void retour6(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterVehiculeEvenement.fxml"));
        Parent root = loader.load();
        ajouterVehiculeEvenement controller = loader.getController();
        controller.evenement = evenement;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToOutilEvenement(ActionEvent event) throws IOException {
        ArrayList<Vehicule> liste = new ArrayList<>(listeVehicule.getItems());
        ArrayList<Vehicule> toAdd = new ArrayList<>();
        for (Vehicule v : liste) {
            if (v.getCheck().isSelected())
                toAdd.add(v);
        }
        if (!toAdd.isEmpty()) {
            evenement.setVehiculesEve(toAdd);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Aucune Vehicule Selectionee");
            alert.showAndWait();
            return;
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterOutilEvenement.fxml"));
        Parent root = loader.load();
        ajouterOutilEvenement controller = loader.getController();
        controller.evenement = evenement;
        controller.initData();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public Evenement evenement;

    @FXML
    private TableView<Vehicule> listeVehicule;

    @FXML
    private TableColumn<Vehicule, String> nomColumn;

    @FXML
    private TableColumn<Vehicule, String> modelColumn;

    @FXML
    private TableColumn<Vehicule, Integer> MatriculeColumn;
    @FXML
    private TableColumn<Vehicule, CheckBox> selectColumn;


    public void initData() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        MatriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        ArrayList<Vehicule> liste = (ArrayList<Vehicule>) EvenementServ.parseVehiculeList();
        for (Vehicule v : liste){
            v.setCheck(new CheckBox());
        }
        listeVehicule.getItems().setAll(liste);


    }
}
