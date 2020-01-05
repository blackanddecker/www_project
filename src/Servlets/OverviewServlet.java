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
			System.out.println("FOOD EMISSIONS: "  + foodEmissions);
            float transportEmissions = selectTransportEmissionsbyUser(username);
            System.out.println("Transport Emissions: " + transportEmissions);
            float avgTransportEmissions = selectTransportEmissionsAvg();
            System.out.println("Average Transport: " + avgTransportEmissions);
            float avgFoodEmissions = selectFoodEmissionsAvg();
            System.out.println("Average Food: " + avgFoodEmissions);

			if(foodEmissions  != -1  &&  transportEmissions  != -1 ) {
				float totalUserBudget = 500;
                float totalUserEmissions = foodEmissions + transportEmissions;
                System.out.println("Total user emissions: " + totalUserEmissions);
                float remainingBudget = totalUserBudget - totalUserEmissions;
                System.out.println("Remaining: " + remainingBudget);
                float avgEmissions = avgTransportEmissions + avgFoodEmissions;
                System.out.println("Avg Emissions: " + avgEmissions);
                
				RequestDispatcher rd = request.getRequestDispatcher("overview.jsp");
                request.setAttribute("FoodEmissions", foodEmissions);
                request.setAttribute("TransportEmissions", transportEmissions);
                request.setAttribute("TotalUserEmmissions", totalUserEmissions);
                request.setAttribute("RemainingBudget", remainingBudget);
                request.setAttribute("AvgEmissions", avgEmissions);

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
     * Calculates the average carbon emissions by food from all users
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
}