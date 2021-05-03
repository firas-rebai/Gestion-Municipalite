package PFA.GestionProjet.Services;

import PFA.GestionPersonnel.Modules.Personnel;
import PFA.GestionPersonnel.Services.PersonnelServices;
import PFA.GestionProjet.Module.Projet;
import PFA.GestionTache.Module.Tache;
import PFA.MaterielFiras.ModuleMateriel.Outil;
import PFA.MaterielFiras.ModuleMateriel.Vehicule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class ProjetServices {
    
    public static ArrayList<Projet> parseProjetListe() {
        ArrayList<Projet> liste = new ArrayList<>();
        String fetchProjet = "select * from projet";
        int id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(fetchProjet);
            while (res.next()) {
                id = res.getInt("idprojet");
                liste.add(new Projet(id, res.getString("nomprojet"), res.getString("description"), fetchTacheWithID(id), res.getString("adresse"), res.getFloat("cout"), res.getDate("datedebut").toLocalDate(), res.getDate("datefin").toLocalDate(), fetchPersonnelWithID(id), fetchVehiculeWithID(id), fetchOutilWithID(id)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static ArrayList<Vehicule> fetchVehiculeWithID(int idProjet) {
        ArrayList<Vehicule> liste = new ArrayList<>();
        String query = "select * from vehicule where idvehicule in (select IDVEHICULE from PROJET_VEHICULE where IDPROJET = " + idProjet + ")";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                liste.add(new Vehicule(res.getInt("idvehicule"), res.getInt("matricule"), res.getString("model"), res.getString("nom")));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static ArrayList<Outil> fetchOutilWithID(int idProjet) {
        ArrayList<Outil> liste = new ArrayList<>();
        String query = "select * from OUTIL where IDOUTIL in (select IDOUTIL from PROJET_OUTIL where IDPROJET = " + idProjet + ")";
        int id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                id = res.getInt("idoutil");
                liste.add(new Outil(id, fetchQuantite(id), res.getString("nomoutil"), res.getInt("consumable")));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static int fetchQuantite(int idoutil) {
        String query = "select quantite from projet_outil where idoutil = " + idoutil;
        int quantite = -1;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                quantite = res.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return quantite;
    }
    
    
    public static ArrayList<Personnel> fetchPersonnelWithID(int idProjet) {
        ArrayList<Personnel> liste = new ArrayList<>();
        String query = "select * from PERSONNEL where IDPERSONNEL in (select IDPERSONNEL from PROJET_PERSONNEL where IDPROJET = " + idProjet + ")";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                liste.add(new Personnel(res.getInt("idpersonnel"), res.getString("nompersonnel"), res.getString("prenompersonnel"), res.getInt("cinpersonnel"), res.getFloat("salaire"), res.getString("poste"), res.getDate("datenaissance").toLocalDate()));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static ArrayList<Tache> fetchTacheWithID(int idProjet) {
        ArrayList<Tache> liste = new ArrayList<>();
        String query = "select * from TACHE where IDTACHE in (select IDTACHE from PROJET_TACHE where IDPROJET = " + idProjet + ")";
        try {
            int id;
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                id = res.getInt("idtache");
                liste.add(new Tache(id, res.getString("nom"), res.getString("description"), PersonnelServices.ParsePersonnel(id)));
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    public static void ajouter(Projet projet) {
        String query = "insert into PROJET (IDPROJET, NOMPROJET, DESCRIPTION, ADRESSE, DATEDEBUT, DATEFIN,COUT) VALUES (projet_seq.nextval,'" + projet.getNom() + "','" + projet.getDescription() + "','" + projet.getAdresse() + "',to_date('" + projet.getDateBedut().toString() + "','yyyy-mm-dd'),to_date('" + projet.getDateFin().toString() + "','yyyy-mm-dd')," + projet.getCout() + ")";
        int id = projet.getId();
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
            insertPersonnel(projet.getEquipe(), id);
            insertOutil(projet.getOutils(), id);
            insertTache(projet.getTaches(), id);
            insertVehicule(projet.getVehicules(), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static void insertVehicule(ArrayList<Vehicule> vehicule, int idprojet) {
        String query = "insert into PROJET_VEHICULE (IDPROJET, IDVEHICULE) VALUES (" + idprojet + ",";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            for (Vehicule v : vehicule) {
                statement.execute(query + v.getId() + ")");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static void insertOutil(ArrayList<Outil> outils, int idprojet) {
        String query = "insert into PROJET_OUTIL (IDPROJET, IDOUTIL, QUANTITE) VALUES (" + idprojet + ",";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            for (Outil v : outils) {
                statement.execute(query + v.getId() + "," + v.getQuantite() + ")");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    
    public static void insertPersonnel(ArrayList<Personnel> personnels, int idprojet) {
        String query = "insert into PROJET_PERSONNEL (IDPROJET, IDPERSONNEL) VALUES (" + idprojet + ",";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            for (Personnel v : personnels) {
                statement.execute(query + v.getId() + ")");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static void insertTache(ArrayList<Tache> taches, int idprojet) {
        String query = "insert into PROJET_TACHE (IDPROJET, IDTACHE) VALUES (" + idprojet + ",";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            for (Tache v : taches) {
                statement.execute(query + v.getId() + ")");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static void supprimer(int id) {
        String query = "delete from PROJET where IDPROJET = " + id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static void modifier(Projet projet){
        String query = "update PROJET set NOMPROJET = '%s',DESCRIPTION = '%s',ADRESSE = '%s', DATEDEBUT = to_date('%s','yyyy-mm-dd'),DATEFIN = to_date('%s','yyyy-mm-dd'),COUT = %f";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
              statement.execute(String.format(query, projet.getNom(),projet.getDescription(),projet.getAdresse(),projet.getDateBedut().toString(),projet.getDateFin().toString(),projet.getCout()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
}
