<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.onlinefoodchat.entity.AddCart"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- <link rel="stylesheet" href="style.css"> -->
<%@include file="all_js_css.jsp"%>
<%@include file="UserDeshBoard.jsp"%>
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
		<%-- 	<%
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
			%> --%>
			<div class="row">
				<%
					List<AddCart> data = (List<AddCart>) request.getAttribute("allAddedCart");
				if (data != null && data.size() > 0) {
					AddCart addCart = null;
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
						<th scope="col">Menu Name</th>
						<th scope="col">Menu Image</th>
						<th scope="col">Menu Price</th>
						<th scope="col">Menu Quantity</th>
						<th scope="col">Total Order</th>
					</tr>
				</thead>
				<tbody>
					<%
						Iterator<AddCart> iterator = data.iterator();
					while (iterator.hasNext()) {
						addCart = iterator.next();
					%>
					<tr>

						<td><p id="menuName<%=addCart.getCartId()%>"><%=addCart.getMenuName()%></p></td>
						<td><p>
								<img src="Image\\<%=addCart.getMenuImage()%>" width='50'
									height='50'>
							</p></td>
						<input type="text" id="menuImage<%=addCart.getCartId()%>"
							value="<%=addCart.getMenuImage()%>" hidden>
						<td><p id="menuPrice<%=addCart.getMenuPrice()%>"><%=addCart.getMenuPrice()%></p></td>
						<td>
							<!--  <div> --> <%-- <button class="fa fa-minus"
								onclick="dec(<%=addCart.getMenuQuantity()%>,<%=addCart.getMenuPrice()%>,<%=addCart.getCartId()%>)"></button>
 --%> <%-- <input class="col-md-2" type="text"
								id="incDecTextFiled<%=addCart.getMenuQuantity()%>"
								readonly="readonly" value="<%=addCart.getMenuQuantity()%>">
 --%> <%-- <button class="fa fa-plus"
								onclick="inc(<%=addCart.getMenuQuantity()%>,<%=addCart.getMenuPrice()%>,<%=addCart.getCartId()%>)"></button>
						--%>
							<p id="incDecTextFiled<%=addCart.getMenuQuantity()%>"><%=addCart.getMenuQuantity()%></p>
							<!-- </div> -->
						</td>
						<td>
							<p id="menuTotlePrice<%=addCart.getTotlePrice()%>"><%=addCart.getTotlePrice()%></p>
						</td>
					</tr>
				</tbody>

				<%
					}
				%>

			</table>
			<div class="row">
				<div class="offset-lg-9">
					<h3 id="totalAmount"><%=addCart.getSumOfTotlePrice()%><h3>
				</div>
			</div class="offset-lg-9">
			<!-- 
		<button class="btn btn-sm btn-danger  offset-lg-9" onclick="addCart()">Cancle
			Order</button> -->
			<button class="btn btn-sm btn-success" type="submit">Place
				Order</button>
			<!-- <button class="btn btn-sm btn-success" onclick="payment()">Pay</button> -->
			<!-- 		<button class="btn btn-sm btn-success" onclick="payment()">Place
			Order</button> -->
			<%
				}
			%>
		</div>
	</form>
</body>

</html>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<script>
	var totalPrice=0;

	  	function dec(quantity,menuPrice,dishId){
	  		 
			var v=$("#incDecTextFiled"+quantity).val();
			if(v>0){
			v=(parseInt(v)-1);
			totalPrice=v*menuPrice;
			}
			$("#incDecTextFiled"+quantity).val(v);
			this.finalPrice(dishId,v,totalPrice);
		}
	function inc(quantity,menuPrice,dishId) {	
		
		var v=$("#incDecTextFiled"+quantity).val();
		v=parseInt(v)+1;
		$("#incDecTextFiled"+quantity).val(v);
		totalPrice=parseInt(v)*parseInt(menuPrice);
			this.finalPrice(dishId,v,totalPrice);
	}
	
	function finalPrice(dishId,v,totalPrice){
		const heading = document.getElementById('heading');
		$.ajax({
		
			type : "GET",
			url : "/updatePrice?quantity=" + v + "&totalPrice=" + totalPrice+"&dishId="+dishId,
			success : function(data) {
				console.log("done");
				heading.textContent = data;
				location.reload();
				
			},
			error : function(data) {
			}
		});
	}
	
  	function payment(){
  		debugger
  		console.log("Called");
  		let amount = $("#totalAmount").html();
  		if (amount == '' || amount == null || amount==0.0 ) {
  			alert("Add Something to buy");
  			return;
  		}
  		$.ajax({
  			url : "/payment",
  			method : "POST",
  			data : JSON.stringify({
  				"amount" : amount
  			}),
  			contentType : 'application/json',
  			dataType : 'json',
  			success : function(result) {
  				debugger
  				console.log(result)
  				 console.log("going inside")
  				var options = {
  					"key" : "rzp_test_pD0YjVtHpPgAXK", // Enter the Key ID
  														// generated from the
  														// Dashboard
  					"amount" : result.amount, // Amount is in currency subunits.
  												// Default currency is INR. Hence,
  												// 50000 refers to 50000 paise
  					"currency" : "INR",
  					"name" : "Online food Chaat",
  					"description" : "Test Transaction",
  					"order_id" : result.id, // This is a sample Order ID. Pass the
  											// `id` obtained in the response of Step
  											// 1
  				  	"handler" : function(response) {
  						/* console.log(response.razorpay_payment_id);
  						console.log(response.razorpay_order_id);
  						console.log(response.razorpay_signature); */
  	               /*      $.ajax({
  							url : "/payOrder",
  							method : "POST",
  							success : function(result) {
  								alert("Order Placed Successfully");
  								$("#totalAmount").html('0.0');
  								$('tr').remove(".rem");
  							},
  							error : function(result) {
  								console.log(result.status)
  								console.log(result + "nhi Aaya");
  							}
  						}); */
  						console.log("payment done");
  				  	}, 
  					"prefill" : {
  						"name" : "",
  						"email" : "",
  						"contact" : ""
  					},
  					"notes" : {
  						"address" : "Online Food Chat"
  					},
  					"theme" : {
  						"color" : "#405888"
  					}
  				};
  				var rzp1 = new Razorpay(options);
  				rzp1.on('payment.failed', function(response) {
  					 alert(response.error.code);
  					alert(response.error.description);
  					alert(response.error.source);
  					alert(response.error.step);
  					alert(response.error.reason);
  					alert(response.error.metadata.order_id);
  					alert(response.error.metadata.payment_id);
  				});
  				rzp1.open();  
  			},
  			error : function(result) {
  				console.log(result)
  				alert("Something went Wrong");
  			}
  		});
  		
	} 
	
	</script>