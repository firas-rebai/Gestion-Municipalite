package GestionDoleance.GUIs;
import javafx.scene.control.Alert;
import Service.DoleanceService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import Module.ModuleDoleance;
public class SupprimerDoleanceController {
@FXML
    Button confirmer,annuler;

    public void annuler(){
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
int iddoleance;
    public void confirmer (){
        DoleanceService.Supprimer(iddoleance);
        Stage stage = (Stage) confirmer.getScene().getWindow();
        stage.close();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("succés de suppression");
        alert.setHeaderText(null);
        alert.setContentText("suppression faite avec succés");
        alert.show();
    }

}

