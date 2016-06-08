package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import dao.CustomerDao;

/**
 * @author sidakpreet.nanda
 * 
 *         Controller implementation for handling log in and logout of Users
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		/*
		 * Handles request parameter "action" and calls internal methods
		 * correspondingly for User's logging in and out.
		 */
		try {
			String action = req.getParameter("action");
			if (action.equals("login")) {
				login(req, resp);
			} else if (action.equals("logout")) {
				logout(req, resp);
			}
		} catch (NullPointerException npe) {
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
			out.print("You have been logged out! Login again!");
		}
	}

	/**
	 * Method that logout's the user and navigates to the login screen.
	 * 
	 * @param req
	 * @param resp
	 */
	private void logout(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter out = resp.getWriter();
			req.getSession().invalidate();
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
			out.print("You have been successfully logged out!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that navigates the user to home page on success login else error
	 * message displayed
	 * 
	 * @param req
	 * @param resp
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html");

			String username = req.getParameter("userName");
			String password = req.getParameter("password");

			CustomerDao customerDao = new CustomerDao();
			Customer customer = customerDao.search(username, password);
			if (customer != null) {
				HttpSession session = req.getSession();
				session.setAttribute("loggedInCustomer", customer);
				resp.sendRedirect("homeProducts.jsp");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.include(req, resp);
				out.print("User Not Registered !!\nSign-Up First !!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
