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
                </ul>
            </div>
        </nav>

        <div class="container-main-text">
            <h3>Fill the necessary fields to add an emission</h3>
        </div>

        <div class="container">
            <h4>Choose Emission Type</h4>
            <button class="typeBtn" type="submit" id="transferBtn">
                Transfer
            </button>

            <button class="typeBtn" type="submit" id="foodBtn">
                Food
            </button>

            <h4>Choose Specific Type</h4>
            <form method="post" action="CreateEmissionServlet">
				<input type="hidden" name="username" value="<%=request.getParameter("username")%>">
	            <!-- TRANSFER TYPES -->
	            <button type="submit" class="specificTypeBtn" name="transferType" id="plane" value="plane" onclick="w3.hide('.specificTypeBtn')">
	                Plane
	            </button>
	            <button type="submit" class="specificTypeBtn" name="transferType" id="train" value="train" onclick="w3.hide('.specificTypeBtn')">
	                Train
	            </button>
	            <button type="submit" class="specificTypeBtn" name="transferType" id="boat" value="boat" onclick="w3.hide('.specificTypeBtn')">
	                Boat
	            </button>
	            <button type="submit" class="specificTypeBtn" name="transferType" id="car" value="car" onclick="w3.hide('.specificTypeBtn')">
	                Car
	            </button>
	            
	            <!-- FOOD TYPES -->
	            <button type="submit" class="specificTypeBtn" name="foodType" id="whiteMeat" value="whiteMeat" onclick="w3.hide('.specificTypeBtn')">
	                White Meat
	            </button>
	            <button type="submit" class="specificTypeBtn" name="foodType" id="redMeat" value="redMeat" onclick="w3.hide('.specificTypeBtn')">
	                Red Meat
	            </button>
	            <button type="submit" class="specificTypeBtn" name="foodType" id="dairy" value="dairy" onclick="w3.hide('.specificTypeBtn')">
	                Dairy
	            </button>
	            <button type="submit" class="specificTypeBtn" name="foodType" id="bread" value="bread" onclick="w3.hide('.specificTypeBtn')">
	                Cereals, Breads
	            </button>
            </form>

            <script type="text/javascript">
                document.getElementById("plane").style.display = "none";
                document.getElementById("train").style.display = "none";
                document.getElementById("boat").style.display = "none";
                document.getElementById("car").style.display = "none";

                document.getElementById("whiteMeat").style.display = "none";
                document.getElementById("redMeat").style.display = "none";
                document.getElementById("dairy").style.display = "none";
                document.getElementById("bread").style.display = "none";

                document.getElementById("transferBtn").onclick = function() {
                    document.getElementById("plane").style.display = "block";
                    document.getElementById("train").style.display = "block";
                    document.getElementById("boat").style.display = "block";
                    document.getElementById("car").style.display = "block";
                    document.getElementById("whiteMeat").style.display = "none";
                    document.getElementById("redMeat").style.display = "none";
                    document.getElementById("dairy").style.display = "none";
                    document.getElementById("bread").style.display = "none";

                }

                document.getElementById("foodBtn").onclick = function () {

                  document.getElementById("whiteMeat").style.display = "block";
                  document.getElementById("redMeat").style.display = "block";
                  document.getElementById("dairy").style.display = "block";
                  document.getElementById("bread").style.display = "block";

                  document.getElementById("plane").style.display = "none";
                  document.getElementById("train").style.display = "none";
                  document.getElementById("boat").style.display = "none";
                  document.getElementById("car").style.display = "none";

                }

            </script>








        </div>

</body>
</html>
