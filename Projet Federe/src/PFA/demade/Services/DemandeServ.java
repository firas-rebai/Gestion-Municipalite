package PFA.demade.Services;

import Module.DemandeModu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static DataBaseConnection.OracleConnection.getOracleConnection;

public class DemandeServ {
    public static void Ajouter(DemandeModu p) {
       /* String SQLquery = String.format("insert into demande values( " +
                        "'%s','%s',%d,'%s',%d,DEM_SEQ.nextval,to_date('%s','yyyy-mm-dd'),'%s','%s')",
                p.getNom(),p.getPrenom(), p.getCin(),p.getAdresse(),p.getNumtel(),p.getId(),
                p.getDateDem().toString(),p.getTypeDem(),p.getDescreption());

        */
        String SQLquery = String.format("insert into dem values( " +
                "'%s','%s',%d,'%s',%d,DEM_SEQ.nextval,to_date('%s','YYYY-MON-DD'),'%s','%s')", p.getNom(),
                p.getPrenom(), p.getCin(),p.getAdresse(),p.getNumtel(),p.getId(),p.getDateDem().toString(),
                p.getTypeDem(),p.getDescreption());

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

}
