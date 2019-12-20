

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
 * Servlet implementation class AddFoodEmissionServlet
 */
@WebServlet("/AddFoodEmissionServlet")
public class AddFoodEmissionServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		/* Check which type was selected */
		String username = request.getParameter("username");
		String foodType = request.getParameter("foodType");
		float foodQuantity = Float.valueOf(request.getParameter("quantity"));
		
		if(username != null && foodType != null && foodQuantity != 0) {
			float carbonQuantity;
			carbonQuantity = calculateCarbonInFood(foodType, foodQuantity);
			boolean insertStatus = insertFoodEmission(username, foodType, foodQuantity, carbonQuantity);
			if(insertStatus) {
				request.setAttribute("carbon", carbonQuantity);
				RequestDispatcher rd = request.getRequestDispatcher("successfulEntry.jsp");
				rd.forward(request, response);
			} else {
				out.print("Oops.. Something went wrong!");
				RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
				rd.include(request, response);
			}
		}
		
	}
	
	public float calculateCarbonInFood(String foodType, float quantity) {
		/*Select query from Table CarbonByFood*/
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM CarbonByFood WHERE foodType=?";
		float carbonPerKg, carbonPerQuantity = 0;
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, foodType);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				carbonPerKg = resultSet.getFloat("carbonPerKg");		
				/*CALCULATE CARBON PER QUANTITY*/
				carbonPerQuantity = carbonPerKg * quantity;	
			}		
			preparedStatement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return carbonPerQuantity;
	}
	
	public boolean insertFoodEmission(String username, String foodType, float foodQuantity, float carbonQuantity) {
		boolean status = false;
		
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "INSERT INTO FoodEmissions VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, foodType);
			preparedStatement.setFloat(3, foodQuantity);
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
