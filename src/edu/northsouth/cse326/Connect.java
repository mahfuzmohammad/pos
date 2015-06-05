package edu.northsouth.cse326;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ali
 */
class Connect {

	Connection con = null;

	public Connect(String db) {
		String url = "jdbc:mysql://localhost:3306/" + db;
		String user = "root";
		String pass = "";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = (Connection) DriverManager.getConnection(url, user, pass);
			System.out.print("Connected...");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public Connection crtst() {
		return con;
	}
}