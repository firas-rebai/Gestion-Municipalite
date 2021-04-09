package PFA.GestionPersonnel.GUIs;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionPersonnel.Services.PersonnelServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class ListeController{
    @FXML
    private TableView<Personnel> ListePersonnelTable;
    @FXML
    private TableColumn<Personnel, String> nomColumn;
    @FXML
    private TableColumn<Personnel, String> prenomColumn;
    @FXML
    private TableColumn<Personnel, Integer> CINColumn;
    @FXML
    private TableColumn<Personnel, String> posteColumn;
    @FXML
    private TableColumn<Personnel, String> equipColumn;
    @FXML
    private Button detailsButton, modifierButton, supprimerButton;
    @FXML
    private TextField rechercheTextField;
    @FXML
    private AnchorPane ListePersonnel;
    
    public void refreshList(List<Personnel> e){
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        CINColumn.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        posteColumn.setCellValueFactory(new PropertyValueFactory<>("poste"));
        equipColumn.setCellValueFactory(new PropertyValueFactory<>("equip"));
    
        ListePersonnelTable.getItems().setAll(e);
    }

    public void initListe() {
        refreshList(parseUserList());
    
        ListePersonnelTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && !ListePersonnelTable.getSelectionModel().isEmpty()){
                detailsButton.setDisable(false);
                modifierButton.setDisable(false);
                supprimerButton.setDisable(false);
            }
        });
        rechercheTextField.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                ListePersonnelTable.getSelectionModel().clearSelection();
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });
        ListePersonnel.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                ListePersonnelTable.getSelectionModel().clearSelection();
                detailsButton.setDisable(true);
                modifierButton.setDisable(true);
                supprimerButton.setDisable(true);
            }
        });

    }


    public void detailsButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/detailspersonnel.fxml"));
        Parent rt = loader.load();
        DetailsController cntrl = loader.getController();
        cntrl.initData(ListePersonnelTable.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        stage.setScene(scene);
        stage.show();
    }


    private List<Personnel> parseUserList() {
        // parse and construct Personnel list by looping ResultSet rs
        // and return the list
        List<Personnel> liste = new ArrayList<>();
        String query = "SELECT * FROM Personnel";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                liste.add(new Personnel(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("CIN"), rs.getString("email"), rs.getInt("numTel"), rs.getFloat("salaire"), rs.getString("poste"), rs.getString("equip"), rs.getString("dateNaissaince")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    public void rechercheButton(){
        refreshList(PersonnelServices.Recherche(rechercheTextField.getText()));
    }

    public void switchToAjouter() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ajouterpersonnel.fxml"));
        Parent rt = loader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(rt);
        stage1.setScene(scene1);
        stage1.showAndWait();
        refreshList(parseUserList());
    }


    public void switchToModifier() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/modifierpersonnel.fxml"));
        Parent rt = loader.load();
        ModifyController controller = loader.getController();
        controller.initData(ListePersonnelTable.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        Scene scene = new Scene(rt);
        stage.setScene(scene);
        stage.showAndWait();
        refreshList(parseUserList());
    }


    public void SuprrimerButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/supression.fxml"));
        Parent root = loader.load();
        SupprimerController controller = loader.getController();
        controller.setP(ListePersonnelTable.getSelectionModel().getSelectedItem());
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        refreshList(parseUserList());
    }


    
    
    

    public void retour(){
    
    }

}
