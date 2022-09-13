<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="style.css">
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
							<label>Enter Name</label> <input type="text"
								class="form-control" name="userName" id="name" required="required">
						</div>
						<div class="form-group">
							<label>Enter Email</label> <input type="email"
								class="form-control" name="userEmail" id="emailId" required="required">
						</div>

						<div class="form-group">
							<label>Enter Password</label> <input type="password"
								class="form-control" name="userPassword" id="password" required="required">
						</div>
						<br> <br>
						<div class="row g-2">

							<div class="col-md">
								<div class="form-floating">
									<div class="g-recaptcha"
										data-sitekey="6LfAseghAAAAAIuXMB8x0VmAemKfsCdPz1VA1ROm">
									</div>

								</div>
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
				 window.location.href = "/views/userLogin.jsp";

			},
			error : function(error) {
			
				swal("Somthing Went Wrong!", "Please Try Again", "error");
				console.log(error);
			},
		});
	}
</script>