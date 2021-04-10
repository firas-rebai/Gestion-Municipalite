package PFA.Materiel.GUIsMateriel;
import java.sql.Connection;
import java.sql.Statement;
import Materiel.ModuleMateriel.MaterielVehicule;
import Materiel.ServiceMateriel.Vehicule;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import static DataBaseConnection.OracleConnection.getOracleConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import java.net.URL;
public class SuppressionVehiculeController implements Initializable {

    @FXML
    Button btnannuler,btnconfirmer;
    @FXML
    ChoiceBox <Integer> choicematricule;



    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select matricule from materielvehicule");
            while (res.next()) {
                choicematricule.getItems().add(res.getInt("matricule"));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void confirmerButtonSupprimer (){

      Vehicule.Suprrimer(choicematricule.getValue());
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
