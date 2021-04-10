package PFA.GestionIntervention.Services;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionPersonnel.Modules.Personnel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class InterventionServices {
    public static void Ajouter(Intervention i){
        String SQLquery = "insert into INTERVENTION values(intervention_seg.nextval,'" + i.getNom() + "','" + new java.sql.Date(i.getDateBedut().getTime())+"','" + new java.sql.Date(i.getDateBedut().getTime()) + "',"+i.getBudget()+",'" + i.getAdresse() + "'";
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
        String query = "delete from INTERVENTION where id = " + id ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    
    public static void Modifier(Intervention i) {
        String query = String.format("update interventoin set " +
                "id = %d," +
                "nom= %s," +
                "dateDebut = %b," +
                "dateFin = %b," +
                "budget = %f" +
                "adresse = %s" +
                " WHERE id = %d",i.getId(),i.getNom(),i.getDateBedut(),i.getDateFin(),i.getBudget(),i.getAdresse(),i.getId());
        
        
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static List<Intervention> parseInterventionListe() {
        List<Intervention> liste = new ArrayList<>();
        String query = "SELECT * FROM INTERVENTION";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                liste.add(new Intervention(res.getInt("id"),res.getString("nom"),res.getDate("dateDebut"),res.getDate("dateFin"),res.getFloat("budget"),res.getString("adresse")));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return liste;
    }
    
    
    
    public  static List<Intervention> Recherche(String term){
        List<Intervention> liste = new ArrayList<>();
        String query = String.format("select * from intervention where lower(nom) LIKE lower('%%s%') or lower(adresse) LIKE lower('%%s%')",term,term);
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                liste.add(new Intervention(res.getInt("id"),res.getString("nom"),res.getDate("dateDebut"),res.getDate("dateFin"),res.getFloat("budget"),res.getString("adresse")));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
}
