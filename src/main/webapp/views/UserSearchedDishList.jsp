<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.onlinefoodchat.entity.MenuEntity"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- 
<link rel="stylesheet" href="style.css"> -->
<%@include file="all_js_css.jsp"%>
<%@include file="UserDeshBoard.jsp"%>
</head>
<body>

	<div class="container">
	<br>
	<div class="row">
	
	<div col-6>
	<h1>Resto Name:- </h1>
	</div>
	<div col-6 >
		<h1 id="restoName" style="color:blue;"><%= (String)request.getAttribute("restoName")%></h1>
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
					<th scope="col">Add Cart</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<MenuEntity> data = (List<MenuEntity>) request.getAttribute("allMenu");
				if (data != null) {
					Iterator<MenuEntity> iterator = data.iterator();
					while (iterator.hasNext()) {
						MenuEntity menuEntity = iterator.next();
				%>
				<tr>

					<td ><p id="menuName<%=menuEntity.getMenuId()%>"><%=menuEntity.getMenuName()%></p></td>
					<td><p><img src="Image\\<%=menuEntity.getMenuImage()%>"
						width='100' height='100'></p></td>
						<input type="text" id="menuImage<%=menuEntity.getMenuId()%>" value="<%=menuEntity.getMenuImage()%>" hidden>
					<td ><p id="menuPrice<%=menuEntity.getMenuPrice()%>"><%=menuEntity.getMenuPrice()%></p></td>
					<td>

						<div>
							<button class="fa fa-minus" onclick="dec(<%=menuEntity.getMenuId()%>)"></button>

							<input class="col-md-2" type="text" id="incDecTextFiled<%=menuEntity.getMenuId()%>"
								readonly="readonly" value="0">

							<button class="fa fa-plus" onclick="inc(<%=menuEntity.getMenuId()%>)"></button>
						</div>
					</td>
					<td><button class="btn btn-sm btn-success"
						onclick="addCart(<%=menuEntity.getMenuId()%>,<%=menuEntity.getMenuPrice()%>)">Add Cart</button></td>
					<%
						}
					}
					%>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
<script type="text/javascript">
	
	var totalCount=0;
	var quantity=0;
	  	function dec(id){
	  		quantity=id;
			var v=$("#incDecTextFiled"+id).val();
			if(v>0)
			v=parseInt(v)-1;
			$("#incDecTextFiled"+id).val(v);
		}
	function inc(id) {	
		quantity=id;
		var v=$("#incDecTextFiled"+id).val();
		v=parseInt(v)+1;
		$("#incDecTextFiled"+id).val(v);
	}
	
	function addCart(menuId,price){
		
		var fieldValues = {
			"restoName" : $("#restoName").html(),
			"menuName" : $("#menuName"+menuId).html(),
			"menuImage" : $("#menuImage"+menuId).val(),
			"menuPrice" : $("#menuPrice"+price).html(),
			"menuQuantity"	: $("#incDecTextFiled"+quantity).val(),
		};
		console.log(fieldValues);
		$.ajax({
			url : "/addCart",
			data : JSON.stringify(fieldValues),
			contentType : "application/json",
			type : "POST",
			success : function(response) {
				console.log(response);
				swal("Good Job", "Dish Added Successfully", "success");
				setInterval(5000);
			

			},
			error : function(error) {
		
				swal("Somthing Went Wrong!", "Please Try Again", "error");
				console.log(error);
			},
		});
	}
	
</script>