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
                        <li class="active"><a href="main.jsp">Home</a></li>
                        <li><a href="add_emission.jsp">Add Emission</a></li>
                        <li><a href="allEmissions.jsp">Check Emissions</a></li>
                        <li><a href="overview.jsp">Check Current Budget</a></li>
                        <li><a href="settings.jsp">Settings</a></li>
                    </ul>
                </div>
            </nav>
    <div class="container-main-text">
        <h3>Your carbon waste is:</h3>
        <h2><strong><%= request.getParameter("carbonQuantity") %></strong> <b>kg CO2eq</b></h2>
    </div>
</div>

</body>
</html>
