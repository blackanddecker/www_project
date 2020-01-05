package Servlets;

import Utils.ConnectionDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
				boolean registerStatus;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				
				ConnectionDetails connDetails = new ConnectionDetails();
				Connection conn = connDetails.getConnection();
				
				String username = request.getParameter("username");		
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String email = request.getParameter("email");
				String password = request.getParameter("psw");
				String pswRepeat = request.getParameter("psw-repeat");
				float budget = Float.parseFloat(request.getParameter("budget"));
				
				if(!password.equals(pswRepeat)) {
					out.print("Passwords do not match. Try again!");
				} else if(usernameExists(username)){
					out.print("Username already exists. Please choose a different one!");
				} else {
					registerStatus = createUser(username, password, firstname, lastname, email, budget);
					if(registerStatus) {
						out.print("Successful Registration Form");
						RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
						rd.include(request, response);
					} else {
						out.print("Something went wrong.. Try again!");
					}
				}
	}
	/**
	 * @apiNote {usernameExists} Checks if username already exists in Users table
	 * @param username
	 * @return
	 */
	private boolean usernameExists(String username) {
		boolean exists = false;
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		
		try {
			String sql = "SELECT * FROM Users WHERE username=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			exists = resultSet.next();
			
			conn.close();
			preparedStatement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return exists;
	}
	
	private boolean createUser(
			String username, 
			String password, 
			String firstname, 
			String lastname, 
			String email,
			float budget)  {
				ConnectionDetails connDetails = new ConnectionDetails();
				Connection conn = connDetails.getConnection();
				try {
					String sql = "INSERT INTO Users VALUES (?, ?, ? ,?, ?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(sql);
					
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					preparedStatement.setString(3, firstname);
					preparedStatement.setString(4, lastname);
					preparedStatement.setString(5, email);
					preparedStatement.setFloat(6, budget);
					
					int success = preparedStatement.executeUpdate();
					if (success > 0) {
						return true;
					} 
				} catch (SQLException ex) {
					ex.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return false;
	}

}
