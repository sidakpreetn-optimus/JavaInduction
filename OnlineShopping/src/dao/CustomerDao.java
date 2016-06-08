package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Customer;

/**
 * @author sidakpreet.nanda
 * 
 *         DAO for handling User/Customer operations in DB
 *
 */
public class CustomerDao {

	private final static String CUSTOMER = "Customer";
	private final static String EMAILID = "EmailId";
	private final static String PASSWORD = "Password";
	private final static String ADDRESS = "Address";
	private final static String NAME = "Name";
	private final static String ID = "Id";

	/**
	 * Method for searching user in DB by username and password i.e.
	 * authenticating user for login
	 * 
	 * @param username
	 * @param password
	 * @return {@link Customer} instance of logged in user
	 */
	public Customer search(String username, String password) {
		Customer customer = null;
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM " + CUSTOMER + " WHERE "
							+ EMAILID + "=? AND " + PASSWORD + "=?;");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				customer = new Customer();
				customer.setId(resultSet.getInt(ID));
				customer.setName(resultSet.getString(NAME));
				customer.setPassword(resultSet.getString(PASSWORD));
				customer.setAddress(resultSet.getString(ADDRESS));
				customer.setEmailId(resultSet.getString(EMAILID));
			} else {
				customer = null;
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			customer = null;
		}
		return customer;
	}

	/**
	 * Method for creating a new User in DB i.e. registering a new Customer
	 * 
	 * @param customer
	 * @return true if success
	 */
	public boolean create(Customer customer) {
		boolean status = false;
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO "
							+ CUSTOMER
							+ " (Id, Name, EmailId, Address, Password) VALUES (null,?,?,?,?);");
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getEmailId());
			statement.setString(3, customer.getAddress());
			statement.setString(4, customer.getPassword());

			status = (statement.executeUpdate() == 1) ? true : false;
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			customer = null;
		}
		return status;
	}
}
