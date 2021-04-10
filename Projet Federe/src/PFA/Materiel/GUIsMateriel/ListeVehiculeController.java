package PFA.Materiel.GUIsMateriel;

import Materiel.ModuleMateriel.MaterielVehicule;
import Materiel.ServiceMateriel.Vehicule;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import static DataBaseConnection.OracleConnection.getOracleConnection;
public class ListeVehiculeController implements Initializable {
@FXML
    Label lbtitle;
@FXML
    TextField tfrecherche;
@FXML
    TableView<MaterielVehicule> tvliste;
@FXML
    TableColumn<MaterielVehicule,Integer> colid;
@FXML
    TableColumn<MaterielVehicule,String> colnom;
@FXML
    TableColumn<MaterielVehicule,Double> colprix;
    @FXML
    TableColumn<MaterielVehicule,Integer> colmatricule;
@FXML
    Button btnajouter,btnmodifier,btndetails,btnsupprimer,btnretour,btnrecherche,btnactualiser;
@FXML
    AnchorPane anpane;


    public void initialize(URL url,ResourceBundle resource) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tvliste.getItems().setAll(VehiculesListe());

        }

    private List<MaterielVehicule> VehiculesListe() {
        List<MaterielVehicule> data = new ArrayList<>();
        String SQLquery = "SELECT * from materielvehicule";
        try {
            Connection connection = getOracleConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new MaterielVehicule(
                        rs.getInt("id"),
                        rs.getInt("matricule"),
                        rs.getString("model") ,
                        rs.getDouble("prix"),
                        rs.getInt("quantité"),
                        rs.getString("nom"),
                        rs.getString("datedachat")));

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

    public void rechercheButton(){
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tvliste.getItems().setAll(Vehicule.Recherche(tfrecherche.getText()));
        if (tfrecherche.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("echec de recherche");
            alert.setHeaderText(null);
            alert.setContentText("svp entrer un id ou une matricule pour faite la recherche");
            alert.show();
        }
    }
    public void retour(){

    }

    public void RefreshButton(ActionEvent event) throws IOException{
        if(event.getSource()==btnactualiser) {
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
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
