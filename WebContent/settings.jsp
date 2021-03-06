<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>Settings</title>

    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        input[type="submit"]{
	   		border:none; 
	   		background:#000; 
	   		color:#fff;
	   		width:150px;
	   		height:60px;
   		}
        input[type=username],
        input[type=password],
        input[type=email],
        input[type=number]{
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }
        button{
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: auto;
            border: none;
            cursor: pointer;
            height: max-content;
            width: max-content;
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
            margin: auto;
            background-color: lightblue;
            padding: 16px;
            width: 600px;
        }
        /*set styles for span and cancel button on small screens*/

        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }
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
	            <li class="active">
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
    <div class="container-welcome">
        <h3>
            Configure User Options
        </h3>
    </div>
    <!-- SETTINGS -->

    <form action="SettingsServlet" method="post">
      	<input type="hidden" name="username" value="<%=request.getParameter("username")%>">

        <div class="container">
        	<label for="budget"><b>Change Carbon Budget</b></label>
        	<input type="number" placeholder="Enter New Monthly Budget" name="budget">

            <label for="password"><b>Change Password</b></label>
            <input type="password" placeholder="Enter New Password" name="psw">

            <label for="email"><b>Change email</b></label>
            <input type="email" placeholder="Enter New email" name="email">

            <button type="submit"><b>Save Changes</b></button>

        </div>
	</form>

</body>
</html>