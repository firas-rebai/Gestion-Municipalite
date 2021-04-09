package PFA.GestionEquipe.GUIs;

import PFA.GestionEquipe.Modules.Equipe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ListeEquipe implements Initializable {


    @FXML
    TreeTableView<Equipe> ListeEquip;
    @FXML
    TreeTableColumn<Equipe, String> NomColumn;
    @FXML
    TreeTableColumn<Equipe, String> SpecialiteColumn;
    @FXML
    TreeTableColumn<Equipe, String> ChefColumn;
    @FXML
    Button SupprimerButton, ModifierButton, AjouterButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpecialiteColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("specialite"));
        NomColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
        ChefColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("chef"));
    }
}
