package PFA.Doleance.Doleance;
import PFA.Doleance.Module.ModuleDoleance;
import PFA.Doleance.Service.DoleanceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

import static PFA.dbConnection.dbConnection.getOracleConnection;


public class ListeDoleanceController implements Initializable {
    @FXML
    TableView<ModuleDoleance> tvdoleance;
    @FXML
    TableColumn<ModuleDoleance,Integer> colid;
    @FXML
    TableColumn<ModuleDoleance,String> colsujet;
    @FXML
    Button btnretour,btnactualiser,btnrecherche,btnajouter,btndetails,btnsupprimer;
    @FXML
    TextField tfrecherche;

    public void initialize(URL url, ResourceBundle resource) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        tvdoleance.getItems().setAll(doleanceListe());

    }

    private List<ModuleDoleance> doleanceListe() {
        List<ModuleDoleance> data = new ArrayList<>();
        String SQLquery = "SELECT * from doleance";
        try {
            Connection connection = getOracleConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new ModuleDoleance(
                        rs.getString("description"),
                        rs.getInt("id"),
                        rs.getString("sujet") ,
                        rs.getString("datedol")));

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;

    }


    public void switchToAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/AjouterDoleance.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.show();


    }
    public void switchToDetails() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/DetailsDoleance.fxml"));
        Parent rt = loader.load();
        DetailsDoleance controller = loader.getController();
        controller.initData(tvdoleance.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        stage.setScene(scene);
        stage.show();


    }

    public void retour(){

    }
    public void switchToSupprimer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fxmls/SupprimerDoleance.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.show();


    }

    public void RefreshButton(ActionEvent event) throws IOException{
        if(event.getSource()==btnactualiser) {
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
            tvdoleance.getItems().setAll(doleanceListe());
        }
    }


    public void rechercheButton(){
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        tvdoleance.getItems().setAll(DoleanceService.Recherche(tfrecherche.getText()));
        if (tfrecherche.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("echec de recherche");
            alert.setHeaderText(null);
            alert.setContentText("svp entrer un id pour faite la recherche");
            alert.show();
        }
    }

}
