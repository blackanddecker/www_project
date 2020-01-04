<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%
        ArrayList<FoodEmissions> list = new ArrayList<FoodEmissions>();
        ArrayList<TransportEmissions> list = new ArrayList<TransportEmissions>();
        transportEmissionslist = (ArrayList<TransportEmissions>) request.getAttribute("TransportEmissions");
        foodEmissionslist = (ArrayList<FoodEmissions>) request.getAttribute("FoodEmissions");
    %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>All Emissions</title>

    <style>
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
                        <li><a href="main.jsp">Home</a></li>
                        <li><a href="add_emission.jsp">Add Emission</a></li>
                        <li class="active"><a href="allEmissions.jsp">Check Emissions</a></li>
                        <li><a href="overview.jsp">Check Current Budget</a></li>
                        <li><a href="settings.jsp">Settings</a></li>
                    </ul>
                </div>
            </nav>

    <div class="container-welcome">
        <h3>
            See all of your emissions
        </h3>
    </div>

    <!-- PRINT EMISSIONS IN LIST -->
    <div class="container">
        <form method="post" action="AllEmissionsServlet">
        <input type="hidden" name="username" value="<%=request.getParameter("username")%>">
        
        <h4><b>Transport Table</b></h4>

        <table>
                <thead>
                    <tr>
                        <th>Transport Type</th>
                        <th>Distance (Km)</th>
                        <th>Quantity of CO2(Kgs) Spent</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(int i = 0; i < transportEmissionslist.size(); i++) {
                        TransportEmissions temissions = new TransportEmissions();
                        temissions = transportEmissionslist.get(i);
                        //out.println(temissions.getId());
                        //out.println(temissions.getFirstname());
                        //out.println(temissions.getLastname());
                    %>
        
        
                    <tr>
                        <td><%=temissions.gettransportType()%></td>
                        <td><%=temissions.getdistance()%></td>
                        <td><%=temissions.getcarbonQuantity()%></td>
                        </tr>
                    <%
                    };
                    %>
                </tbody>
            </table>

        <h4><b>Transport Table</b></h4>

        <table style="width:max-content">
            <tr>
                <th>Transport Type</th>
                <th>Duration (Hours)</th>
                <th>Quantity of CO2(Kgs) Spent</th>
            </tr>
            <tr>
                <td>Flight</td>
                <td>2</td>
                <td>230</td>
            </tr>
            <tr>
                <td>Train</td>
                <td>4</td>
                <td>18</td>
            </tr>
            <tr>
                <td>Car</td>
                <td>18</td>
                <td>80</td>
            </tr>
        </table>

        <h4><b>Food Table</b></h4>

        <table style="width:max-content">
            <tr>
                <th>Food Type</th>
                <th>Quantity of food (Kgs) Spent</th>
                <th>Quantity of CO2(Kgs) Spent</th>
            </tr>
            <tr>
                <td>White meat</td>
                <td>0.2</td>
                <td>2.1</td>
            </tr>
            <tr>
                <td>Red Meat</td>
                <td>0.3</td>
                <td>7.5</td>
            </tr>
            <tr>
                <td>Bread</td>
                <td>1.3</td>
                <td>0.9</td>
            </tr>
        </table>
    </div>

</body>
</html>