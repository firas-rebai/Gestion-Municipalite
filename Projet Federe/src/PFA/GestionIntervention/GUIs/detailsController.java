package PFA.GestionIntervention.GUIs;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Modules.OutilsUtilise;
import PFA.GestionPersonnel.Modules.Personnel;
import PFA.Materiel.ModuleMateriel.Outil;
import PFA.Materiel.ServiceMateriel.Vehicules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class detailsController {
    
    @FXML
    TableView<Outil> OutilListe;
    @FXML
    TableColumn<Outil, String> nomOutilColumn;
    @FXML
    TableColumn<Outil, Integer> quantiteOutilColumn;
    
    @FXML
    TableView<Personnel> PersonnelListe;
    @FXML
    TableColumn<Personnel, String> nomPersonnelColumn;
    @FXML
    TableColumn<Personnel, String> prenomPersonnelColumn;
    @FXML
    TableColumn<Personnel, Integer> cinPersonnelColumn;
    @FXML
    TableColumn<Personnel, String> postePersonnelColumn;
    
    @FXML
    TableView<Vehicules> VehiculeListe;
    @FXML
    TableColumn<Vehicules, String> nomVehiculeColumn;
    @FXML
    TableColumn<Vehicules, String> modelVehiculeColumn;
    @FXML
    TableColumn<Vehicules, Integer> matriculeVehiculeColumn;
    
    @FXML
    Label nomLabel,dateDebutLabel,dateFinLabel,adresseLabel,budgetLabel;
    
    public void initData(Intervention intervention) {
        //initializing outil liste
        nomOutilColumn.setCellValueFactory(new PropertyValueFactory<>("nomOutils"));
        quantiteOutilColumn.setCellValueFactory(new PropertyValueFactory<>("quantiteOutils"));
        for (OutilsUtilise o : intervention.getOutilsUtilises()) {
            OutilListe.getItems().add(o.outils);
        }
        //intializing personnel liste
        nomPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cinPersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        postePersonnelColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        PersonnelListe.getItems().setAll(intervention.getEquipe());
        //initializing vehicule liste
        nomVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        modelVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        matriculeVehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        VehiculeListe.getItems().setAll(intervention.getVehicules());
        //initializing lables
        nomLabel.setText(intervention.getNom());
        adresseLabel.setText(intervention.getAdresse());
        budgetLabel.setText(String.valueOf(intervention.getBudget()));
        dateDebutLabel.setText(String.valueOf(intervention.getDateBedut()));
        dateFinLabel.setText(String.valueOf(intervention.getDateFin()));
    }
    
    public void listeOutilButton(){
        PersonnelListe.setVisible(false);
        OutilListe.setVisible(true);
        VehiculeListe.setVisible(false);
    }
    
    public void listeVehiculeButton(){
        PersonnelListe.setVisible(false);
        OutilListe.setVisible(false);
        VehiculeListe.setVisible(true);
    }
    
    public void listePersonnelButton(){
        VehiculeListe.setVisible(false);
        PersonnelListe.setVisible(true);
        OutilListe.setVisible(false);
    }
    
    public void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/listeIntervention.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
