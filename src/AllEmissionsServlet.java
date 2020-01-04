

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
 * Servlet implementation class AllEmissionsServlet
 */
@WebServlet("/AllEmissionsServlet")
public class AllEmissionsServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		/* Check which type was selected */
		String username = request.getParameter("username");
		
		if(username != null) {
			boolean selectFoodEmissionsStatus = selectFoodEmissions(username);
            boolean selectTransportEmissionsStatus = selectTransportEmissions(username);

			if(selectFoodEmissionsStatus && selectTransportEmissionsStatus) {
				// request.setAttribute("carbon", carbonQuantity);
				RequestDispatcher rd = request.getRequestDispatcher("allEmissions.jsp");
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
	
	public boolean selectFoodEmissions(String username) {
		boolean status = false;
		
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM FoodEmissions WHERE username=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                {
                    status = true;

                    String foodType = rs.getString("foodType");
                    float foodQuantity = rs.getString("foodQuantity");
                    float carbonQuantity = rs.getDate("carbonQuantity");
                    // print the results
                    System.out.format("%s, %s, %s\n", foodType, foodQuantity, carbonQuantity);
                }
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

    public boolean selectTransportEmissions(String username) {
		boolean status = false;
		
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM TransportEmissions WHERE username=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                {
                    status = true;

                    String transportType = rs.getString("transportType");
                    float distance = rs.getString("distance");
                    float carbonQuantity = rs.getDate("carbonQuantity");
                    // print the results
                    System.out.format("%s, %s, %s\n", transportType, distance, carbonQuantity);
                }

		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
}
