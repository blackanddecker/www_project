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
 * Servlet implementation class SettingsServlet
 */
@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				float budget = Float.parseFloat(request.getParameter("budget"));
				String email = request.getParameter("email");
				String password = request.getParameter("psw");
				String username = request.getParameter("username");
				checkBudgetRequest(username, budget, out);
				checkPswRequest(username, password, out);
				checkEmailRequest(username, email, out);
				
				request.setAttribute("username", username);
				RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
				rd.include(request, response);
	
	}
	/**
	 * Checks if user requested budget change and updates it
	 * @param budget
	 * @param out
	 */
	private void checkBudgetRequest(String username, float budget, PrintWriter out) {
		boolean settingStatus;
		String type;
		
		if(budget == 0) return;
		
		type = "budget";
		settingStatus = changeSettings(username, type, String.valueOf(budget));
		if(settingStatus) {
			out.print("Budget updated" + "<br>");
		} else {
			out.print("Something went wrong.. Try again!" + "<br>");
		}
	}
	/**
	 * Checks if user requested password change and updates it
	 * @param password
	 * @param out
	 */
	private void checkPswRequest(String username, String password, PrintWriter out) {
		boolean settingsStatus;
		String type;

		if(password == null) return;
		if(password.equals("")) return;
		
		type = "password";
		settingsStatus = changeSettings(username, type, password);
		if(settingsStatus) {
			out.print("Password Updated" + "<br>");
		} else {
			out.print("Something went wrong.. Try again!" + "<br>");
		}
	}
	/**
	 * Checks if user requested email change and updates it
	 * @param email
	 * @param out
	 */
	private void checkEmailRequest(String username, String email, PrintWriter out) {
		boolean settingsStatus;
		String type;

		if(email == null) return;
		if(email.equals("")) return;
		
		type = "email";
		settingsStatus = changeSettings(username, type, email);
		if(settingsStatus) {
			out.print("Email Updated" + "<br>");
		} else {
			out.print("Something went wrong.. Try again!" + "<br>");
		}
			
		
	}
	/**
	 * Updates Users table in the fields required by the user
	 * @param type
	 * @param field
	 * @return status of query
	 */
	private boolean changeSettings(String username, String type, String field)  {
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		try {
			String sql = "UPDATE Users SET " + type + " = ? WHERE username = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, field);		
			preparedStatement.setString(2, username);
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