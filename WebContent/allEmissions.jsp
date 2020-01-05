<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="Utils.*"%>
<%@ page import ="Servlets.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>All Emissions</title>

    <style>
    	input[type="submit"]{
	   		border:none; 
	   		background:#000; 
	   		color:#fff;
	   		width:150px;
	   		height:60px;
   		}
        body {font-family: Arial, Helvetica, sans-serif;}
        button{
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            height: 100px;
            width: 100%;
        }

        /* set a hover effect for the button*/

        button:hover {
            opacity: 0.8;
        }

        .container-app-title{
            margin:auto;
            text-align: center;
            padding: 35px;
            height: 150px;
            background-color:#4CAF50;
        }

        .container-welcome{
            padding: 30px;
            text-align: center;
            height: 100px;
        }

        .container{
            background-color: lightblue;
            margin: auto;
            padding: 80px;
            width: max-content;
            height: max-content;
        }
        /*set styles for span and cancel button on small screens*/

        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
        }
        th {
            text-align: left;
        }
    </style>
</head>
<body>
    <div class="container-app-title">
	    <h1>
	        <strong>RubishGram</strong>
	    </h1>
    </div>
	<nav class="navbar navbar-inverse">
	    <div class="container-fluid">
	        <ul class="nav navbar-nav">
	            <li>
	            	<form method="post" action="main.jsp">
	            		<input type="hidden" name="username" value="<%=request.getParameter("username")%>">
	            		<input type="submit" Value="Home">
	            	</form>
	            </li>
	            <li>
	            	<form method="post" action="add_emission.jsp">
	            		<input type="hidden" name="username" value="<%=request.getParameter("username")%>">
	            		<input type="submit" Value="Add Emission">
	            	</form>
	            </li>
	            <li class="active">
	            	<form method="post" action="AllEmissionsServlet">
	            		<input type="hidden" name="username" value="<%=request.getParameter("username")%>">
	            		<input type="submit" Value="Check Emissions">
	            	</form>
	            </li>
	            <li>
	            	<form method="post" action="OverviewServlet">
	            		<input type="hidden" name="username" value="<%=request.getParameter("username")%>">
	            		<input type="submit" Value="Check Current Budget">
	            	</form>
	            </li>
	            <li>
	            	<form method="post" action="settings.jsp">
	            		<input type="hidden" name="username" value="<%=request.getParameter("username")%>">
	            		<input type="submit" Value="Settings">
	            	</form>
	            </li>
	        </ul>
	    </div>
	</nav>
    <div class="container-welcome">
        <h3>
            See all of your emissions
        </h3>
    </div>
    <div class="container">
	        <input type="hidden" name="username" value="<%=request.getParameter("username")%>">
	        <h4><b>Transport Table</b></h4>
	        <table>
		        <thead>
		            <tr>
		                <th>Transport Type</th>
		                <th>Distance (Km)</th>
		                <th>Quantity of CO2(Kgs) Spent</th>
		                <th>Date of Emission</th>
		            </tr>
		        </thead>
		        <tbody>
		            <% 
		            ArrayList<TransportEmission> transportEmissionsList = (ArrayList<TransportEmission>) request.getAttribute("TransportEmissions");
		            if(transportEmissionsList != null) {
		           		for(TransportEmission transportEmission : transportEmissionsList) {
		            %>
		            <tr>
		                <td><%=transportEmission.getTransportType()%></td>
		                <td><%=transportEmission.getDistance()%></td>
		                <td><%=transportEmission.getCarbonQuantity()%></td>
		                <td><%=transportEmission.getDate() %></td>
		                </tr>
		            <%
		           		};
		            }
		            %>
		        </tbody>
	        </table>
	        <h4><b>Food Table</b></h4>
			<table>
		        <thead>
		            <tr>
		                <th>Food Type</th>
		                <th>Quantity of Food (Kgs)</th>
		                <th>Quantity of CO2 (Kgs) Spent</th>
		                <th>Date</th>
		            </tr>
		        </thead>
		        <tbody>
		            <% 
		            ArrayList<FoodEmission> foodEmissionsList = (ArrayList<FoodEmission>) request.getAttribute("FoodEmissions");
		            if(foodEmissionsList != null) {
		            	for(FoodEmission foodEmission : foodEmissionsList) {
		            %>
		            <tr>
		                <td><%=foodEmission.getFoodType()%></td>
		                <td><%=foodEmission.getFoodQuantity()%></td>
		                <td><%=foodEmission.getCarbonQuantity()%></td>
		                <td><%=foodEmission.getDate() %></td>
		                </tr>
		            <%
		            	};
		            }
		            %>
		        </tbody>
			</table>
	        
	</div>
</body>
</html>