package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author sidakpreet.nanda
 *
 *         Utility class
 */
public class Util {

	/**
	 * Method for getting the MySql DB {@link Connection} instance.
	 * 
	 * @return
	 */
	public static Connection getDbConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/OnlineShopping", "root",
					"root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
