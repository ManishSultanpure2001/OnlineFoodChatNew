<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.onlinefoodchat.entity.MenuEntity"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Menu</title>
<!-- <link rel="stylesheet" href="style.css"> -->
<%@include file="all_js_css.jsp"%>
</head>
<body class="loginBody">
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">

		<div class="row">
			<div class="col-md-6 offset-md-3">

				<div class="card">
					<div class="card-body">
						<h4 class="text-center text-primary">Update Dish</h4>

						<form action="/updateSuccessful" method="POST" enctype="multipart/form-data" >
							<% MenuEntity entity=(MenuEntity)request.getAttribute("data");%>
							<div class="form-group">
								<label>Update Menu Name *</label> <input type="text"
									class="form-control" name="menuName" id="menuName" value=<%= entity.getMenuName()%>
									>
							</div>
							<div class="form-group">
								<label>Update Price *</label> <input type="number"
									class="form-control" name="menuPrice" id="menuPrice" value=<%= entity.getMenuPrice()%>
									>
							</div>
							<div class="form-group">
								<label>Update Menu Image</label> <br> <input type="file"
									id="menuImageId" name="multipart">
									<img src="Image\\<%=entity.getMenuImage()%>"
						width='100' height='100'>
							</div>

							<div class="form-floating">
								<br>
								<button type="submit"
									class="btn btn-primary btn-block btn-large">Edit Menu</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
