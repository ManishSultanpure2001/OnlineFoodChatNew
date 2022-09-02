<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="all_js_css.jsp"%>
<link rel="stylesheet" href="style.css">
<title>Login</title>
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
						<h4 class="text-center text-primary">Sign-Up</h4>

						<form action="/login" method="get">

							<div class="form-group">
								<label>Enter Email</label> <input type="email"
									class="form-control" name="clientEmail" id="emailId">
							</div>

							<div class="form-group">
								<label>Enter Phone</label> <input type="number"
									class="form-control" name="clientPhone">
							</div>

							<div class="form-group">
								<label>Enter Password</label> <input type="password"
									class="form-control" name="clientPassword">
							</div>

							<div class="row g-2">

								<div class="col-md">
									<div class="form-floating">
										<br> <select class="form-select" id="floatingSelectGrid"
											aria-label="Floating label select example" name="clientPlan">
											<option selected>select Any Plans</option>
											<option value="One Month">One Month</option>
											<option value="Two Month">Two Month</option>
											<option value="Three Month">Three Month</option>
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
						<div class="form-group">
								<label  id="emailLabel">Enter OTP</label> <input type="number"
									class="form-control" name="otp" id="hiddenText" >
							</div>
							<br>
							<button class="btn btn-primary btn-block">Signup</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


<script src="component/jquery/jquery.js" type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="component/jquery/jquery.min.js" type="text/javascript"></script>
<script src="component/jquery.validate.min.js" type="text/javascript"></script>
<script src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	function demo() {
		let mail=$("#emailId").val();
		
		$.ajax({

					type : "GET",
					url : "/email?email="+mail,
					success : function(data) {
						console.log("done");
						$("#emailLabel").show();
						$("#emailId").hide();
					},
					error : function(data) {
						$("#emailId").val("not ok");
					}
				});
	}
</script>
