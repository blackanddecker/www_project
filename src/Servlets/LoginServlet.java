package Servlets;

import Utils.ConnectionDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	/**
	 * @apiNote doPost
	 * @param request
	 * @param response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean loginStatus = false;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		// Get request Parameters
		String username = request.getParameter("username");
		out.print("USERNAME: " + username);
		String password = request.getParameter("psw");
		// Check validity of credentials
		loginStatus = validateLogin(username, password);
		if (loginStatus) {
			out.print("Successful Login!");
			request.setAttribute("username", username);
			RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
			rd.forward(request, response);
		} else {
			out.print("Wrong credentials. Please try again!");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}

		out.close();
	}
	/**
	 * @apiNote validateLogin 
	 * @param username
	 * @param password
	 * @return status
	 */
	protected static boolean validateLogin(String username, String password){
		boolean status = false;
			
		try {
			ConnectionDetails connDetails = new ConnectionDetails();
			Connection conn = connDetails.getConnection(); 
			
			String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			status = resultSet.next();
			
			conn.close();
			preparedStatement.close();
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}		
		return status;
	}
}
