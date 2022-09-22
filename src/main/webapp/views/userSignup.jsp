<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
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
						<h4 class="text-center text-primary">User Sign-Up</h4>

						<!-- 	<form method="POST"> -->

						<div class="form-group">
							<label class="fa fa-user-circle"> Enter Name</label> <input
								type="text" class="form-control" name="userName" id="name"
								placeholder="Enter Name" required="required">
						</div>
						<div class="form-group">
							<label class="fa fa-envelope"> Enter Email</label> <input
								 placeholder="Enter Email" type="email" class="form-control" name="userEmail" id="emailId"
								required="required">
						</div>

						<div class="form-group">
							<label class="fa fa-unlock-alt"> Enter Password</label> <input
								placeholder="Enter Password" type="password" class="form-control" name="userPassword"
								id="password" required="required">
						</div>
						<br> <br>
						<div class="row g-2">

							<div class="col-md-6">
								<img alt="captcha" src="${path}/captcha-servlet">
							</div>
							<div class="col-md-6">
								<input class="form-control" id="captchaText" type="text" placeholder="Enter Captcha" name="captcha" required="required">
							</div>

						</div>
						<br> <br>
						<button class="btn btn-primary btn-block" id="form1"
							onclick="submitForm()">Signup</button>
						<!-- </form> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function submitForm() {

		var fieldValues = {
			"userName" : $("#name").val(),
			"userEmail" : $("#emailId").val(),
			"userPassword" : $("#password").val(),
			"captcha"	:$("#captchaText").val(),
		};
		$.ajax({
			url : "/save",
			data : JSON.stringify(fieldValues),
			contentType : "application/json",
			type : "POST",
			dataType : "json",
			success : function(response) {
				console.log(response);
				swal("Good Job", "Register Successfully", "success");
				setInterval(5000);
				window.location.href = "/userLogin";

			},
			error : function(error) {

				swal(error.responseText, "Please Try Again", "error");
				console.log(error);
			},
		});
	}
</script>