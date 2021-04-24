package PFA.GestionIntervention.GUIs.ajouterIntervention;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Modules.OutilsUtilise;
import PFA.GestionIntervention.Services.InterventionServices;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import PFA.MaterielFiras.ServiceMateriel.Outils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ajouterOutilIntervention implements Initializable {
    
    @FXML
    private TableView<Outil> outilTV;
    
    @FXML Button ajouter;
    
    @FXML
    private TableColumn<Outil, String> nomColumn;
    
    @FXML
    private TableColumn<Outil, String> conumableColumn;
    
    @FXML
    private TableColumn<Outil, Integer> quantiteColumn;
    
    @FXML
    private TableColumn<Outil, Spinner<Integer>> spinnerColumn;
    
    public Intervention intervention;
    
    private ArrayList<HBox> hBoxes = new ArrayList<>();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        conumableColumn.setCellValueFactory(new PropertyValueFactory<>("consumable"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        spinnerColumn.setCellValueFactory(new PropertyValueFactory<>("spinner"));
        ArrayList<Outil> liste = new ArrayList<>(InterventionServices.parseOutilList());
        for (Outil o: liste){
            o.setSpinner(new Spinner<Integer>());
            SpinnerValueFactory<Integer> valueFactory = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, o.getQuantite(), 0);
            o.getSpinner().setValueFactory(valueFactory);
        }
        outilTV.getItems().setAll(liste);
        
//        ArrayList<Outil> outil = (ArrayList<Outil>) Outils.OutilsListe();
//        vbox.setSpacing(10);
//        for (Outil o:outil){
//            //setup hbox's height and width
//            HBox hbox = new HBox();
//            hbox.prefHeight(40);
//            hbox.prefWidth(545);
//
//            //setup nom label
//            Label nomLabel = new Label(o.getNom());
//            nomLabel.prefHeight(27);
//            nomLabel.prefWidth(130);
//            nomLabel.setFont(new Font("Arial",16));
//            nomLabel.setMinWidth(130);
//
//            //setup consumable label
//            Label conLabel = new Label();
//            if (o.getConsumable() == 1) conLabel.setText("Consumable");
//            else conLabel.setText("Non Consumable");
//            conLabel.prefWidth(159);
//            conLabel.prefHeight(27);
//            conLabel.setFont(new Font("Arial",16));
//            conLabel.setMinWidth(159);
//
//            //setup quantite label
//            Label quanLabel = new Label();
//            quanLabel.setText(o.getQuantite()+"");
//            quanLabel.prefWidth(142);
//            quanLabel.setMinWidth(100);
//            quanLabel.prefHeight(27);
//            quanLabel.setFont(new Font("Arial",16));
//
//            //setup spinner
//            Spinner<Integer> spinner = new Spinner<>();
//            SpinnerValueFactory<Integer> valueFactory = //
//                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, o.getQuantite(), 0);
//            spinner.setValueFactory(valueFactory);
//            spinner.prefHeight(27);
//            spinner.prefWidth(150);
//            spinner.setMinWidth(150);
//
//            //add labels and spinner to hbox
//            hbox.getChildren().add(nomLabel);
//            hbox.getChildren().add(conLabel);
//            hbox.getChildren().add(quanLabel);
//            hbox.getChildren().add(spinner);
//            hbox.getStyleClass().add("hbox");
//            hBoxes.add(hbox); // for later use in checking button
//
//            vbox.getChildren().add(hbox);//add an hbox to the vbox for every iteration
//
//        }
//
//
    }
    
    public void retour(ActionEvent event) {
    
    }
    
    public void ajouter(ActionEvent event) {
        ArrayList<OutilsUtilise> toAdd = new ArrayList<>();
        ArrayList<Outil> liste = new ArrayList<>(outilTV.getItems());
        for (Outil o : liste){
            if (o.getSpinner().getValue() > 0){
                toAdd.add(new OutilsUtilise(o,o.getSpinner().getValue()));
            }
        }
        if(!toAdd.isEmpty()){
            intervention.setOutilsUtilises(toAdd);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Outil Selectione");
            alert.showAndWait();
            return ;
        }
        InterventionServices.Ajouter(intervention);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Intervention ajoute");
        alert.showAndWait();
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.close();
        
    }
}

