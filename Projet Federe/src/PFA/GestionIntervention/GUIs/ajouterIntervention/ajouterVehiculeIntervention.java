package PFA.GestionIntervention.GUIs.ajouterIntervention;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ajouterVehiculeIntervention implements Initializable {
    public void retour(ActionEvent event) {
    }
    
    public void switchToOutil(ActionEvent event) {
    
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        MatriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        listeVehicule.getItems().setAll();
    }
}
