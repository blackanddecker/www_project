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
    <title>Home</title>

    <style>
   		input[type="submit"]{
	   		border:none; 
	   		background:#000; 
	   		color:#fff;
	   		width:150px;
	   		height:60px;
   		}
        body {font-family: Arial, Helvetica, sans-serif;}
        button {
            background-color:#4CAF50;
            color: white;
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
	            <li class="active">
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
          
    <div class="container-main-text">
        <h3>
            Choose Activity
        </h3>
    </div>

    <div class="container">
        <form method="post" action="add_emission.jsp">
        	<input type="hidden" name="username" value="<%=request.getParameter("username")%>">
            <button type="submit">
                <h3>Add Emission</h3>
            </button>
        </form>
        <form method="post" action="AllEmissionsServlet">
         	<input type="hidden" name="username" value="<%=request.getParameter("username")%>">       
            <button type="submit">
                <h3>Check Emissions</h3>
            </button>
        </form>
        <form method="post" action="OverviewServlet">
        	<input type="hidden" name="username" value="<%=request.getParameter("username")%>">       
            <button>
                <h3>Check Current Budget</h3>
            </button>
        </form>
        <form method="post" action="settings.jsp"> 
        	<input type="hidden" name="username" value="<%=request.getParameter("username")%>">        
            <button type="submit">
                <h3>Settings</h3>
            </button>
        </form>
        <form method="post" action="index.jsp">
            <button type="submit">
                <h3>Sign Out</h3>
            </button>
        </form>
    </div>
</body>
</html>
    