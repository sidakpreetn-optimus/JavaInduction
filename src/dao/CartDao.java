package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Customer;
import model.Product;

/**
 * @author sidakpreet.nanda
 *
 *         DAO for handling Cart operations on DB
 */
public class CartDao {

	private final static String CART = "Cart";
	private final static String PRODUCTID = "ProductId";
	private final static String CUSTOMERID = "CustomerId";
	private final static String ISPURCHASED = "IsPurchased";
	private final static String QUANTITY = "Quantity";
	private final static String ID = "Id";

	/**
	 * Method for getting the items currently in user's cart
	 * 
	 * @param customer
	 * @return List of Products in User's Cart
	 */
	public ArrayList<Product> getCartItems(Customer customer) {
		ArrayList<Product> products = new ArrayList<>();
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM " + CART + " WHERE "
							+ CUSTOMERID + " = '" + customer.getId() + "' AND "
							+ ISPURCHASED + " = 0;");
			ResultSet resultSet = statement.executeQuery();
			ProductDao productDao = new ProductDao();
			while (resultSet.next()) {
				int productId = resultSet.getInt(PRODUCTID);
				int quantity = resultSet.getInt(QUANTITY);
				Product product = productDao.getProductById(productId);
				product.setQuantity(String.valueOf(quantity));
				products.add(product);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * Method for buying the Product from Cart i.e. changing its status to
	 * purchased
	 * 
	 * @param product
	 * @param customer
	 * @return true if operation on DB success
	 */
	public boolean buyFromCart(Product product, Customer customer) {
		boolean status = false;
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE "
					+ CART + " SET " + ISPURCHASED + " = " + 1 + " WHERE "
					+ CUSTOMERID + " = " + "? AND " + PRODUCTID + " = ?;");
			statement.setInt(1, customer.getId());
			statement.setInt(2, product.getId());

			status = (statement.executeUpdate() == 1) ? true : false;
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			customer = null;
		}
		return status;
	}

	/**
	 * Method for deleting an item from user's Cart.
	 * 
	 * @param product
	 * @param customer
	 * @return true if operation on DB success
	 */
	public boolean deleteFromCart(Product product, Customer customer) {
		boolean status = false;
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM " + CART + " WHERE "
							+ CUSTOMERID + " = " + "? AND " + PRODUCTID
							+ " = ?;");
			statement.setInt(1, customer.getId());
			statement.setInt(2, product.getId());

			status = (statement.executeUpdate() == 1) ? true : false;
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			customer = null;
		}
		return status;
	}

	/**
	 * Method for getting the User's purchased items
	 * 
	 * @param customer
	 * @return List of purchased Products
	 */
	public ArrayList<Product> getPurchasedItems(Customer customer) {
		ArrayList<Product> products = new ArrayList<>();
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM " + CART + " WHERE "
							+ CUSTOMERID + " = '" + customer.getId() + "' AND "
							+ ISPURCHASED + " = 1;");
			ResultSet resultSet = statement.executeQuery();
			ProductDao productDao = new ProductDao();
			while (resultSet.next()) {
				int productId = resultSet.getInt(PRODUCTID);
				int quantity = resultSet.getInt(QUANTITY);
				Product product = productDao.getProductById(productId);
				product.setQuantity(String.valueOf(quantity));
				products.add(product);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * Add's or update's Cart item for a specific product and customer row
	 * mapping.
	 * 
	 * @param product
	 * @param customer
	 * @return true if operation on DB success
	 */
	public boolean addUpdateToCart(Product product, Customer customer) {
		boolean status = false;
		try {
			Connection connection = utils.Util.getDbConnection();
			int productQuantity = getProductQuantity(product, customer);
			// if for
			if (productQuantity >= 1) {
				productQuantity++;
				PreparedStatement statement = connection
						.prepareStatement("UPDATE " + CART + " SET " + QUANTITY
								+ " = " + productQuantity + " WHERE "
								+ CUSTOMERID + " = " + "? AND " + PRODUCTID
								+ " = ? AND " + ISPURCHASED + " = 0;");
				statement.setInt(1, customer.getId());
				statement.setInt(2, product.getId());
				status = (statement.executeUpdate() == 1) ? true : false;
			} else {
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO " + CART + " (" + ID
								+ ", " + CUSTOMERID + ", " + PRODUCTID + ", "
								+ ISPURCHASED + ", " + QUANTITY
								+ ") VALUES (null,?,?,0,1);");
				statement.setInt(1, customer.getId());
				statement.setInt(2, product.getId());
				status = (statement.executeUpdate() == 1) ? true : false;
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			customer = null;
		}
		return status;
	}

	/**
	 * Method for getting product quantity in cart for a specific customer and
	 * product
	 * 
	 * @param product
	 * @param customer
	 * @return product count for a unique customer product row
	 */
	public int getProductQuantity(Product product, Customer customer) {
		int count = 0;
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT "
					+ QUANTITY + " FROM " + CART + " WHERE " + CUSTOMERID
					+ " = " + customer.getId() + " AND " + ISPURCHASED
					+ " = 0 AND " + PRODUCTID + " =" + product.getId() + ";");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(QUANTITY);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}