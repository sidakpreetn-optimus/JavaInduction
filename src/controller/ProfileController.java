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

import dao.CartDao;
import model.Customer;
import model.Product;

/**
 * @author sidakpreet.nanda
 *
 *         Controller implementation for handling the Profile of Logged in User.
 */
@WebServlet("/Profile")
public class ProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
			// Gets the products from DB that are purchased by the logged in
			// User
			ArrayList<Product> productsPurchased = cartDao
					.getPurchasedItems(customer);

			RequestDispatcher dispatcher = req
					.getRequestDispatcher("homeProfile.jsp");
			req.setAttribute("ProductsPurchased", productsPurchased);
			dispatcher.forward(req, resp);
		} catch (NullPointerException npe) {
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
			out.print("You have been logged out! Login again!");
		}
	}

}
