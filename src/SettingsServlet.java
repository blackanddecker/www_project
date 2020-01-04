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
				boolean settingsStatus;
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				
				ConnectionDetails connDetails = new ConnectionDetails();
				Connection conn = connDetails.getConnection();
				
				String username = request.getParameter("username");		
				String email = request.getParameter("email");
				String password = request.getParameter("psw");
				out.print("USERNAME: " + username);
				
                if(usernameExists(username)){
					out.print("Username already exists. Please choose a different one!");
				} else {
					settingsStatus = changeSettings(username, password, email);
					if(settingsStatus) {
						out.print("Settings Updated");
						RequestDispatcher rd = request.getRequestDispatcher("settings.jsp");
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
	
	private boolean changeSettings(
			String username, 
			String password, 
			String email)  {
				ConnectionDetails connDetails = new ConnectionDetails();
				Connection conn = connDetails.getConnection();
				try {
					String sql = "UPDATE Users SET username = ?, password = ?, email = ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(sql);
					
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					preparedStatement.setString(3, email);
					
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