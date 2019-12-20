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


    </style>

    <script>
    window.onload = function () {
        
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
                { y: 420, label: "Average User" },
                { y: 463, label: "You" }
            ]
        }]
    });
    chart.render();
    
    }
    </script>
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
                    <li><a href="allEmissions.jsp">Check Emissions</a></li>
                    <li class="active"><a href="overview.jsp">Check Current Budget</a></li>
                    <li><a href="settings.jsp">Settings</a></li>
                </ul>
            </div>
        </nav>

    <div class="container-welcome">
        <h3>
            Carbon Budget
        </h3>
    </div>
    <!-- SHOW CURRENT BUDGET & TOTAL AMOUNT OF EMISSIONS -->
    <div class="container">



        <div id="chartContainer" style="height: 300px; width: 400;"></div>

        <h3><b>October</b></h3>
        <h4>Total Budget: 500Kg</h4>

        <div id="piechart"></div>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <script type="text/javascript">
          // Load google charts
          google.charts.load('current', {'packages':['corechart']});
          google.charts.setOnLoadCallback(drawChart);

          // Draw the chart and set the chart values
          function drawChart() {
            var data = google.visualization.arrayToDataTable([
              ['Emission Type', 'CO2(Kgs)'],
              ['Transport', 328],
              ['Food', 10.1],
              ['Remaining', 161.9]
            ]);

            // Optional; add a title and set the width and height of the chart
            var options = {'title':'My Average Day', 'width':550, 'height':400};

            // Display the chart inside the <div> element with id="piechart"
            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
          }
        </script>
        <h4>Remaining Budget: 161.9Kg</h4>

    </div>



</body>
</html>