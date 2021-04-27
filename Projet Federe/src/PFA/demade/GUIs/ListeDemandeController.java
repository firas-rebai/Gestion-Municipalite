package PFA.demade.GUIs;
import Module.DemandeModu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

import static DataBaseConnection.OracleConnection.getOracleConnection;

public class ListeDemandeController implements Initializable{
    @FXML
    AnchorPane anchro;
    @FXML
    TableView<DemandeModu> tvdem;
    @FXML
    TableColumn<DemandeModu,Integer> colid;
    @FXML
    TableColumn<DemandeModu,Integer> colcin;
    @FXML
    TableColumn<DemandeModu,String> coltype;
    @FXML
    TableColumn<DemandeModu,String> colnom;
    @FXML
    Button ajouter,details,retour,recherche,actualiser,modifier,supprimer;
    @FXML
    TextField tfrecherche;

    public void initialize(URL url, ResourceBundle resource){
        refresh();
        anchro.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY)){
                modifier.setDisable(true);
                supprimer.setDisable(true);
                details.setDisable(true);
                tvdem.getSelectionModel().clearSelection();
            }
        });

        tfrecherche.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY)){
                modifier.setDisable(true);
                supprimer.setDisable(true);
                details.setDisable(true);
                tvdem.getSelectionModel().clearSelection();
            }
        });

        tvdem.setOnMouseClicked((MouseEvent event)->{
            if (event.getButton().equals(MouseButton.PRIMARY) && !tvdem.getSelectionModel().isEmpty()){
                modifier.setDisable(false);
                supprimer.setDisable(false);
                details.setDisable(false);
            }
        });
    }

    private List<DemandeModu> DemandeListe() {
        List<DemandeModu> data = new ArrayList<>();
        String SQLquery = "SELECT * from dem ";
        try {
            Connection connection = getOracleConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new DemandeModu(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("cin"),
                        rs.getString("adresse"),
                        rs.getInt("numtel"),
                        rs.getInt("id"),
                        rs.getDate("datedem").toLocalDate(),
                        rs.getString("typedem"),
                        rs.getString("descreption")));

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;

    }

    private void refresh(){
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("TypeDem"));
        colcin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        tvdem.getItems().setAll(DemandeListe());
    }


    public void switchToAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/AjouterDemande.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        stage1.setTitle("Ajouter une Demande");
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.setResizable(false);
        stage1.showAndWait();
        refresh();


    }

    public void retour(){

    }
}
