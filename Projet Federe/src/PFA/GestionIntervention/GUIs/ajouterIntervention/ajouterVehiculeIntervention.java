package PFA.GestionIntervention.GUIs.ajouterIntervention;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Services.InterventionServices;
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
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ajouterVehiculeIntervention implements Initializable {
    public void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterVehiculeIntervention.fxml"));
        Parent root = loader.load();
        ajouterVehiculeIntervention controller = loader.getController();
        controller.intervention = intervention;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToOutil(ActionEvent event) throws IOException {
        ArrayList<Vehicule> liste = new ArrayList<>(listeVehicule.getItems());
        ArrayList<Vehicule> toAdd = new ArrayList<>();
        for (Vehicule v: liste){
            if (v.getCheck().isSelected())
                toAdd.add(v);
        }
        if(!toAdd.isEmpty()){
            intervention.setVehicules(toAdd);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Aucune Vehicule Selectionee");
            alert.showAndWait();
            return ;
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterOutilIntervention.fxml"));
        Parent root = loader.load();
        ajouterOutilIntervention controller = loader.getController();
        controller.intervention = intervention;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public Intervention intervention;
    
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
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        MatriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        ArrayList<Vehicule> liste = (ArrayList<Vehicule>) InterventionServices.parseVehiculeList();
        for (Vehicule v : liste){
            v.setCheck(new CheckBox());
        }
        listeVehicule.getItems().setAll(liste);
        
        
    }
}
