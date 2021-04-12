package PFA.Materiel2.GUIsMateriel;

import Materiel.ModuleMateriel.MaterielOutils;
import Materiel.ModuleMateriel.MaterielVehicule;
import Materiel.ServiceMateriel.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class ModifierOutilsController {
    @FXML
    TextField tfprix,tfquantité,tfnom;
    @FXML
    Label lbid,lbprix,lbnom,lbquantité;
    @FXML
    Button btnmodifier,retour;


    String prixPattern="[0-9]*.[0-9]*";
    String nomPattern = "[a-zA-Z]{3}[a-zA-Z ]*";
    String quantitéPattern = "[0-9]*";
    boolean verif = true;

    public void ModifierButton(ActionEvent event) throws IOException {
        if (!Pattern.matches(prixPattern, tfprix.getText()) || tfprix.getText().isEmpty()) {
            lbprix.setVisible(true);
        } else {
            lbprix.setVisible(false);
        }


        if (!Pattern.matches(nomPattern, tfnom.getText()) || tfnom.getText().isEmpty()) {
            lbnom.setVisible(true);
        } else {
            lbnom.setVisible(false);
        }


        if (!Pattern.matches(quantitéPattern, tfquantité.getText()) || tfquantité.getText().isEmpty()) {
            lbquantité.setVisible(true);
        } else {
            lbquantité.setVisible(false);
        }

        if (verif &&
                Pattern.matches(prixPattern, tfprix.getText()) &&
                Pattern.matches(nomPattern, tfnom.getText()) &&
                Pattern.matches(quantitéPattern, tfquantité.getText())
                && event.getSource() == btnmodifier) {
            MaterielOutils p = new MaterielOutils(Integer.parseInt(lbid.getText()), Integer.parseInt(tfquantité.getText()),
                    Double.parseDouble(tfprix.getText()), tfnom.getText());
            Outils.Modifier(p);

            Stage stage = (Stage) btnmodifier.getScene().getWindow();
            stage.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succés de modification");
            alert.setHeaderText(null);
            alert.setContentText("modification faite avec succés");
            alert.show();

        }
    }

        public void initData(MaterielOutils p) {
            tfnom.setText(p.getNom());
            tfprix.setText(String.valueOf(p.getPrix()));
            tfquantité.setText(String.valueOf(p.getQuantité()));
            lbid.setText(String.valueOf(p.getId()));
        }

    public void retour4() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();

    }


}
