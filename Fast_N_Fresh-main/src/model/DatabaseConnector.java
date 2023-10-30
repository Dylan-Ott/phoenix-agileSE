package model;

import java.sql.*;

import constants.DatabaseUserConstants;

public class DatabaseConnector {

	private static Connection databaseConnection = null;
	
	private static Statement st; 

	private DatabaseConnector() {
	}
	
	// Singleton class instance to avoid multiple Database connections
	// Returns Database connection instance
	public static Connection getInstance() throws Exception {
		if (databaseConnection == null) {
			synchronized (DatabaseConnector.class) {
				if (databaseConnection == null) {
					Class.forName("com.mysql.jdbc.Driver");
					databaseConnection = DriverManager.getConnection(DatabaseUserConstants.url,
							DatabaseUserConstants.username, DatabaseUserConstants.password);
				}
			}
		}
		return databaseConnection;
	}

	// getItemsFromCatalog method is used to fetch the product list based on catalogId from the database
	// Returns the ResultSet
	public static ResultSet getItemsFromCatalog(String catalogId) {
		try {
			Connection conn = DatabaseConnector.getInstance();
			st = conn.createStatement();
			String query = "select productId, productName, productPrice, productQuantity, catalog from product_list where catalog = '"
					+ catalogId + "'";
			ResultSet rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// closeStatement is a common method to close the statement
	public static void closeStatement() {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
