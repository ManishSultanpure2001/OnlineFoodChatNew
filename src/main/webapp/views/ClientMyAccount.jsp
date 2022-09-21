<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.onlinefoodchat.entity.MenuEntity"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show All Dish</title>
<!-- <link rel="stylesheet" href="style.css"> -->
<%@include file="all_js_css.jsp"%>
</head>
<body>
	<div class="container">
	<form action="/myPlan">
	<br>
	<br>
	<br>
	<button type="submit" class="btn btn-primary btn-lg btn-block">My Plan</button>
	</form>
	<form action="/renewPlan">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
<button type="submit" class="btn btn-secondary btn-lg btn-block">Renew Plan</button>
	</form>
</div>
</body>
</html>