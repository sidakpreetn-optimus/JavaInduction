<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Shopping</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="background-color: aqua; width: auto; height: auto">
		<h1 align="center">Welcome to Online Shopping</h1>
		<div class="tabs" align="center">
			<ul class="list-inline">
				<li><a href="homeProducts.jsp">Products</a></li>
				<li><a href="ProductsByCategory">Product Categories</a></li>
				<li><a href="Cart">My Cart</a></li>
				<li><a href="Profile">My Profile</a></li>
			</ul>
		</div>
	</div>

	<div style="margin: 20px; width: auto;">
  			<p align="center"><b>Purchased Items : </b>${fn:length(ProductsPurchased)}</p>
  			<div align="center">
  			<form style="margin-left: 20px;margin-bottom: 10px" action="Login?action=logout" method="post">
    			<input type="submit" value="Sign Out" />
			</form>
			</div>
		<c:forEach var="product" items="${ProductsPurchased}">
			<details> <summary> <c:out
				value="${product.name}" /></summary>
			<p style="margin-left: 20px; margin-top: 10px">
				<b>Category:</b>
				<c:out value="${product.category}" />
			</p>
			<p style="margin-left: 20px">
				<b>Price:</b>
				<c:out value="${product.price}" />
			</p>
			<p style="margin-left: 20px">
				<b>Quantity:</b>
				<c:out value="${product.quantity}" />
			</p>
			<div style="margin-left: 0px;margin-bottom: 0px;display: inline-block;">
			</div>
			</details>
		</c:forEach>

	</div>
</body>
</html>