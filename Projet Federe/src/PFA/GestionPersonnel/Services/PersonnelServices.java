package PFA.GestionPersonnel.Services;

import PFA.GestionPersonnel.Modules.Personnel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class PersonnelServices {
    public static void Ajouter(Personnel p){
        String SQLquery = "insert into Personnel values(seq_person.nextval" + ",'" + p.getNom() + "','" + p.getPrenom() + "','" + p.getCIN() + "','" + p.getEmail() + "','" + p.getNumTel() + "','" + p.getSalaire() + "','" + p.getPoste() + "','" + p.getEquip() + "','" + p.getDateNaissaince() + "')";
        Statement statement;
        try {
            Connection connection = getOracleConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SQLquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Suprrimer(int id) {
        String query = "delete from personnel where id = " + id ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void Modifier(Personnel p) {
        String query ;

        query = "update personnel set " +
                "nom = "+ "'" +p.getNom()+ "'" +",prenom ="+ "'" +p.getPrenom()+ "'" +",cin ="+ "'" +p.getCIN()+ "'" +" ,email = "+ "'" +p.getEmail()+ "'" +",poste ="+ "'" +p.getPoste()+ "'" +",equip ="+ "'" +p.getEquip()+ "'" +",salaire ="+ "'" +p.getSalaire()+ "'" +",numTel ="+ "'" +p.getNumTel()+ "'" +",dateNaissaince ="+ "'" +p.getDateNaissaince()+ "'" +
                "where id = "+ "'" +p.getId()+ "'" +"";

        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public  static List<Personnel> Recherche(String term){
        List<Personnel> liste = new ArrayList<>();
        String query = "select * from Personnel" +
                " WHERE lower(nom) LIKE lower('%" + term + "%')" +
                " OR lower(prenom) LIKE lower('%" + term + "%')" +
                " OR cin ="+ "'" + term + "'";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                liste.add(new Personnel(res.getInt("id"),res.getString("nom"),res.getString("prenom"),res.getInt("CIN"),res.getString("email"),res.getInt("numTel"),res.getFloat("salaire"),res.getString("poste"),res.getString("equip"),res.getString("dateNaissaince")));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }

}
