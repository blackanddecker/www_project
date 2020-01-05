<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>Overview</title>

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
        .danger-text {
	        margin:auto;
            text-align: center;
            padding: 35px;
            height: 150px;
            color:red;
        }
        .ok-text{
        	margin:auto;
        	text-align: center;
            padding: 35px;
            height: 150px;
            color:blue;
        	
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
    <% 	if("overLimit".equals(request.getAttribute("warning"))) { 		
	    %>
		    <div class="danger-text">
		    	<h2><b>FAIL</b><br></h2>
		    	<h4>Your emissions have overcome the available budget for this month! <br>
		    	The difference is: <%=request.getAttribute("offset") %> Kg</h4>
		    </div>
	    <%
    	} else if("aboveMean".equals(request.getAttribute("warning"))) {
    	%>
    		<div class="danger-text">
		    	<h2><b>WARNING</b><br></h2>
		    	<h4>Be careful, your carbon usage is high this month!</h4>
		    </div>
	    <% } else if("belowMean".equals(request.getAttribute("warning"))) {
	    %>
	    	<div class="ok-text">
	    	<h2><b>Congratulations!</b><br></h2>
	    	<h4>Keep going like that. You are helping save the environment.<br></h4></div>
	    <%} %>
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
	            <li class="active">
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

	<script>
    window.onload = function () {

   	var avg = ${AvgEmissions};
   	var total = ${TotalUserEmmissions};
   	
    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        
        title:{
            text:""
        },
        axisX:{
            interval: 1
        },
        axisY2:{
            interlacedColor: "rgba(1,77,101,.2)",
            gridColor: "rgba(1,77,101,.1)",
        },
        data: [{
            type: "bar",
            name: "companies",
            axisYType: "secondary",
            color: "#006400",
            dataPoints: [
                { y: avg, label: "Average User"}, 
                { y: total, label: "You"}
            ]
        }]
    });
    chart.render();
    
    }
    </script>
    <div class="container-welcome">
        <h3>
            Carbon Budget
        </h3>
    </div>
    
    
    <!-- SHOW CURRENT BUDGET & TOTAL AMOUNT OF EMISSIONS -->
    <div class="container">



        <div id="chartContainer" style="height: 300px; width: 400;"></div>

        <h3><b>This Month</b></h3>
        <h4>Total Budget: <%=request.getAttribute("TotalUserBudget") %> Kg</h4>

        <div id="piechart"></div>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <script type="text/javascript">
          // Load google charts
          google.charts.load('current', {'packages':['corechart']});
          google.charts.setOnLoadCallback(drawChart);

          // Draw the chart and set the chart values
          function drawChart() {
        	  var transportEmissions = ${TransportEmissions};
        	  var foodEmissions = ${FoodEmissions};
        	  var remaining = ${RemainingBudget};
        	  
            var data = google.visualization.arrayToDataTable([
              ['Emission Type', 'CO2(Kgs)'],
              ['Transport', transportEmissions],
              ['Food', foodEmissions],
              ['Remaining', remaining]
            ]);

            // Optional; add a title and set the width and height of the chart
            var options = {'title':'My Average Day', 'width':550, 'height':400};

            // Display the chart inside the <div> element with id="piechart"
            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
          }
        </script>
        <h4>Remaining Budget: <%=request.getAttribute("RemainingBudget")%> Kg</h4>

    </div>



</body>
</html>