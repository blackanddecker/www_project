package Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateEmissionServlet
 */
@WebServlet("/CreateEmissionServlet")
public class CreateEmissionServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		/* Check which type was selected */			
		String username = request.getParameter("username");
		String transportType = request.getParameter("transferType");
		String foodType = request.getParameter("foodType");

		if(transportType != null) {
			request.setAttribute("username", username);
			request.setAttribute("transferType", transportType);
			RequestDispatcher rd = request.getRequestDispatcher("addTransportEmission.jsp");
			rd.forward(request, response);
		} else if(foodType != null) {
			request.setAttribute("username", username);
			request.setAttribute("foodType", foodType);
			RequestDispatcher rd = request.getRequestDispatcher("addFoodEmission.jsp");
			rd.forward(request, response);
		}
		
	}
}
