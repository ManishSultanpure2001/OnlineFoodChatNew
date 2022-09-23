<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
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
						<h4 class="text-center text-primary">Client Sign-Up</h4>

						<!-- 	<form method="POST"> -->

						<div class="form-group">
							<label class="fa fa-envelope">     Enter Email</label> <input type="email"
								class="form-control" name="clientEmail" id="emailId">
						</div>

						<div class="form-group">
							<label class="fa fa-phone">     Enter Phone</label> <input type="number"
								class="form-control" name="clientPhone" id="phone">
						</div>

						<div class="form-group">
							<label class="fa fa-unlock-alt">     Enter Password</label> <input type="password"
								class="form-control" name="clientPassword" id="password">
						</div>

						<div class="row g-2">

							<div class="col-md">
								<div class="form-floating">
									<br> <select class="form-select" id="plan"
										aria-label="Floating label select example" name="clientPlan">
										<option selected>select Any Plans</option>
										<option value="One Month">One Month buy 150 rs</option>
										<option value="Two Month">Two Month buy 290 rs</option>
										<option value="Three Month">Three Month buy 290 rs</option>
									</select>
								</div>

							</div>
							<div class="col-md">
								<div class="form-floating">
									<br>
									<button type="button"
										class="btn btn-primary btn-block btn-large" onclick="demo()">pay</button>
								</div>
							</div>
						</div>
						<div class="form-group" id="hiddenDiv">
							<label id="emailLabel">Enter OTP</label> <input type="number"
								class="form-control" name="otp" id="hiddenText">
						</div>
						<br>
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
	window.onload = function() {
		$("#hiddenDiv").hide();
	};
	function demo() {

		let mail = $("#emailId").val();
		let plan = $("#plan").val();

		$.ajax({

			type : "GET",
			url : "/email?email=" + mail + "&plan=" + plan,
			success : function(data) {
				console.log("done");
				swal("Good Job", "OTP Sent Successfull", "success");
				$("#hiddenDiv").show();
				/*  $("#emailId").hide();	 */
			},
			error : function(data) {
				swal("Opps", "OTP Not Sent Successfull", "error");
			}
		});
	}
	function submitForm() {

		var fieldValues = {
			"clientEmail" : $("#emailId").val(),
			"clientPhone" : $("#phone").val(),
			"clientPassword" : $("#password").val(),
			"clientPlan" : $("#plan").val(),
			"otp" : $("#hiddenText").val(),
		};

		$.ajax({
			url : "/clientSignup",
			data : JSON.stringify(fieldValues),
			contentType : "application/json",
			type : "POST",
			dataType : "json",
			success : function(response) {
				console.log(response);
				//var data = '';
				swal("Good Job", "Register Successfully", "success");
				setInterval(5000);
				window.location.href = "/login";

			},
			error : function(error) {
				//window.location.href = "/views/clintSignUp.jsp";
				swal(error.responseText, "Please Try Again", "error");
				console.log(error);
			},
		});
	}
</script>
