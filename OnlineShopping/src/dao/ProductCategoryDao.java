package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ProductCategory;

/**
 * @author sidakpreet.nanda
 * 
 *         DAO for handling Products by category
 */
public class ProductCategoryDao {

	private final static String PRODUCTCATEGORY = "ProductCategory";
	private final static String NAME = "Name";
	private final static String ID = "Id";

	/**
	 * Method for getting all the Product categories
	 * 
	 * @return List of all product categories
	 */
	public ArrayList<ProductCategory> getAllProductCategories() {
		ArrayList<ProductCategory> productCategories = new ArrayList<>();
		try {
			Connection connection = utils.Util.getDbConnection();
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM " + PRODUCTCATEGORY + ";");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ProductCategory productCategory = new ProductCategory();
				productCategory.setId(resultSet.getInt(ID));
				productCategory.setName(resultSet.getString(NAME));

				productCategories.add(productCategory);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productCategories;
	}
}
