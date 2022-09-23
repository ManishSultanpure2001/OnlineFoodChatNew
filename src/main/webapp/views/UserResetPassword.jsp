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
<%@include file="UserDeshBoard.jsp"%>
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
						<h4 class="text-center text-primary">Change Password</h4>

						<!-- 	<form method="POST"> -->

						<div class="form-group">
							<label>Enter Email</label> <input type="email"
								class="form-control" name="userEmail" id="emailId">
						</div>


						<div class="form-group">
							<label>Enter Old Password</label> <input type="password"
								class="form-control" name="userPassword" id="oldPassword">
						</div>
						<div class="form-group">
							<label>Enter New Password</label> <input type="password"
								class="form-control" name="userPassword" id="newPassword">
						</div>

						<div class="row g-2">

							<div class="col-md">
								<div class="form-floating">
									<br>
									<button type="button"
										class="btn btn-primary btn-block btn-large" onclick="demo()">Send
										OTP</button>
								</div>
							</div>
						</div>
						<div class="form-group" id="hiddenDiv">
							<label id="emailLabel">Enter OTP</label> <input type="number"
								class="form-control" name="otp" id="hiddenText">
						</div>
						<br>
						<button class="btn btn-primary btn-block" id="form1"
							onclick="submitForm()">Save</button>
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

		$.ajax({

			type : "GET",
			url : "/loginEmail?email=" + mail,
			success : function(data) {
				console.log("done");
				swal("Good Job", "OTP Sent Successfull", "success");
				$("#hiddenDiv").show();
			},
			error : function(data) {
				swal("Opps", "OTP Not Sent Successfull", "error");

			}
		});
	}
	function submitForm() {

		var fieldValues = {
			"userEmail" : $("#emailId").val(),
			"userPassword" : $("#oldPassword").val(),
			"newPassword" : $("#newPassword").val(),
			"otp" : $("#hiddenText").val(),
		};

		$.ajax({
			url : "/resetPassword",
			data : JSON.stringify(fieldValues),
			contentType : "application/json",
			type : "POST",
			success : function(response) {
				console.log(response);
				var data = '';
				swal("Congratulations", "Password Change Successfully", "success");
				setInterval(5000);
				window.location.href = "/views/UserDeshBoard.jsp";
			},
			error : function(error) {
				swal("Opps Somthing Went Wrong!", "Please Try Again", "error");
				console.log(error);
			},
		});
	}
</script>
