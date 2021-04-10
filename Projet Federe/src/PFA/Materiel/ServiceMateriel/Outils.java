package PFA.Materiel.ServiceMateriel;

import Materiel.ModuleMateriel.MaterielVehicule;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static DataBaseConnection.OracleConnection.getOracleConnection;

public class Outils {

    public static void Ajouter( p){
        String SQLquery = "insert into materieloutils values(" + p.getId() + ",'" + p.getModel() +
                "'," + p.getMatricule() + "," + p.getQuantité() + "," + p.getPrix() + ",'" + p.getNom() + "','" +
                p.getDateDachat() + "')";
        Statement statement;
        try {
            Connection connection = getOracleConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SQLquery);
            System.out.println("ajouté");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
