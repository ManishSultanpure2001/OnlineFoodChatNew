<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.onlinefoodchat.entity.OrderProductList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show All Dish</title>
<!-- <link rel="stylesheet" href="style.css"> -->
<%@include file="all_js_css.jsp"%>
</head>
<body>
	<!-- NAVBAR  -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<h1 style="color:white">All Menu</h1>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/clientDashboard">           DashBoard <span
						class="sr-only">(current)</span>
				</a></li>
				</li>
				<span class="sr-only">(current)</span>
				<li class="nav-item active"><a class="nav-link" href="/clientNotification">Notification</a></li>

				<li class="nav-item active"><a class="nav-link"
					href="/views/clientLogin.jsp">Logout </a>
			</ul>
		</div>
	</nav>
	<!--NAVBAR  -->


	<div class="container">
		<table class="table table-striped">
			<thead class="bg-info text-white">
				<tr>
					<th scope="col">Menu Id</th>
					<th scope="col">Menu Name</th>
					<th scope="col">Menu Image</th>
					<th scope="col">Menu Price</th>
					<th scope="col">Quantity</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<OrderProductList> data = (List<OrderProductList>) request.getAttribute("data");
		
				if (data != null) {
					OrderProductList menuList=null;
					Iterator<OrderProductList> iterator = data.iterator();
					while (iterator.hasNext()) {
						menuList = iterator.next();
				%>
				<tr>
					<th name="menuId"><%=menuList.getId()%></th>
					<td><%=menuList.getMenuName()%></td>
				  <td><img src="Image\\<%=menuList.getMenuImage()%>"
						width='50' height='50'></td>
					<td><%=menuList.getMenuPrice()%></td>  
					<td><%=menuList.getQuantity()%></td>  
					<%
						}%>
					<td></td>
					
				</tr>
 
			</tbody>
		</table>
		<div  class="offset-lg-9">
		<a  class="btn btn-sm btn-success" href="/orderPickup">Order Pickup</a>
			<%}
					%>
	</div>
	</div>
</body>
</html>