package Servlets;

import Utils.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AllEmissionsServlet
 */
@WebServlet("/AllEmissionsServlet")
public class AllEmissionsServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
		String username = request.getParameter("username");
		if(username != null) {
			ArrayList<FoodEmission> foodEmissionsList = selectFoodEmissions(username);
            ArrayList<TransportEmission> transportEmissionsList = selectTransportEmissions(username);
            
			if(foodEmissionsList  != null && transportEmissionsList  != null ) {
				RequestDispatcher rd = request.getRequestDispatcher("/allEmissions.jsp");
                request.setAttribute("FoodEmissions", foodEmissionsList);
                request.setAttribute("TransportEmissions", transportEmissionsList);
				request.setAttribute("username", username);
                rd.forward(request, response);
			} else {
				out.print("Oops.. Something went wrong!");
				request.setAttribute("username", username);
				RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
				rd.include(request, response);
			}
		}
		
	}
	/* NOTE: REMEMBER TO HANDLE EMPTY TABLES ACCORDINGLY*/
	public ArrayList<FoodEmission> selectFoodEmissions(String username) {
		ArrayList<FoodEmission> list = null;
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM FoodEmissions WHERE username=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

            list = new ArrayList<FoodEmission>();
            while (rs.next())
                {
                    String foodType = rs.getString("foodType");
                    float foodQuantity = rs.getFloat("foodQuantity");
                    float carbonQuantity = rs.getFloat("carbonQuantity");
                    Date date = rs.getDate("date");
                    
                    FoodEmission foodEmission = new FoodEmission();
                    foodEmission.setUsername(username);
                    foodEmission.setFoodType(foodType);
                    foodEmission.setFoodQuantity(foodQuantity);
                    foodEmission.setCarbonQuantity(carbonQuantity);
                    foodEmission.setDate(date);
                    
                    list.add(foodEmission);
                }
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

    public ArrayList<TransportEmission> selectTransportEmissions(String username) {
		ArrayList<TransportEmission> list = null;
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM TransportEmissions WHERE username=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

            list = new ArrayList<TransportEmission>();

            while (rs.next())
                {
                    String transportType = rs.getString("transportType");
                    float distance = rs.getFloat("distance");
                    float carbonQuantity = rs.getFloat("carbonQuantity");
                    Date date = rs.getDate("date");
                    
                    TransportEmission transportEmission = new TransportEmission();
                    transportEmission.setUsername(username);
                    transportEmission.setTransportType(transportType);
                    transportEmission.setDistance(distance);
                    transportEmission.setCarbonQuantity(carbonQuantity);
                    transportEmission.setDate(date);
                    
                    list.add(transportEmission);
                }

		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
