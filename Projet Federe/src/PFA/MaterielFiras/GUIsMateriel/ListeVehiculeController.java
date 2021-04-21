package PFA.MaterielFiras.GUIsMateriel;

import PFA.MaterielFiras.ModuleMateriel.Vehicule;
import PFA.MaterielFiras.ServiceMateriel.Vehicules;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class ListeVehiculeController implements Initializable {
    @FXML
    Label lbtitle;
    @FXML
    TextField tfrecherche;
    @FXML
    TableView<Vehicule> tvliste;
    @FXML
    TableColumn<Vehicule, Integer> colid;
    @FXML
    TableColumn<Vehicule, String> colnom;
    @FXML
    TableColumn<Vehicule, String> colmodel;
    @FXML
    TableColumn<Vehicule, Integer> colmatricule;
    @FXML
    Button btnajouter, btnmodifier, btnsupprimer, btnretour, btnrecherche, btnactualiser;
    @FXML
    AnchorPane anpane;
    
    
    public void initialize(URL url, ResourceBundle resource) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colmodel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tvliste.getItems().setAll(VehiculesListe());
        
        anpane.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                tvliste.getSelectionModel().clearSelection();
                btnmodifier.setDisable(true);
                btnsupprimer.setDisable(true);
            }
        });
        
        tfrecherche.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                tvliste.getSelectionModel().clearSelection();
                btnmodifier.setDisable(true);
                btnsupprimer.setDisable(true);
            }
        });
        
        btnactualiser.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                tvliste.getSelectionModel().clearSelection();
                btnmodifier.setDisable(true);
                btnsupprimer.setDisable(true);
            }
        });
        
        tvliste.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && !tvliste.getSelectionModel().isEmpty()) {
                btnmodifier.setDisable(false);
                btnsupprimer.setDisable(false);
            }
        });
        
        
    }
    
    private List<Vehicule> VehiculesListe() {
        List<Vehicule> data = new ArrayList<>();
        String SQLquery = "SELECT * from VEHICULE";
        try {
            Connection connection = getOracleConnection();
            
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new Vehicule(
                        rs.getInt("idVehicule"),
                        rs.getInt("matriculeVehicule"),
                        rs.getString("modelVehicule"),
                        rs.getString("nomVehicule")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
        
    }
    
    public void switchToAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/ajouterVehicule.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.show();
    }
    
    
    public void switchToModifier() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/ModifierVehicule.fxml"));
        Parent rt = loader.load();
        ModifierVehiculeController controller = loader.getController();
        controller.initData(tvliste.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        stage.setScene(scene);
        stage.show();
    }
    
    public void rechercheButton() {
        if (!tfrecherche.getText().isEmpty()) {
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            tvliste.getItems().setAll(Vehicules.Recherche(tfrecherche.getText()));
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("echec de recherche");
            alert.setHeaderText(null);
            alert.setContentText("svp entrer un id ou une matricule pour faite la recherche");
            alert.show();
        }
    }
    
    public void retour() {
    
    }
    
    public void RefreshButton(ActionEvent event) throws IOException {
        if (event.getSource() == btnactualiser) {
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colmodel.setCellValueFactory(new PropertyValueFactory<>("model"));
            colmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            tvliste.getItems().setAll(VehiculesListe());
        }
    }
    
    public void switchToDetails() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/DetailsVehicule.fxml"));
        Parent rt = loader.load();
        DetailsVehiculeController controller = loader.getController();
        controller.initData(tvliste.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToSupprimer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/SuppressionVehicule.fxml"));
        Parent rt = loader.load();
        SuppressionVehiculeController controller = loader.getController();
        controller.matricule = tvliste.getSelectionModel().getSelectedItem().getMatricule();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.show();
        
        
    }
    
    public void switchToChoix(ActionEvent event) throws IOException {
        Parent root7 = FXMLLoader.load(getClass().getResource("Fxmls/ChoixVehiculeOutils.fxml"));
        Stage stage7 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene7 = new Scene(root7);
        stage7.setScene(scene7);
        stage7.show();
        System.out.println("c'est l'interface des listes des Vehicules && des Outils");
    }
    
    
}
