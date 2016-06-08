package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Product;
import model.ProductCategory;

/**
 * @author sidakpreet.nanda
 *
 *         DAO for handling Products operations in DB
 */
public class ProductDao {

	private final static String PRODUCT = "Product";
	private final static String CATEGORY = "Category";
	private final static String PRICE = "Price";
	private final static String QUANTITY = "Quantity";
	private final static String NAME = "Name";
	private final static String ID = "Id";

	/**
	 * Method for getting all the Products in DB
	 * 
	 * @return List of all Products
	 */
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM " + PRODUCT + ";");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt(ID));
				product.setName(resultSet.getString(NAME));
				product.setCategory(resultSet.getString(CATEGORY));
				product.setPrice(resultSet.getString(PRICE));
				product.setQuantity(resultSet.getString(QUANTITY));

				products.add(product);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * Method for getting the products of a specific productCategory
	 * 
	 * @param productCategory
	 * @return List of products of category productCategory
	 */
	public ArrayList<Product> getProductsByCategory(
			ProductCategory productCategory) {
		ArrayList<Product> products = new ArrayList<>();
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM " + PRODUCT + " WHERE "
							+ CATEGORY + " = '" + productCategory.getName()
							+ "';");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt(ID));
				product.setName(resultSet.getString(NAME));
				product.setCategory(resultSet.getString(CATEGORY));
				product.setPrice(resultSet.getString(PRICE));
				product.setQuantity(resultSet.getString(QUANTITY));

				products.add(product);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * Method for retrieving {@link Product} by its Id
	 * 
	 * @param id
	 * @return {@link Product}
	 */
	public Product getProductById(int id) {
		Product product = null;
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM " + PRODUCT + " WHERE "
							+ ID + "=?;");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				product = new Product();
				product.setId(resultSet.getInt(ID));
				product.setName(resultSet.getString(NAME));
				product.setQuantity(resultSet.getString(QUANTITY));
				product.setPrice(resultSet.getString(PRICE));
				product.setCategory(resultSet.getString(CATEGORY));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	/**
	 * Method for updating the Product's Quantity
	 * 
	 * @param product
	 * @param toSubtractQuantity
	 * @return
	 */
	public boolean updateProductQuantity(Product product, int toSubtractQuantity) {
		boolean status = false;
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE "
							+ PRODUCT
							+ " SET "
							+ QUANTITY
							+ " = "
							+ (Integer.parseInt(product.getQuantity()) - toSubtractQuantity)
							+ " WHERE " + ID + " = ?;");
			statement.setInt(1, product.getId());

			status = (statement.executeUpdate() == 1) ? true : false;
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
