package PFA.Materiel2.GUIsMateriel;
import Materiel.ModuleMateriel.MaterielOutils;
import Materiel.ServiceMateriel.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import static DataBaseConnection.OracleConnection.getOracleConnection;

public class ListeOutilsController implements Initializable {

    @FXML
    Button boutajouter,boutmodifier,boutactualiser,boutsupprimer,boutrecherche,boutretour;
    @FXML
    TableView<MaterielOutils> tvoutils;
    @FXML
    TableColumn<MaterielOutils,Integer> coulid;
    @FXML
    TableColumn<MaterielOutils,String> coulnom;
    @FXML
    TableColumn<MaterielOutils,Integer> coulquantité;
    @FXML
    TableColumn<MaterielOutils,Float> coulprix;
    @FXML
    Label lboutilstitle;
    @FXML
    AnchorPane anchro;
    @FXML
    TextField toufrecherche;

    public void initialize(URL url, ResourceBundle resource){
        coulid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coulnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coulquantité.setCellValueFactory(new PropertyValueFactory<>("quantité"));
        coulprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tvoutils.getItems().setAll(OutilsListe());
    }

    private List<MaterielOutils> OutilsListe() {
        List<MaterielOutils> data = new ArrayList<>();
        String SQLquery = "SELECT * from materieloutils ";
        try {
            Connection connection = getOracleConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new MaterielOutils(
                        rs.getInt("id"),
                        rs.getInt("quantité"),
                        rs.getDouble("prix"),
                        rs.getString("nom")));

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;

    }
    public void switchToAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/ajouterOutils.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.show();


    }

    public void rechercheButton(ActionEvent event){
        if (event.getSource()==boutrecherche && !toufrecherche.getText().isEmpty()) {
            coulid.setCellValueFactory(new PropertyValueFactory<>("id"));
            coulnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            coulquantité.setCellValueFactory(new PropertyValueFactory<>("quantité"));
            coulprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tvoutils.getItems().setAll(Outils.Recherche(toufrecherche.getText()));
        }
        if (event.getSource()==boutrecherche && toufrecherche.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("echec de recherche");
            alert.setHeaderText(null);
            alert.setContentText("svp entrer un id , un prix , quantité ou un nom  pour faite la recherche");
            alert.show();
        }
    }


    public void retour(){

    }

    public void switchToChoix1(ActionEvent event) throws IOException {
        Parent root7 = FXMLLoader.load(getClass().getResource("Fxmls/ChoixVehiculeOutils.fxml"));
        Stage stage7 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene7 = new Scene(root7);
        stage7.setScene(scene7);
        stage7.show();
        System.out.println("c'est l'interface des listes des Vehicules && des Outils");


    }


    public void RefreshButton1(ActionEvent event) throws IOException{
        if(event.getSource()==boutactualiser) {
            coulid.setCellValueFactory(new PropertyValueFactory<>("id"));
            coulnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            coulquantité.setCellValueFactory(new PropertyValueFactory<>("quantité"));
            coulprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tvoutils.getItems().setAll(OutilsListe());
        }
    }

    public void switchToModifier() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/ModifierOutils.fxml"));
        Parent rt = loader.load();
        ModifierOutilsController controller = loader.getController();
        controller.initData(tvoutils.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSupprimer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/SupprimerOutils.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.show();


    }


}

