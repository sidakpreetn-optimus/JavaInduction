package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import model.Customer;

/**
 * @author sidakpreet.nanda
 *
 *         Controller implementation for handling the Sign up of new Users and
 *         registering them in the DB.
 */
@WebServlet("/Signup")
public class SignupController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		Customer customer = new Customer();
		customer.setName(req.getParameter("name"));
		customer.setAddress(req.getParameter("address"));
		customer.setEmailId(req.getParameter("emailId"));
		customer.setPassword(req.getParameter("password"));

		boolean status = new CustomerDao().create(customer);
		if (status) {
			// On success User Registration in DB
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
			out.print("Successfully Registered\nLogin to Continue");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
			out.print("Internal Error\nTry Again Please");
		}
	}
}
