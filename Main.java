package javaSQLtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		String dbURL = "jdbc:oracle:thin:@localhost:1521/XE";
		String username = "rebai";
		String password = "firas";
		try {
			Connection connection = DriverManager.getConnection(dbURL,username,password);
			System.out.println("worked!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
