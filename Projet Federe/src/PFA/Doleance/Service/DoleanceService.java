package PFA.Doleance.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Module.ModuleDoleance;
import static DataBaseConnection.OracleConnection.getOracleConnection;

public class DoleanceService {
    public static void Ajouter(ModuleDoleance p){
        String SQLquery = "insert into doleance values('" + p.getDescription() + "'," + p.getId() +
                ",'" + p.getSujet() + "','" + p.getDate() + "')";
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

    public static void Suprrimer(Integer id) {
        String query = "delete from doleance where id = " + id ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public  static List<ModuleDoleance> Recherche(String term){
        List<ModuleDoleance> liste = new ArrayList<>();
        String query = "select * from doleance WHERE id ='" + term + "'";
        System.out.println(term.getClass());
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                liste.add(new ModuleDoleance(
                        rs.getString("description"),
                        rs.getInt("id"),
                        rs.getString("sujet") ,
                        rs.getString("datedol")));

            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }

}
