

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
 * Servlet implementation class OverviewServletServlet
 */
@WebServlet("/OverviewServletServlet")
public class OverviewServletServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();		
		String username = request.getParameter("username");
		if(username != null) {
			float FoodEmissions = selectFoodEmissionsbyUser(username);
            float TransportEmissions = selectTransportEmissionsbyUser(username);
            float AvgTransportEmissions = selectTransportEmissionsAvg(username);
            float AvgFoodEmissions = selectFoodEmissionsAvg(username);

			if(FoodEmissions  != -1  &&  TransportEmissions  != -1 ) {
                //Total user emissions
                float TotalUserEmmissions = FoodEmissions + TransportEmissions;
                float RemainingBugdet = RemainingBugdet - TotalUserEmmissions;
                float AvgEmissions = AvgTransportEmissions + AvgFoodEmissions;

				RequestDispatcher rd = request.getRequestDispatcher("overview.jsp");
                request.setAttribute("FoodEmissions", FoodEmissions);
                request.setAttribute("TransportEmissions", TransportEmissions);
                request.setAttribute("TotalUserEmmissions", TotalUserEmmissions);
                request.setAttribute("RemainingBugdet", RemainingBugdet);
                request.setAttribute("AvgEmissions", AvgEmissions);

                rd.forward(request, response);
			} else {
				out.print("Oops.. Something went wrong!");
				RequestDispatcher rd = request.getRequestDispatcher("overview.jsp");
				rd.include(request, response);
			}
		}
		
	}
	/* NOTE: REMEMBER TO HANDLE EMPTY TABLES ACCORDINGLY*/
	public boolean selectFoodEmissionsbyUser(String username) {
		boolean status = false;
		
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM FoodEmissions WHERE username=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

            int foodCounter = 0; 
            float carbonQuantity = 0; 
            while (rs.next())
                {
                    foodQuantity = foodQuantity + rs.getFloat("carbonQuantity");
                    transportEmission.setTotalTransportCarbonQuantity(carbonQuantity);

                    // print the results
                    System.out.format("%s, %s, %s\n", transportType, distance, carbonQuantity);
                }
            float carbonTotalQuantity = carbonQuantity;

		} catch(SQLException ex) {
			ex.printStackTrace();
            return -1;
		} catch(Exception ex) {
			ex.printStackTrace();
            return -1;
		}
		return carbonTotalQuantity;
	}

    public boolean selectTransportEmissionsbyUser(String username) {
		boolean status = false;
		
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM TransportEmissions WHERE username=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

            int transportCounter = 0;
            float carbonQuantity = 0; 
            while (rs.next())
                {
                    carbonQuantity = carbonQuantity + rs.getFloat("carbonQuantity");
                    transportEmission.setTotalTransportCarbonQuantity(carbonQuantity);

                    // print the results
                    System.out.format("%s, %s, %s\n", transportType, distance, carbonQuantity);
                }
            float carbonTotalQuantity = carbonQuantity;

		} catch(SQLException ex) {
			ex.printStackTrace();
            return -1;
		} catch(Exception ex) {
			ex.printStackTrace();
            return -1;
		}
		return carbonTotalQuantity;
	}

    public boolean selectTransportEmissionsAvg() {
		boolean status = false;
		
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM TransportEmissions";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

            int transportCounter = 0;
            float carbonQuantity = 0; 
            while (rs.next())
                {
                    carbonQuantity = carbonQuantity + rs.getFloat("carbonQuantity");
                    transportCounter = transportCounter+1 
                    // print the results
                    System.out.format("%s, %s, %s\n", transportType, distance, carbonQuantity);
                }
            float carbonAvgQuantity = carbonQuantity / transportCounter ;

		} catch(SQLException ex) {
			ex.printStackTrace();
            return -1;
		} catch(Exception ex) {
			ex.printStackTrace();
            return -1;
		}
		return carbonAvgQuantity;
	}

    public boolean selectFoodEmissionsAvg() {
		boolean status = false;
		
		ConnectionDetails connDetails = new ConnectionDetails();
		Connection conn = connDetails.getConnection();
		String sql = "SELECT * FROM FoodEmissions";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

            int foodCounter = 0; 
            float carbonQuantity = 0; 

            while (rs.next())
                {
                    carbonQuantity = carbonQuantity + rs.getFloat("carbonQuantity");
                    foodCounter = foodCounter+1 
                    // print the results
                    System.out.format("%s, %s, %s\n", transportType, distance, carbonQuantity);
                }
            float carbonAvgQuantity = carbonQuantity / foodCounter ;

		} catch(SQLException ex) {
			ex.printStackTrace();
            return -1;
		} catch(Exception ex) {
			ex.printStackTrace();
            return -1;
		}
		return carbonAvgQuantity;
}
