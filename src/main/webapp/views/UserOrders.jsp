<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.onlinefoodchat.entity.MyOrders"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- <link rel="stylesheet" href="style.css"> -->
<%@include file="all_js_css.jsp"%>
<title>Insert title here</title>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
</head>
<body>
	<center>
		<h1 style="color: green">Your Order</h1>
	</center>

	<form action="/payOrder" method="post">
	<div class="container">
		<br>
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
		<div class="row">
			<%
				List<MyOrders> data = (List<MyOrders>) request.getAttribute("allOrders");
			if (data != null) {
				MyOrders myorders=null;
			%>
			<div col-6>
				<h3>
					Resto Name:-
					</h1>
			</div>
			<div col-6>
				<h3 id="restoName" style="color: blue;"><%=data.get(0).getRestoName()%></h1>
			</div>
		</div>
		<br>

		<table class="table table-striped">
			<thead class="bg-info text-white">
				<tr>
					<th scope="col">Order Id</th>
					<th scope="col">Resto Name</th>
					<th scope="col">Order Status</th>
					<th scope="col">Total Quantity</th>
					<th scope="col">Total Order</th>
					<th scope="col">Cancel Order</th>
				</tr>
			</thead>
			<tbody>
				<%
					Iterator<MyOrders> iterator = data.iterator();
				while (iterator.hasNext()) {
					myorders = iterator.next();
				%>
				<tr>

					<td><p id="orderId<%=myorders.getOrderId()%>"><%=myorders.getOrderId()%></p></td>
				
					<td><p id="restoName"><%=myorders.getRestoName()%></p></td>
					<td><p id="status"><%=myorders.getOrderStatus()%></p></td>
					<td><p id="quantity"><%=myorders.getTotalQuantity()%></p></td>
					<td><p id="quantity"><%=myorders.getTotalAmount()%></p></td>
					<td>
					<% if(myorders.getOrderStatus().equals("panding")){%>
					<a
						class="btn btn-sm btn-danger"
						href="/cancelOrder?orderId=<%=myorders.getOrderId()%>">Cancel Order</a></td>
			<%} %>
				</tr>
			</tbody>

<%
			}
		%>
		
		</table>
		<%
			}
		%>
	</div>
</form>
</body>

</html>
