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

<link rel="stylesheet" href="style.css">
<%@include file="all_js_css.jsp"%>

</head>
<body>

	<div class="container">
		
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

					<td id="menuName"><%=menuEntity.getMenuName()%></td>
					<td><img src="Image\\<%=menuEntity.getMenuImage()%>"
						width='100' height='100'></td>
					<td><%=menuEntity.getMenuPrice()%> Rs</td>
					<td>

						<div>
							<button id="dec" onclick="dec()">-</button>

							<input class="col-md-2" type="text" id="incDecTextFiled"
								readonly="readonly">

							<button class="inc">+</button>
						</div>
					</td>
					<td><a class="btn btn-sm btn-success"
						href="/editDish?menuId=<%=menuEntity.getMenuId()%>">Add Cart</a></td>
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
	var count = 0;
	$('#incDecTextFiled').val();

	  	function dec(){
			
			totalCount.innerHTML = --count
		}
	function inc() {
		console.log(document.getElementById("menuName"));

		totalCount.value = ++count
	}
</script>