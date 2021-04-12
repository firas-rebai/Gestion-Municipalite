package PFA.Materiel2.GUIsMateriel;
import Materiel.ModuleMateriel.MaterielOutils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Materiel.ServiceMateriel.Outils;
import java.io.IOException;
import java.util.regex.Pattern;

public class ajouterOutilsController {
    @FXML
    TextField tfid,tfquantité,tfprix,tfnom;
    @FXML
    Button btnajouter,retour;
    @FXML
    Label lbid,lbprix,lbquantité,lbnom;



    String idPattern = "[0-9]*";
    String prixPattern="[0-9]*.[0-9]*";
    String nomPattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String quantitéPattern = "[0-9]*";
    boolean verif = true;

    public void ajouterOutils  (ActionEvent event) throws IOException {

        if (!Pattern.matches(idPattern, tfid.getText()) || tfid.getText().isEmpty()) {
            lbid.setVisible(true);

        }else{
            lbid.setVisible(false);
        }

        if (!Pattern.matches(prixPattern, tfprix.getText()) || tfprix.getText().isEmpty()) {
            lbprix.setVisible(true);

        }else{
            lbprix.setVisible(false);
        }

        if (!Pattern.matches(nomPattern, tfnom.getText()) || tfnom.getText().isEmpty()) {
            lbnom.setVisible(true);

        }else{
            lbnom.setVisible(false);
        }

        if (!Pattern.matches(quantitéPattern, tfquantité.getText()) || tfquantité.getText().isEmpty()) {
            lbquantité.setVisible(true);

        }else{
            lbquantité.setVisible(false);
        }


        if (verif && Pattern.matches(idPattern, tfid.getText()) &&
                Pattern.matches(prixPattern, tfprix.getText())&&
                Pattern.matches(nomPattern, tfnom.getText())
                && Pattern.matches(quantitéPattern, tfquantité.getText()) &&
                event.getSource()==btnajouter){
            MaterielOutils p = new MaterielOutils(Integer.parseInt(tfid.getText()),Integer.parseInt(tfquantité.getText()),
                    Double.parseDouble(tfprix.getText()),tfnom.getText());
                    Outils.Ajouter(p);

            Stage stage = (Stage) btnajouter.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés d'ajout");
            alert.setHeaderText(null);
            alert.setContentText("l'ajout est fait avec succés");
            alert.show();
        }

    }

    public void retour3() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }

}

