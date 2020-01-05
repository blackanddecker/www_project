<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="https://www.w3schools.com/lib/w3.js"></script>
    <title>Add Emission</title>
    <style>
    	input[type="submit"]{
	   		border:none; 
	   		background:#000; 
	   		color:#fff;
	   		width:150px;
	   		height:60px;
   		}
   		input[type=number]{
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }
        body {font-family: Arial, Helvetica, sans-serif;}
        button {
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
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

        .container-main-text{
            padding: 20px;
            text-align: center;
            height: 100px;
        }

        .container{
            background-color: lightblue;
            margin: auto;
            width: 600px;
            height: 600px;
            padding: 40px;
        }
        .typeBtn{
            width: max-content;
            height: max-content;
            background-color: white;
            color: black;
            border: 2px solid #f44336;
        }
        .typeBtn:hover {
            background-color: #f44336;
            color: white;
        }
        .specificTypeBtn {
            width: max-content;
            height: max-content;
            background-color: white;
            color: black;
            border: 2px solid #f44336;
        }
        .specificTypeBtn:hover{
            background-color: #f44336;
            color: white;
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
                    <li class="active">
                    	<form method="post" action="add_emission.jsp">
                    		<input type="hidden" name="username" value="<%=request.getParameter("username")%>">
                    		<input type="submit" Value="Add Emission">
                    	</form>
                    </li>
                    <li>
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
                    <li>
		        	    <form method="post" action="index.jsp">
	            			<input type="submit" value="Log out">
	        			</form>
	            	</li>
                </ul>
            </div>
        </nav>
        <div class="container">
	        <form method="post" action="AddTransportEmissionServlet">
	        	<input type="hidden" name="username" value="<%= request.getParameter("username") %>">
	        	<input type="hidden" name="transferType" value="<%= request.getParameter("transferType") %>" />
	            <label for="distance">Add Distance (in Kilometers)<br></label>
	            <input id="distance" type="number" name="distance" value="distance" required><br><br></input>
	           	<button type="submit">Submit</button>
	        </form>
        </div>

</body>
</html>
