package PFA.Materiel2.ServiceMateriel;
import Materiel.ModuleMateriel.MaterielOutils;
import Materiel.ModuleMateriel.MaterielVehicule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static DataBaseConnection.OracleConnection.getOracleConnection;

public class Outils {
    public static void Ajouter(MaterielOutils p){
        String SQLquery = "insert into materieloutils values(" + p.getId() +
                ",'" + p.getNom() + "'," + p.getQuantité() +
                "," + p.getPrix() + ")";
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


    public static List<MaterielOutils>  Recherche (String term){
        List<MaterielOutils> liste = new ArrayList<>();
        String query = "select * from materieloutils WHERE lower(nom) LIKE lower('% " + term + "%')"  +
                " OR id =" + "'" + term + "'" +
                " OR quantité ="+ "'" + term + "'" + " OR prix ="+ "'" + term + "'";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                liste.add(new MaterielOutils( rs.getInt("id"),
                        rs.getInt("quantité"),
                        rs.getDouble("prix"),
                        rs.getString("nom")));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }


    public static void Modifier(MaterielOutils p) {
        String SQLquery = "update materieloutils set  " + "  nom = '" + p.getNom() +
                "', quantité = " +
                p.getQuantité() + ", prix = " + p.getPrix() +
                " where id = " + p.getId() + "";
        System.out.println(SQLquery);
        Statement statement;
        try {
            Connection connection = getOracleConnection();

            statement = connection.createStatement();

            statement.executeUpdate(SQLquery);



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void Suprrimer(Integer id) {
        String query = "delete from materieloutils where id = " + id ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

