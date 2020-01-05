package Servlets;

import Utils.*;

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
 * Servlet implementation class OverviewServlet
 */
@WebServlet("/OverviewServlet")
public class OverviewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
		String username = request.getParameter("username");
		if(username != null) {
			float foodEmissions = selectFoodEmissionsbyUser(username);
            float transportEmissions = selectTransportEmissionsbyUser(username);
            float avgTransportEmissions = selectTransportEmissionsAvg();
            float avgFoodEmissions = selectFoodEmissionsAvg();

			if(foodEmissions  != -1  &&  transportEmissions  != -1 ) {
				float totalUserBudget = getUserBudget(username);
                float totalUserEmissions = foodEmissions + transportEmissions;
                float remainingBudget = totalUserBudget - totalUserEmissions;
                float avgEmissions = avgTransportEmissions + avgFoodEmissions;
                float offset = 0;
                String warning = "false";
                
                if(remainingBudget < 0) {
                	warning = "true";
                	offset = - remainingBudget;
                	remainingBudget = 0;
                }
				RequestDispatcher rd = request.getRequestDispatcher("overview.jsp");
                request.setAttribute("FoodEmissions", foodEmissions);
                request.setAttribute("TransportEmissions", transportEmissions);
                request.setAttribute("TotalUserEmmissions", totalUserEmissions);
                request.setAttribute("RemainingBudget", remainingBudget);
                request.setAttribute("AvgEmissions", avgEmissions);
                request.setAttribute("TotalUserBudget", totalUserBudget);
                request.setAttribute("warning", warning);
                request.setAttribute("offset", offset);
                
                rd.forward(request, response);
			} else {
				out.print("Oops.. Something went wrong!");
				RequestDispatcher rd = request.getRequestDispatcher("overview.jsp");
				rd.include(request, response);
			}
		}
		
	}
	/**
	 * Calculates & returns the total carbon quantity spent by the user's food consumption
	 * @param username
	 * @return totalCarbonQuantity
	 */
	public float selectFoodEmissionsbyUser(String username) {
		float totalCarbonQuantity = -1;
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT SUM(carbonQuantity) AS CarbonSum FROM FoodEmissions WHERE username = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				totalCarbonQuantity = rs.getFloat("CarbonSum");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return totalCarbonQuantity;
	
	}
	/**
	 * Calculates & returns the total carbon quantity spent by the user's transport
	 * @param username
	 * @return totalCarbonQuantity
	 */
    public float selectTransportEmissionsbyUser(String username) {
		float totalCarbonQuantity = -1;
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT SUM(carbonQuantity) AS CarbonSum FROM TransportEmissions WHERE username = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				totalCarbonQuantity = rs.getFloat("CarbonSum");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return totalCarbonQuantity;
	}
    /**
     * Calculates the average carbon emissions by transport from all users
     * @return avgCarbonQuantity
     */
    public float selectTransportEmissionsAvg() {
    	float avgCarbonQuantity = -1;
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT AVG(carbonQuantity) AS CarbonAvg FROM TransportEmissions";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                {
            		avgCarbonQuantity = rs.getFloat("CarbonAvg");
                }
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return avgCarbonQuantity;
	}
    /**
     * Returns the average carbon emissions by food from all users
     * @return avgCarbonQuantity
     */
    public float selectFoodEmissionsAvg() {
		float avgCarbonQuantity = -1;
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT AVG(carbonQuantity) AS CarbonAvg FROM FoodEmissions";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                {
            		avgCarbonQuantity = rs.getFloat("CarbonAvg");
                }
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return avgCarbonQuantity;
    }
    /**
     * Returns the specific user's carbon budget
     * @param username
     * @return budget
     */
    public float getUserBudget(String username) {
    	float budget = 0;
    	ConnectionDetails connDetails = new ConnectionDetails();
    	Connection conn = connDetails.getConnection();
    	String sql = "SELECT budget FROM Users WHERE username = ?";
    	try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                {
            		budget = rs.getFloat("budget");
                }
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    	return budget;
    }
}