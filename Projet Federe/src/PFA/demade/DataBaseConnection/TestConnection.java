package PFA.demade.DataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args)throws SQLException {

        String selectTableSQL = "SELECT * FROM materielvehicule";

        Statement statement = null;

        try{
            Connection connection= OracleConnection.getOracleConnection();

            statement = connection.createStatement();


            //get data from db

            ResultSet rs = statement.executeQuery(selectTableSQL);



            //fetch data

            while(rs.next()){
                Integer field1 = rs.getInt("id");
                Integer field2 = rs.getInt("quantité");
                Double field3 = rs.getDouble("prix");
                String field4 =rs.getString("nom");


                System.out.println("id :"+field1 + " quantité :"+field2 + " prix :"+field3 + " nom :"+field4);
                //System.out.println("idtest : "+field);
                // System.out.println("nametest : "+field1);

            }
            rs.close();




        }
        catch (SQLException e){
            System.out.println("1000000 dawa7");
        }

    }
}
