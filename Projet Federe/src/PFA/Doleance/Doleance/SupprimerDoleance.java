package PFA.Doleance.Doleance;

import Service.DoleanceService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static DataBaseConnection.OracleConnection.getOracleConnection;

public class SupprimerDoleance implements Initializable  {
    @FXML
    Button btnconfirmer,btnannuler;
    @FXML
    ChoiceBox<Integer> box;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select id from doleance");
            while (res.next()) {
                box.getItems().add(res.getInt("id"));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void confirmerButtonSupprimer (){

        DoleanceService.Suprrimer(box.getValue());
        Stage stage = (Stage) btnconfirmer.getScene().getWindow();
        stage.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("succés de suppression");
        alert.setHeaderText(null);
        alert.setContentText("suppression faite avec succés");
        alert.show();
    }
    public void annuler(){
        Stage stage = (Stage) btnannuler.getScene().getWindow();
        stage.close();
    }


}
