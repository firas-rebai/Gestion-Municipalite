package PFA.login_mainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class login {
    @FXML
    Button loginButton;
    @FXML
    TextField nomTextField, passwordTextField;
    @FXML
    Label errorLabel;
    
    public void pressLogin (ActionEvent event) {
        String query = "select count(*) from compte where nomutilisateur like '" + nomTextField.getText() + "' and password like '" + passwordTextField.getText() + "'";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            res.next();
            if (res.getInt(1) == 0){
                errorLabel.setVisible(true);
            }else {
                switchToMainMenu(event);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public void switchToMainMenu (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
