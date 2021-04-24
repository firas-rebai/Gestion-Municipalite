package PFA.GestionIntervention.GUIs;


import PFA.GestionIntervention.GUIs.ajouterIntervention.ajouterVehiculeIntervention;
import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Services.InterventionServices;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ListeInterventionController {
    @FXML
    TableView<Intervention> listeIntervention;
    @FXML
    TableColumn<Intervention, String> nomColumn;
    @FXML
    TableColumn<Intervention, Date> dateDebutColumn;
    @FXML
    TableColumn<Intervention, Date> dateFinColumn;
    @FXML
    TableColumn<Intervention, String> adresseColumn;
    
    public void initListe(){
        refreshListe(InterventionServices.parseInterventionListe());
    }
    
    public void refreshListe(List<Intervention> parseInterventionListe) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        listeIntervention.getItems().setAll(parseInterventionListe);
    }
    
    public void ajouterButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ajouterGenerale.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.show();
        
    }
    
    
    public void detailsbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/detailsIntervention.fxml"));
        Parent root = loader.load();
        detailsController controller = loader.getController();
        controller.initData(listeIntervention.getSelectionModel().getSelectedItem());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void modifierButton(ActionEvent event) {
    
    
    }
    
    public void supprimerButton(ActionEvent event) {
    }
    
    public void retour(ActionEvent event) throws IOException {
        Parent root7 = FXMLLoader.load(getClass().getResource("../../mainMenu.fxml"));
        Stage stage7 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene7 = new Scene(root7);
        stage7.setScene(scene7);
        stage7.show();
    }
}
