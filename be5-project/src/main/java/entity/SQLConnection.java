package entity;

import java.sql.Connection;
import java.sql.DriverManager; 

public class SQLConnection {
	public static Connection makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/be5-final-project", "admin", "admin12345678");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 	 	
	}
}