package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Utility class with method connectToDb() that will be used by other programs in this chapter
public class DBConnector {
	// jdbc:mysql://localhost:3306/Peoples?autoReconnect=true&useSSL=false;
	private final static String url = "jdbc:mysql://localhost:3306/";
	private final static String database = "password?autoReconnect=true&useSSL=false";
	private final static String userName = "root";
	private final static String password = "mysql123";

	static Connection connectToDb() {
		try {
			return DriverManager.getConnection(url + database, userName, password);
		} catch (SQLException e) {
			return null;
		}
	}

}
