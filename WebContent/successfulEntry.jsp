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
    <title>Successful Entry</title>

    <style>
    	input[type="submit"]{
	   		border:none; 
	   		background:#000; 
	   		color:#fff;
	   		width:150px;
	   		height:60px;
   		}
        body {font-family: Arial, Helvetica, sans-serif;}

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
	        </ul>
	    </div>
    </nav>
    <div class="container-main-text">
        <h3>Your carbon waste is:</h3>
        <h2><strong><%=request.getAttribute("carbon")%></strong> <b>kg CO2eq</b></h2>
        <input type="hidden" name="username" value="<%=request.getParameter("username")%>">
    </div>
</div>

</body>
</html>
