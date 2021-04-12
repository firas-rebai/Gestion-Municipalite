package PFA.Materiel2.ServiceMateriel;
import java.sql.SQLException;
import Materiel.ModuleMateriel.MaterielVehicule;
import java.sql.Connection;
import java.sql.Statement;
import static DataBaseConnection.OracleConnection.getOracleConnection;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
public class Vehicule {


    public static void Ajouter(MaterielVehicule p){
        String SQLquery = "insert into materielvehicule values(" + p.getId() + ",'" + p.getModel() +
                "'," + p.getMatricule() + "," + p.getPrix() + ",'" + p.getNom() + "','" +
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



    public static void Modifier(MaterielVehicule p) {
        String SQLquery = "update materielvehicule set  " + "  model = '" + p.getModel() +
                "', matricule = " +  p.getMatricule() +
                ", prix = " + p.getPrix()
                + ", nom = '" +p.getNom()  +
                "' where id = " + p.getId() + "";
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

    public  static List<MaterielVehicule> Recherche(String term){
        List<MaterielVehicule> liste = new ArrayList<>();
        String query = "select * from materielvehicule WHERE lower(nom) LIKE lower('% " + term + "%')"  +
                " OR id =" + "'" + term + "'" +
                " OR matricule ="+ "'" + term + "'";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                liste.add(new MaterielVehicule( rs.getInt("id"),
                        rs.getInt("matricule"),
                        rs.getString("model") ,
                        rs.getDouble("prix"),
                        rs.getString("nom"),
                        rs.getString("datedachat")));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }

    public static void Suprrimer(Integer matricule) {
        String query = "delete from materielvehicule where matricule = " + matricule ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




}
