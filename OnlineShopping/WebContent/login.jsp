<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Shopping</title>
</head>
<body>
	<div style="background-color: aqua; width: auto; height: auto">
		<h1 align="center">Welcome to Online Shopping</h1>
	</div>
	<div align="center">
		<form action="Login?action=login" method="post">
			Email Id:<br> <input type="text" name="userName"> <br>
			Password:<br> <input type="password" name="password"> <br>
			<br> <input type="submit" value="Log In">
		</form>
	</div>
	<div align="center"
		style="margin-top: 17px; width: auto; height: auto;">
		<a href="signup.jsp" type="button">Sign Up</a>
	</div>
</body>
</html>