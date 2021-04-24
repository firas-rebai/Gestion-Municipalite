package PFA.Doleance.Doleance;

import PFA.Doleance.Module.ModuleDoleance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class DetailsDoleance {
    @FXML
    Label lblid,lblsujet,lbldate,lbldes;
    @FXML
    Button retour;


    public void initData(ModuleDoleance p) {
        lblid.setText(String.valueOf(p.getId()));
        lblsujet.setText(p.getSujet());
        lbldate.setText(p.getDate());
        lbldes.setText(p.getDescription());

    }

    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
    }
}
