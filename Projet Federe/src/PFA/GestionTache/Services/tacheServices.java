package PFA.GestionTache.Services;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionTache.Module.Tache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class tacheServices {
    public static void Suprrimer(int id) {
        String query = "delete from tache where idtache = " + id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static ArrayList<Tache> parsetacheListe() {
        ArrayList<Tache> liste = new ArrayList<>();
        String query = "select * from tache";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("idtache");
                ResultSet resPersonnel = statement1.executeQuery("select * from PERSONNEL where IDPERSONNEL = " + id);
                resPersonnel.next();
                Personnel personnel = new Personnel(res.getInt("idPersonnel")
                        , res.getString("nomPersonnel")
                        , res.getString("prenomPersonnel")
                        , res.getInt("cinPersonnel")
                        , res.getFloat("salaire")
                        , res.getString("postePersonnel")
                        , res.getDate("datenaissancepersonnel").toLocalDate());
                resPersonnel.close();
                liste.add(new Tache(id, res.getString("nomtache"), res.getString("description"), personnel));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static void ajouter(Tache tache) {
        String query;
        if (tache.getPersonnel() == null){
            query = "insert into tache (IDTACHE, NOMTACHE, DESCRIPTION) values (tache_seq.nextval,'" + tache.getNom() + "','" + tache.getDescription() + "')";
        }else
            query = "insert into tache (IDTACHE, NOMTACHE, IDPERSONNEL, DESCRIPTION) values (tache_seq.nextval,'" + tache.getNom() + "'," + tache.getPersonnel().getId() + ",'" + tache.getDescription() + "')";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static void Modifier(Tache tache) {
        String query;
        if (tache.getPersonnel() == null){
            query = "update TACHE set NOMTACHE = '" + tache.getNom() + "'," +
                    "DESCRIPTION = " + tache.getDescription() +
                    " where IDTACHE =" + tache.getId();
        }else
             query = "update TACHE set NOMTACHE = '" + tache.getNom() + "'," +
                    "IDPERSONNEL = " + tache.getPersonnel().getId() + "," +
                    "DESCRIPTION = " + tache.getDescription() +
                    " where IDTACHE =" + tache.getId();
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
}
