

import java.io.IOException;
import java.io.PrintWriter;

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
		out.print(transportType);
		
		String distance = request.getParameter("distance");
		out.print("Distance: " + distance);
		/* Insert transport emission in TransportEmissions Table*/
		if(transportType != null && distance != null) {
			
		}
	}

}
