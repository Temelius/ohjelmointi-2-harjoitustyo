package database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 
 * Connection manager josta saa helposti yhteyden mukaan muihin luokkiin.
 * 
 * @author Temelius
 *
 */

public class Database {
	// Tietokanta sijaitsee projektin juurikansiossa -> "jdbc:sqlite:db/shoppingList.sqlite"
	private static final String JDBC_URL = System.getenv("JDBC_DATABASE_URL");
	
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(JDBC_URL);
	}
	
	public static void close(Connection connection, PreparedStatement statement, ResultSet results) {
		if (results != null) {
			try {
				results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
