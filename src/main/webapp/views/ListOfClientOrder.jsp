<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.onlinefoodchat.entity.MyOrders"%>
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
		<%
	if (request.getAttribute("successMsg") != null) {
	%>
	<h1 style="color: green"><%=request.getAttribute("successMsg")%></h1>
	<%
	}
	%>
	<%
	if (request.getAttribute("errorMsg") != null) {
	%>
	<h1 style="color: red"><%=request.getAttribute("errorMsg")%></h1>
	<%
	}
	%>
		<table class="table table-striped">
			<thead class="bg-info text-white">
				<tr>
					<th scope="col">Order Id</th>
					<th scope="col">User Email</th>
					<th scope="col">Total Quantity</th>
					<th scope="col">Total Order Price</th>
					<th scope="col">Order status</th>
					<th scope="col">View Dish/Delete Order</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<MyOrders> data = (List<MyOrders>) request.getAttribute("data");
				if (data != null) {
					Iterator<MyOrders> iterator = data.iterator();
					while (iterator.hasNext()) {
						MyOrders myOrders = iterator.next();
				%>
				<tr>
					<th name="menuId"><%=myOrders.getOrderId()%></th>
					<td><%=myOrders.getUserEmail()%></td>
					<td><%=myOrders.getTotalQuantity()%></td>
					<td><%=myOrders.getTotalAmount()%></td>
					<td><%=myOrders.getOrderStatus()%></td>
					<%if(myOrders.getOrderStatus().equals("panding")) {%>
					<td><a class="btn btn-sm btn-success" href="/menuList?menuId=<%=myOrders.getOrderId()%>">View Menu List</a> 
					
					<a
						class="btn btn-sm btn-danger"
						href="/deleteOrder?menuId=<%=myOrders.getOrderId()%>">Delete Order</a></td>
						<%}
					else{
					%>
					<td><a class="btn btn-sm btn-success disabled" href="/menuList?menuId=<%=myOrders.getOrderId()%>" disabled>View Menu List</a> 
					
					<a
						class="btn btn-sm btn-danger disabled"
						href="/deleteOrder?menuId=<%=myOrders.getOrderId()%>" >Delete Order</a></td>
					<%
					}
						}
					}
					%>
				</tr>

			</tbody>
		</table>
	</div>
</body>
</html>