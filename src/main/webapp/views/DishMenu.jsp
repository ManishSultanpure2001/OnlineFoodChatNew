<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Menu</title>
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
						<h4 class="text-center text-primary">Add Dish</h4>

						<form action="/addMenu" method="POST"
							enctype="multipart/form-data">

							<div class="form-group">
								<label>Enter Menu Name *</label> <input type="text"
									class="form-control" name="menuName" id="menuName"
									required="required">
							</div>
							<div class="form-group">
								<label>Enter Price *</label> <input type="number"
									class="form-control" name="menuPrice" id="menuPrice"
									required="required">
							</div>
							<div class="form-group">
								<label>Upload Menu Image</label> <br> <input type="file"
									id="menuImageId" name="multipart">
							</div>

							<div class="form-floating">
								<br>
								<button type="submit"
									class="btn btn-primary btn-block btn-large">Add Menu</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
