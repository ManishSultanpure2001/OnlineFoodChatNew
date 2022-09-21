<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.onlinefoodchat.entity.ClientLogin"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Plan</title>
<!-- <link rel="stylesheet" href="views/style.css"> -->
<%@include file="all_js_css.jsp"%>
</head>
<body>
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
						<form action="/editProfile" method="POST"
							enctype="multipart/form-data">
							<%
								ClientLogin input2 = (ClientLogin) request.getAttribute("data");
							if (input2 != null) {
							%>
							<div class="text-center">
								<img height="100" width="100" style="border-radius: 100px"
									src="views/profile.png" />
							</div>
							<br> <br>

							<h4 class="text-center text-info">
								Your Plan Is
								<%=input2.getClientPlan()%></h4>
							<br> <br>
							<div class="text-center">
								<ul class="navbar-nav mr-auto">
									<center>

										<h5 class="btn btn-outline-success my-2 my-sm-0"
											onclick="checkPlan()">Renew Plan</h5>
										</span> </a>
										</li>
									</center>
								</ul>
							</div>
						</form>
						<br>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div class="container mt-3">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-5">
						<h5>Start Date</h5>
					</div>
					<div class="col-md-5">
						<h5>End Date</h5>
					</div>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-5">
						<h5><%=input2.getStartDate()%></h5>
					</div>
					<div class="col-md-5" id="lastDate">
						<h5><%=input2.getEndDate()%></h5>
					</div>
				</div>

				<%
					}
				%>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function checkPlan() {

		let date = $("#lastDate").first().text();

		$.ajax({

			type : "GET",
			url : "/plan?lastDate=" + date,
			success : function(data) {
				if (data == "ok") {
					console.log(data);
					swal("Good Job", "Your Plan didn't Expried", "success");
				} else {
					swal("Opps", "Your Plan didn't Expried", "error");
				}
			},
			error : function(data) {
				swal("Opps", "Your Plan didn't Expried", "error");
			}
		});
	}
</script>
