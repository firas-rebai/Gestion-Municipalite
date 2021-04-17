package PFA.GestionIntervention.GUIs.ajouterIntervention;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Modules.PersonnelMin;
import PFA.GestionIntervention.Services.InterventionServices;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.collections.ObservableList;
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

public class ajouterPersonnelIntervention implements Initializable {
    public Intervention intervention;
    
    @FXML
    private TableView<PersonnelMin> PersonnelTV;
    
    @FXML
    private TableColumn<PersonnelMin, String> nomColumn;
    
    @FXML
    private TableColumn<PersonnelMin, String> prenomColumn;
    
    @FXML
    private TableColumn<PersonnelMin, String> posteColumn;
    
    @FXML
    private TableColumn<PersonnelMin, CheckBox> selectColumn;
    
    
    public void retour(ActionEvent event) {
    }
    
    public void switchToOutil(ActionEvent event) throws IOException {
        ArrayList<PersonnelMin> liste;
        if (!PersonnelTV.getSelectionModel().isEmpty()){
            liste = new ArrayList<>(PersonnelTV.getItems());
            intervention.setEquipe(liste);
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("aucun personnel selectione");
            alert.showAndWait();
            return ;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterVehiculeIntervention.fxml"));
        Parent root = loader.load();
        ajouterVehiculeIntervention controller = loader.getController();
        controller.intervention = intervention;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
        for(PersonnelMin p:InterventionServices.ParsePersonnelListe()){
            System.out.println(p.getNom());
        }
        PersonnelTV.getItems().setAll(InterventionServices.ParsePersonnelListe());
    }
    
    
    
}
