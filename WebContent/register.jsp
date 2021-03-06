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
  <title>Register</title>
</head>
<style>
body {font-family: Arial, Helvetica, sans-serif;}

/* Full-width input fields */
input[type=text], input[type=password], input[type=number] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus, input[type=number]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
button {
  background-color:#4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity:1;
}

/* Add padding to container elements */
.container {
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

.container-register-text{
  padding: 20px;
  text-align: center;
  height: 100px;
}


/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn, .signupbtn {
     width: 100%;
  }
}
</style>
<body>

<div class="container-app-title">
  <h1>
    <strong>RubishGram</strong>
  </h1>
</div>

<div class="container-register-text">
  <h3>Create new account</h3>
</div>

<form action="RegisterServlet" method="post" >
  <div class="container">
    
    <label for="username"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label for="firstname"><b>Firstname</b></label>
    <input type="text" placeholder="Enter Firstname" name="firstname" required>

    <label for="lastname"><b>Lastname</b></label>
    <input type="text" placeholder="Enter Lastname" name="lastname" required>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Enter Email" name="email" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>

    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
    
    <label for="budget"><b>Carbon Budget</b></label>
	<input type="number" placeholder="Enter Monthly Carbon Budget (Suggested: 500Kg)" name="budget" required>

    <button type="submit">Sign Up</button>

  </div>
</form>

</body>
</html>
