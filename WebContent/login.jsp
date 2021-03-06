<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>Login</title>
<style>
    body {font-family: Arial, Helvetica, sans-serif;}


input[type=username],
input[type=password] {
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
}
/*set a style for the buttons*/

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

/*set padding to the container*/

.container-login {
    margin: auto;
    background-color: lightblue;
    padding: 16px;
    width: 600px;
}

.container-app-title{
    margin:auto;
    text-align: center;
    padding: 35px;
    height: 150px;
    background-color:#4CAF50;
}

.container-login-text{
    padding: 20px;
    text-align: center;
    height: 100px;
}
/*set the forgot password text*/

span.psw {
    float: right;
    padding-top: 16px;
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

    <div class="container-login-text">
        <h3>Log In To Your Account</h3>
    </div>

    <form action="LoginServlet" method="post">
        <div class="container-login">
          <label for="username"><b>Username</b></label>
          <input type="username" placeholder="Enter Username" name="username" required>

          <label for="password"><b>Password</b></label>
          <input type="password" placeholder="Enter Password" name="psw" required>

          <button type="submit"><b>Login</b></button>
          <!-- <label>
            <input type="checkbox" checked="checked" name="remember"> Remember me
          </label> -->
          <!-- <span class="psw">Forgot <a href="#">password?</a></span> -->

        </div>
      </form>
</body>
</html>
