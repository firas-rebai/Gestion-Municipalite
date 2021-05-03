package PFA.GestionEvenement.GUIs.ajouterEvenement;

import PFA.GestionEvenement.Modules.Evenement;
import PFA.GestionEvenement.Services.EvenementServ;
import PFA.GestionIntervention.GUIs.ajouterIntervention.ajouterVehiculeIntervention;
import PFA.GestionIntervention.Modules.PersonnelMin;
import PFA.GestionIntervention.Services.InterventionServices;
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
import PFA.GestionEvenement.Modules.Perso;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ajouterPersonnelEvenement implements Initializable {
    private Evenement evenement;

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @FXML
    private TableView<Perso> PersonnelTV;

    @FXML
    private TableColumn<Perso, String> nomColumn;

    @FXML
    private TableColumn<Perso, String> prenomColumn;

    @FXML
    private TableColumn<Perso, String> posteColumn;

    @FXML
    private TableColumn<Perso, CheckBox> selectColumn;

    public void retour1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterGeneralEvenement.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToVehicule(ActionEvent event) throws IOException {
        ArrayList<Perso> liste = new ArrayList<>(PersonnelTV.getItems());
        ArrayList<Perso> toAdd = new ArrayList<>();
        for (Perso p : liste) {
            if (p.getCheckper().isSelected()) {
                toAdd.add(p);
            }
        }

        if (!toAdd.isEmpty()) {
            evenement.setEquipeEve(toAdd);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Aucun personnel selectione");
            alert.showAndWait();
            return ;
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ajouterVehiculeEvenement.fxml"));
        Parent root = loader.load();
        ajouterVehiculeEvenement controller = loader.getController();
        controller.evenement = getEvenement();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomper"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenomper"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("posteper"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("checkper"));
        ArrayList<Perso> liste = (ArrayList<Perso>) EvenementServ.ParsePersonnelListe();
        for (Perso p:liste){
            p.setCheckper(new CheckBox());
        }
        PersonnelTV.getItems().setAll(liste);
    }
}
