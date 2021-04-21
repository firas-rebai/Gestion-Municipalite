package PFA.MaterielFiras.ServiceMateriel;

import java.sql.SQLException;

import PFA.MaterielFiras.ModuleMateriel.Vehicule;

import java.sql.Connection;
import java.sql.Statement;


import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class Vehicules {
    
    
    public static void Ajouter(Vehicule p) {
        String SQLquery = String.format("insert into vehicule values( " +
                "vehicule_seq.nextval,'%s','%s',%d)", p.getNom(), p.getModel(), p.getMatricule());
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
    
    
    public static void Modifier(Vehicule p) {
        String SQLquery = String.format("update vehicule set " +
                "nomVehicule = '%s'," +
                "modelVehicule = '%s'," +
                "matriculeVehicule = %d " +
                "where idVehicule = %d", p.getNom(), p.getModel(), p.getMatricule(), p.getId());
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
    
    public static List<Vehicule> Recherche(String term) {
        List<Vehicule> liste = new ArrayList<>();
        String query = "select * from vehicule where lower(modelVehicule) like lower('%" + term + "%') or lower(nomVehicule) like lower('%" + term + "%')";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                liste.add(new Vehicule(rs.getInt("idvehicule"),
                        rs.getInt("matriculevehicule"),
                        rs.getString("modelvehicule"),
                        rs.getString("nomvehicule")));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static void Suprrimer(int matricule) {
        String query = "delete from VEHICULE where MATRICULEVEHICULE = " + matricule;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    
}
