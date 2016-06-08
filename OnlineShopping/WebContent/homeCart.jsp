<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tags:head/>
<body>
	<tags:header/>

	<div style="margin: 20px; width: auto;">
  			<p align="center"><b>Cart : </b>${fn:length(ProductsInCart)}</p>
		<c:forEach var="product" items="${ProductsInCart}">
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
			<div style="margin-left: 20px;margin-bottom: 10px;display: inline-block;">
			<form style="float: left;margin-left: 20px;margin-bottom: 10px" action="Cart?pId=${product.id}&action=buy" method="post">
    					<input type="submit" value="Buy" />
			</form>
			<form style="float: left;margin-left: 20px;margin-bottom: 10px" action="Cart?pId=${product.id}&action=delete" method="post">
    					<input type="submit" value="Delete" />
			</form>
			</div>
			</details>
		</c:forEach>

	</div>
</body>
</html>