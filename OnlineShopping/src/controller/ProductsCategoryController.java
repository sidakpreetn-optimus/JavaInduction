package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductCategory;
import dao.ProductCategoryDao;
import dao.ProductDao;

/**
 * @author sidakpreet.nanda
 *
 *         Controller implementation for displaying the list of products by
 *         category in Category Page.
 */
@WebServlet("/ProductsByCategory")
public class ProductsCategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");

		ProductCategoryDao categoryDao = new ProductCategoryDao();
		ProductDao productDao = new ProductDao();

		ArrayList<ProductCategory> productCategories = categoryDao
				.getAllProductCategories();
		// Sends a HashMap to homeCategories.jsp with key as Category Name and
		// Value as list of products of
		// that Category
		HashMap<String, ArrayList<Product>> productsByCategories = new HashMap<>();
		for (ProductCategory category : productCategories) {
			ArrayList<Product> products = productDao
					.getProductsByCategory(category);
			productsByCategories.put(category.getName(), products);
		}

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("homeCategories.jsp");
		req.setAttribute("ProductsByCategories", productsByCategories);
		dispatcher.forward(req, resp);
	}
}
