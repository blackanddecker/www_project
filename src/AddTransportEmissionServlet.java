

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
 * Servlet implementation class AddTransportEmissionServlet
 */
@WebServlet("/AddTransportEmissionServlet")
public class AddTransportEmissionServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		/* Check which type was selected */			
		String transportType = request.getParameter("transferType");		
		float distance = Float.valueOf(request.getParameter("distance"));
		String username = request.getParameter("username");
		boolean insertStatus;
		
		/* Insert transport emission in TransportEmissions Table*/
		if(transportType != null && distance != 0 && username != null) {
			float carbonPerTrip = calculateCarbonByTrip(distance, transportType);
			insertStatus = insertTransportEmission(username, transportType, distance, carbonPerTrip);
			if(insertStatus) {
				request.setAttribute("carbon", carbonPerTrip);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/successfulEntry.jsp");
				rd.forward(request, response);
			} else {
				out.print("Oops.. Something went wrong!");
				RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
				rd.include(request, response);
			}
		}
	}
	
	public float calculateCarbonByTrip(float distance, String transportType) {
		float carbonPerKm, carbonPerTrip = 0;
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM CarbonByTransport WHERE transportType = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, transportType);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				carbonPerKm = resultSet.getFloat("carbonPerKm");
				carbonPerTrip = carbonPerKm * distance;
			}
			preparedStatement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return carbonPerTrip;
	}
	
	public boolean insertTransportEmission(String username, String transportType, float distance, float carbonQuantity) {
		boolean status = false;
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "INSERT INTO TransportEmissions VALUES(?, ?, ?, ?, ?)";
		try {
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, transportType);
			preparedStatement.setFloat(3, distance);
			preparedStatement.setFloat(4, carbonQuantity);
			preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			
			int success = preparedStatement.executeUpdate();
			if(success > 0) {
				status = true;
			}		
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
}
