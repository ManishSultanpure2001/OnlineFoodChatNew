<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.Iterator"%>
<%@page import="com.onlinefoodchat.entity.AddCart"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<%@include file="all_js_css.jsp"%>
<title>Insert title here</title>
</head>
<body>
<center>
<h1 style="color:green">Your Order</h1>
</center>

	<div class="container">
	<br>
	<div class="row">
	
	<div col-6>
	<h3>Resto Name:- </h1>
	</div>
	<div col-6 >
		<h3 id="restoName" style="color:blue;"><%= (String)request.getAttribute("restoName")%></h1>
	</div>
	</div>
		<br>
		
		<table class="table table-striped">
			<thead class="bg-info text-white">
				<tr>
					<th scope="col">Menu Name</th>
					<th scope="col">Menu Image</th>
					<th scope="col">Menu Price</th>
					<th scope="col">Dec/Inc</th>
					<th scope="col">Totle Order</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<AddCart> data = (List<AddCart>) request.getAttribute("allAddedCart");
				if (data != null) {
					Iterator<AddCart> iterator = data.iterator();
					while (iterator.hasNext()) {
						AddCart addCart = iterator.next();
				%>
				<tr>

					<td ><p id="menuName<%=addCart.getCartId()%>"><%=addCart.getMenuName()%></p></td>
					<td><p><img src="Image\\<%=addCart.getMenuImage()%>"
						width='50' height='50'></p></td>
						<input type="text" id="menuImage<%=addCart.getCartId()%>" value="<%=addCart.getMenuImage()%>" hidden>
					<td ><p id="menuPrice<%=addCart.getMenuPrice()%>"><%=addCart.getMenuPrice()%></p></td>
					<td>

						<div>
							<button class="fa fa-minus" onclick="dec(<%=addCart.getMenuQuantity()%>,<%=addCart.getMenuPrice()%>)"></button>

							<input class="col-md-2" type="text" id="incDecTextFiled<%=addCart.getMenuQuantity()%>"
								readonly="readonly" value="<%=addCart.getMenuQuantity()%>">

							<button class="fa fa-plus" onclick="inc(<%=addCart.getMenuQuantity()%>,<%=addCart.getMenuPrice()%>)"></button>
						</div>
					</td>
					<td>
					<p id="menuTotlePrice<%=addCart.getTotlePrice()%>"><%=addCart.getTotlePrice()%></p>
					</td>
				</tr>
			</tbody>
		</table>
		<button class="btn btn-sm btn-danger  offset-lg-9"
						onclick="addCart(<%=addCart.getCartId()%>,<%=addCart.getMenuPrice()%>)">Cancle Order</button>
						<button class="btn btn-sm btn-success"
						onclick="addCart(<%=addCart.getCartId()%>,<%=addCart.getMenuPrice()%>)">Place Order</button>
	
					<%
						}
					}
					%>
	</div>

</body>

</html>
<script type="text/javascript">
	var totalPrice=0;
	var quantity=0;
	  	function dec(id,menuPrice){
	  		quantity=id;
			var v=$("#incDecTextFiled"+id).val();
			if(v>0){
			v=(parseInt(v)-1);
			totalPrice=v*menuPrice;
			}
			$("#incDecTextFiled"+id).val(v);
			this.finalPrice();
		}
	function inc(id,menuPrice) {	
		quantity=id;
		var v=$("#incDecTextFiled"+id).val();
		v=parseInt(v)+1;
		$("#incDecTextFiled"+id).val(v);
		totalPrice=parseInt(v)*parseInt(menuPrice);
			this.finalPrice(totalPrice);
	}
	
	function finalPrice(){
		console.log("function Called");
	}
	</script>