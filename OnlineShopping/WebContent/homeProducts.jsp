<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tags:head/>
<body>
	<tags:header/>
	<sql:setDataSource var="myDS" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/OnlineShopping" user="root"
		password="root" />

	<sql:query var="listProducts" dataSource="${myDS}">
        SELECT * FROM Product;
    </sql:query>

	<div style="margin: 20px; width: auto;">
		<c:forEach var="product" items="${listProducts.rows}">
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
				<b>Stock Left:</b>
				<c:out value="${product.quantity}" />
			</p>
			<form style="margin-left: 20px;margin-bottom: 10px" action="Cart?pId=${product.id}&action=add" method="post">
    			<input type="submit" value="Add To Cart" />
			</form>
			</details>
		</c:forEach>

	</div>
</body>
</html>