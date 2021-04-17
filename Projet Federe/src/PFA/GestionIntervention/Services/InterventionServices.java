package PFA.GestionIntervention.Services;

import PFA.GestionIntervention.Modules.Intervention;
import PFA.GestionIntervention.Modules.OutilsUtilise;
import PFA.GestionIntervention.Modules.PersonnelMin;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class InterventionServices {
    public static void Ajouter(Intervention i) {
        String SQLquery = "insert into INTERVENTION values(INTERVENTION_SEQ.nextval,'" + i.getNom() + "'," + "TO_DATE('2003/05/03 21:02:44','yyyy/mm/dd hh24:mi:ss')" + "," + "TO_DATE('2003/05/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss')" + "," + i.getBudget() + ",'" + i.getAdresse() + "'";
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
        String query = "delete from INTERVENTION where IDINTERVENTION = " + id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    
    public static void Modifier(Intervention i) {
        String query = String.format("update intervention set " +
                "idintervention = %d," +
                "nomintervention= '%s'," +
                "dateDebutintervention = %b," +
                "dateFinintervention = %b," +
                "budgetintervention = %f" +
                "adresseintervention = '%s'" +
                " WHERE idintervention = %d", i.getId(), i.getNom(), i.getDateBedut(), i.getDateFin(), i.getBudget(), i.getAdresse(), i.getId());
        
        
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
        String fetchPersonnel = "SELECT * from intervention_personnel where idIntervention = ";
        String fetchVehicule = "SELECT * from intervention_vehicule where idIntervention = ";
        String fetchOutil = "SELECT * from intervention_outil where idIntervention = ";
        ArrayList<PersonnelMin> listePersonnel = new ArrayList<>();
        ArrayList<Vehicule> listeVehicule = new ArrayList<>();
        ArrayList<OutilsUtilise> listeOutil = new ArrayList<>();
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            Statement stat = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                ResultSet rsPersonnel = statement.executeQuery(fetchPersonnel + res.getString("idIntervention"));
                ResultSet rsVehicule = statement.executeQuery(fetchVehicule + res.getString("idIntervention"));
                ResultSet rsOutil = statement.executeQuery(fetchOutil + res.getString("idIntervention"));
                listePersonnel.clear();
                listeVehicule.clear();
                listeOutil.clear();
                while (rsPersonnel.next()) {
                    listePersonnel.add(new PersonnelMin(rsPersonnel.getInt("idPersonnel"), rsPersonnel.getString("nom"), rsPersonnel.getString("prenom"), rsPersonnel.getString("poste")));
                }
                while (rsVehicule.next()) {
                    listeVehicule.add(new Vehicule(rsVehicule.getInt("idVehicule"), rsVehicule.getInt("matricule"), rsVehicule.getString("model"), rsVehicule.getString("nom")));
                }
                while (rsOutil.next()) {
                    listeOutil.add(new OutilsUtilise(new Outil(rsOutil.getInt("idOutil"), rsOutil.getInt("quantite"), rsOutil.getString("nom"), rsOutil.getInt("consumable")), rsOutil.getInt("quantite")));
                }
                liste.add(new Intervention(res.getInt("idIntervention"), res.getString("nomIntervention"), res.getDate("dateDebutIntervention").toLocalDate(), res.getDate("dateFinIntervention").toLocalDate(), res.getFloat("budgetIntervention"), res.getString("adresseIntervention"), listePersonnel, listeVehicule, listeOutil));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return liste;
    }
    
    public static List<PersonnelMin> ParsePersonnelListe() {
        List<PersonnelMin> liste = new ArrayList<>();
        String query = "select * from Personnel " +
                "where IDPERSONNEL NOT IN (select IDPERSONNEL from INTERVENTION_PERSONNEL)";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                liste.add(new PersonnelMin(res.getInt("idpersonnel"), res.getString("nomPersonnel"), res.getString("prenomPersonnel"), res.getString("postePersonnel")));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static List<Vehicule> parseVehiculeList() {
        List<Vehicule> liste = new ArrayList<>();
        String query = "select * from vehicule where idVehicule not in (select idvehicule from INTERVENTION_VEHICULE)";
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
//    public  static List<Intervention> Recherche(String term){
//        List<Intervention> liste = new ArrayList<>();
//        String query = String.format("select * from intervention where lower(nom) LIKE lower('%%s%') or lower(adresse) LIKE lower('%%s%')",term,term);
//        try {
//            Connection connection = getOracleConnection();
//            Statement statement = connection.createStatement();
//            ResultSet res = statement.executeQuery(query);
//            while(res.next()){
//                liste.add(new Intervention(res.getInt("id"),res.getString("nom"),res.getDate("dateDebut"),res.getDate("dateFin"),res.getFloat("budget"),res.getString("adresse")));
//            }
//            res.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return liste;
//    }
}
