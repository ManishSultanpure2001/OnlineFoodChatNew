<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
						<h4 class="text-center text-primary">Add Resto</h4>

						<form action="addResto" method="POST">

							<div class="form-group">
								<label>Enter Resto Name</label> <input type="text"
									class="form-control" name="restoName" id="restoName">
							</div>

							<div class="form-floating">
								<br>
								<button type="submit"
									class="btn btn-primary btn-block btn-large">Add Resto</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
