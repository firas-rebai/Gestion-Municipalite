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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class InterventionServices {
    public static void Ajouter(Intervention i) {
        String query1;
        String query3;
        String query2;
        String query = "insert into INTERVENTION values(INTERVENTION_SEQ.nextval,'" + i.getNom() + "'," + "TO_DATE('" + i.getDateBedut().toString() + "','yyyy-mm-dd')" + "," + "TO_DATE('" + i.getDateFin().toString() + "','yyyy-mm-dd')" + "," + i.getBudget() + ",'" + i.getAdresse() + "')";
        Statement statement;
        Statement statement1;
        try {
            Connection connection = getOracleConnection();
            statement1 = connection.createStatement();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            for (OutilsUtilise o : i.getOutilsUtilises()) {
                query1 = "insert into INTERVENTION_OUTIL values(" + o.outils.getId() + "," + o.outils.getConsumable() + ",INTERVENTION_SEQ.currval ," + o.quantite + ",'" + o.outils.getNom() + "')";
                statement1.executeUpdate(query1);
            }
            for (PersonnelMin p : i.getEquipe()) {
                query2 = "insert into INTERVENTION_PERSONNEL values(" + p.getId() + ",INTERVENTION_SEQ.currval,'" + p.getNom() + "','" + p.getPrenom() + "','" + p.getPoste() + "')";
                statement.executeUpdate(query2);
            }
            for (Vehicule v : i.getVehicules()) {
                query3 = "insert into INTERVENTION_VEHICULE values(" + v.getId() + ",INTERVENTION_SEQ.currval,'" + v.getModel() + "','" + v.getNom() + "','" + v.getMatricule() + "'," + v.getPrix() + ",to_date('" + v.getDateDachat().toString() + "','yyyy-mm-dd'))";
                statement.executeUpdate(query3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void Suprrimer(int id) {
        String query = "delete from INTERVENTION where IDINTERVENTION = " + id;
        String query1 = "delete from INTERVENTION_VEHICULE where IDINTERVENTION = " + id;
        String query2 = "delete from INTERVENTION_PERSONNEL where IDINTERVENTION = " + id;
        String query3 = "delete from INTERVENTION_OUTIL where IDINTERVENTION = " + id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.execute(query1);
            statement.execute(query2);
            statement.execute(query3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    
    public static void Modifier(Intervention i) {
        String query = String.format("update intervention set " +
                "idintervention = %d," +
                "nomintervention= '%s'," +
                "dateDebutintervention = TO_DATE('%s','DD-MM-YYYY')," +
                "dateFinintervention = TO_DATE('%s','DD-MM-YYYY')," +
                "budgetintervention = %f" +
                "adresseintervention = '%s'" +
                " WHERE idintervention = %d", i.getId(), i.getNom(), i.getDateBedut().toString(), i.getDateFin().toString(), i.getBudget(), i.getAdresse(), i.getId());
        
        
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
        String id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                id = res.getString("idintervention");
                Statement statement1 = connection.createStatement();
                Statement statement2 = connection.createStatement();
                Statement statement3 = connection.createStatement();
                ResultSet rsPersonnel = statement1.executeQuery(fetchPersonnel + id);
                ResultSet rsVehicule = statement2.executeQuery(fetchVehicule + id);
                ResultSet rsOutil = statement3.executeQuery(fetchOutil + id);
                listePersonnel.clear();
                listeVehicule.clear();
                listeOutil.clear();
                while (rsPersonnel.next()) {
                    listePersonnel.add(new PersonnelMin(rsPersonnel.getInt("idPersonnel"), rsPersonnel.getString("nom"), rsPersonnel.getString("prenom"), rsPersonnel.getString("poste")));
                }
                rsPersonnel.close();
                while (rsVehicule.next()) {
                    listeVehicule.add(new Vehicule(rsVehicule.getInt("idVehicule"), rsVehicule.getInt("matricule"), rsVehicule.getString("model"), rsVehicule.getString("nom")));
                }
                rsVehicule.close();
                while (rsOutil.next()) {
                    listeOutil.add(new OutilsUtilise(new Outil(rsOutil.getInt("idOutil"), rsOutil.getInt("quantite"), rsOutil.getString("nom"), rsOutil.getInt("consumable")), rsOutil.getInt("quantite")));
                }
                rsOutil.close();
                liste.add(new Intervention(Integer.parseInt(id), res.getString("NOMINTERVENTION"), res.getDate("DATEDEBUTINTERVENTION").toLocalDate(), res.getDate("DATEFININTERVENTION").toLocalDate(), res.getFloat("budget"), res.getString("adresse"), listePersonnel, listeVehicule, listeOutil));
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
    
    public static List<PersonnelMin> ParsePersonnelListe(LocalDate debut, LocalDate fin) {
        List<PersonnelMin> liste = new ArrayList<>();
        String query = "select * from Personnel " +
                "where IDPERSONNEL NOT IN " +
                "(select IDPERSONNEL from INTERVENTION_PERSONNEL where IDINTERVENTION IN " +
                "(select IDINTERVENTION from INTERVENTION " +
                "where to_date('" + debut.toString() + "','yyyy-mm-dd') between DATEDEBUTINTERVENTION and DATEFININTERVENTION))";
        System.out.println(query);
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
    
    public static List<Vehicule> parseVehiculeList(LocalDate debut, LocalDate fin) {
        List<Vehicule> data = new ArrayList<>();
        String SQLquery = "select * from VEHICULE " +
                "where IDVEHICULE NOT IN " +
                "(select IDVEHICULE from INTERVENTION_VEHICULE where IDINTERVENTION IN " +
                "(select IDINTERVENTION from INTERVENTION " +
                "where DATEDEBUTINTERVENTION between to_date('" + debut.toString() + "','yyyy-mm-dd') and to_date('" + fin.toString() + "','yyyy-mm-dd')))";
        try {
            Connection connection = getOracleConnection();
            
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new Vehicule(
                        rs.getInt("idVehicule"),
                        rs.getInt("matriculeVehicule"),
                        rs.getString("modelVehicule"),
                        rs.getString("nomVehicule"),
                        rs.getDate("dateachat").toLocalDate(),
                        rs.getFloat("prix")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static ArrayList<Vehicule> parseVehiculeList(LocalDate debut) {
        ArrayList<Vehicule> data = new ArrayList<>();
        String SQLquery = "SELECT * from VEHICULE where IDVEHICULE not in (select IDVEHICULE from INTERVENTION_VEHICULE where IDINTERVENTION in (select IDINTERVENTION from INTERVENTION where to_date('"+debut.toString()+"','yyyy-mm-dd') between DATEDEBUTINTERVENTION and DATEFININTERVENTION))";
        try {
            Connection connection = getOracleConnection();
            
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new Vehicule(
                        rs.getInt("idVehicule"),
                        rs.getInt("matriculeVehicule"),
                        rs.getString("modelVehicule"),
                        rs.getString("nomVehicule"),
                        rs.getDate("dateachat").toLocalDate(),
                        rs.getFloat("prix")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static List<Outil> parseOutilList() {
        List<Outil> liste = new ArrayList<>();
        String query = "select * from OUTIL";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                liste.add(new Outil(rs.getInt("idoutil"),
                        rs.getInt("quantiteoutil"),
                        rs.getString("nomoutil"),
                        rs.getInt("consumableoutil")));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static ArrayList<Outil> parseOutilList(LocalDate debut) {
        ArrayList<Outil> liste = new ArrayList<>();
        String query = "select * from OUTIL";
        String query2 = "select QUANTITE from INTERVENTION_OUTIL where IDINTERVENTION in" +
                "(select IDINTERVENTION from INTERVENTION where to_date('" + debut.toString() + "','yyyy-mm-dd') between DATEDEBUTINTERVENTION and DATEFININTERVENTION)";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int total = 0;
            while (rs.next()) {
                total = rs.getInt("quantiteoutil");
                ResultSet res = statement1.executeQuery(query2);
                int con = rs.getInt("consumableoutil");
                while(res.next()){
                    int i =res.getInt(1);
                    if (con == 0 && (total - i > 0))
                        total -= i;
                }
                res.close();
                liste.add(new Outil(rs.getInt("IDOUTIL"),
                        total,
                        rs.getString("nomoutil"),
                        con));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static List<Intervention> Recherche(String term) {
        List<Intervention> liste = new ArrayList<>();
        String query = "SELECT * FROM INTERVENTION WHERE lower(NOMINTERVENTION) like lower('" + term + "')";
        String fetchPersonnel = "SELECT * from intervention_personnel where idIntervention = ";
        String fetchVehicule = "SELECT * from intervention_vehicule where idIntervention = ";
        String fetchOutil = "SELECT * from intervention_outil where idIntervention = ";
        ArrayList<PersonnelMin> listePersonnel = new ArrayList<>();
        ArrayList<Vehicule> listeVehicule = new ArrayList<>();
        ArrayList<OutilsUtilise> listeOutil = new ArrayList<>();
        String id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                id = res.getString("idintervention");
                Statement statement1 = connection.createStatement();
                Statement statement2 = connection.createStatement();
                Statement statement3 = connection.createStatement();
                ResultSet rsPersonnel = statement1.executeQuery(fetchPersonnel + id);
                ResultSet rsVehicule = statement2.executeQuery(fetchVehicule + id);
                ResultSet rsOutil = statement3.executeQuery(fetchOutil + id);
                listePersonnel.clear();
                listeVehicule.clear();
                listeOutil.clear();
                while (rsPersonnel.next()) {
                    listePersonnel.add(new PersonnelMin(rsPersonnel.getInt("idPersonnel"), rsPersonnel.getString("nom"), rsPersonnel.getString("prenom"), rsPersonnel.getString("poste")));
                }
                rsPersonnel.close();
                while (rsVehicule.next()) {
                    listeVehicule.add(new Vehicule(rsVehicule.getInt("idVehicule"), rsVehicule.getInt("matricule"), rsVehicule.getString("model"), rsVehicule.getString("nom")));
                }
                rsVehicule.close();
                while (rsOutil.next()) {
                    listeOutil.add(new OutilsUtilise(new Outil(rsOutil.getInt("idOutil"), rsOutil.getInt("quantite"), rsOutil.getString("nom"), rsOutil.getInt("consumable")), rsOutil.getInt("quantite")));
                }
                rsOutil.close();
                liste.add(new Intervention(Integer.parseInt(id), res.getString("NOMINTERVENTION"), res.getDate("DATEDEBUTINTERVENTION").toLocalDate(), res.getDate("DATEFININTERVENTION").toLocalDate(), res.getFloat("budget"), res.getString("adresse"), listePersonnel, listeVehicule, listeOutil));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return liste;
    }
}
