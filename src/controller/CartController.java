package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import model.Product;
import dao.CartDao;
import dao.ProductDao;

/**
 * @author sidakpreet.nanda
 *
 *         Controller implementation for handling User's Cart
 */
@WebServlet("/Cart")
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		/*
		 * Handles request parameter "action" and calls internal methods
		 * correspondingly for adding, buying and deleting from User's Cart.
		 */
		try {
			String action = req.getParameter("action");
			if (action.equals("add")) {
				add(req, resp);
			} else if (action.equals("buy")) {
				buy(req, resp);
			} else if (action.equals("delete")) {
				delete(req, resp);
			}
		} catch (NullPointerException npe) {
			// if null exception then logout the User
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
			out.print("You have been logged out! Login again!");
		}
	}

	/**
	 * Method to delete product from Cart in DB
	 * 
	 * @param req
	 * @param resp
	 */
	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			Customer customer = (Customer) session
					.getAttribute("loggedInCustomer");
			int productId = Integer.parseInt(req.getParameter("pId"));
			ProductDao productDao = new ProductDao();
			CartDao cartDao = new CartDao();

			Product product = productDao.getProductById(productId);
			cartDao.deleteFromCart(product, customer);

			resp.sendRedirect("Cart");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to buy product from Cart and add it to purchased items of user in
	 * DB
	 * 
	 * @param req
	 * @param resp
	 */
	private void buy(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			Customer customer = (Customer) session
					.getAttribute("loggedInCustomer");
			int productId = Integer.parseInt(req.getParameter("pId"));
			ProductDao productDao = new ProductDao();
			CartDao cartDao = new CartDao();

			Product product = productDao.getProductById(productId);
			int toSubtractQuantity = cartDao.getProductQuantity(product,
					customer);
			cartDao.buyFromCart(product, customer);
			productDao.updateProductQuantity(product, toSubtractQuantity);

			resp.sendRedirect("Cart");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to add product to User's Cart in DB
	 * 
	 * @param req
	 * @param resp
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			Customer customer = (Customer) session
					.getAttribute("loggedInCustomer");
			int productId = Integer.parseInt(req.getParameter("pId"));
			ProductDao productDao = new ProductDao();
			Product product = productDao.getProductById(productId);
			CartDao cartDao = new CartDao();
			cartDao.addUpdateToCart(product, customer);
			resp.sendRedirect("Cart");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		try {
			resp.setContentType("text/html");
			HttpSession session = req.getSession();
			Customer customer = (Customer) session
					.getAttribute("loggedInCustomer");
			CartDao cartDao = new CartDao();
			ArrayList<Product> productsInCart = cartDao.getCartItems(customer);
			// Navigates the user to its Cart Page
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("homeCart.jsp");
			req.setAttribute("ProductsInCart", productsInCart);
			dispatcher.forward(req, resp);
		} catch (NullPointerException npe) {
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
			out.print("You have been logged out! Login again!");
		}
	}

}
