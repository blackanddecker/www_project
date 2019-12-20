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
    <title>RubishGram</title>

<style>
    body {font-family: Arial, Helvetica, sans-serif;}
    .button{
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
        width: 600px;
        height: 400px;
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

    <div class="container-welcome">
        <h3>
            Welcome to RubishGram
        </h3>
    </div>
    <div class="container">
        <form method="get" action="login.jsp">
            <button type="submit" class="button" type="submit">
                <h3><b>Sign In</b></h3>
            </button>
        </form>
        <form method="get" action="register.jsp">
            <button type="submit" class="button" type="submit">
                <h3><b>Sign Up</b></h3>
            </button>
        </form>
    </div>


</body>
</html>