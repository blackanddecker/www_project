

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
		String username = request.getParameter("username");
		if(username != null) {
			boolean selectFoodEmissionsStatus = selectFoodEmissions(username);
            boolean selectTransportEmissionsStatus = selectTransportEmissions(username);

			if(selectFoodEmissionsStatus && selectTransportEmissionsStatus) {
				RequestDispatcher rd = request.getRequestDispatcher("allEmissions.jsp");
				rd.forward(request, response);
			} else {
				out.print("Oops.. Something went wrong!");
				RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
				rd.include(request, response);
			}
		}
		
	}
	/* NOTE: REMEMBER TO HANDLE EMPTY TABLES ACCORDINGLY*/
	public boolean selectFoodEmissions(String username) {
		boolean status = false;
		
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM FoodEmissions WHERE username=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                {
                    status = true;

                    String foodType = rs.getString("foodType");
                    float foodQuantity = rs.getFloat("foodQuantity");
                    float carbonQuantity = rs.getFloat("carbonQuantity");
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
			ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                {
                    status = true;

                    String transportType = rs.getString("transportType");
                    float distance = rs.getFloat("distance");
                    float carbonQuantity = rs.getFloat("carbonQuantity");
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
