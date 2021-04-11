package PFA.GestionIntervention.GUIs;


import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Services.InterventionServices;

import PFA.fxmlLoader.loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
        refresheListe(InterventionServices.parseInterventionListe());
    }
    
    public void refresheListe(List<Intervention> parseInterventionListe) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        
        listeIntervention.getItems().setAll(parseInterventionListe);
    }
    
    public void ajouterButton(){
    
    }
    
    
    public void detailsbutton(ActionEvent event) throws IOException {
        detailsController controller = (detailsController) loader.loadStage("fxml/detailsIntervention.fxml",event);
        controller.initData(listeIntervention.getSelectionModel().getSelectedItem());
    }
    
    public void modifierButton(ActionEvent event) {
    
    }
    
    public void supprimerButton(ActionEvent event) {
    }
}
