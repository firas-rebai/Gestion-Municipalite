package PFA.Materiel.GUIsMateriel;

import Materiel.ModuleMateriel.MaterielOutils;
import Materiel.ModuleMateriel.MaterielVehicule;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    TableColumn<MaterielOutils,Double> coulprix;
    @FXML
    Label lboutilstitle;
    @FXML
    AnchorPane anchro;
    @FXML
    TextField toufrecherche;

    public void initialize(URL url, ResourceBundle resource){
        coulid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coulnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coulprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        coulquantité.setCellValueFactory(new PropertyValueFactory<>("quantité"));
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

}
