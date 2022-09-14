<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<%@include file="all_js_css.jsp"%>
</head>
<!DOCTYPE html>
<html lang="en">
<body class="loginBody">
	<center>
		<h1 id="deshboardHading">Welcome In Deshboard</h1>
	</center>
	<div class="container">

		<section class="attendance">
			<div class="attendance-list">
				<nav class="deshboradNav">
					<ul>
						<li><a href="#" class="logo"> <img src="profile.png">
								<span class="nav-item">Your Profile</span>
						</a></li>
						<li><a href="../checkResto"> <i class="fa fa-cutlery"></i>
								<span class="nav-item">Add Resto </span>
						</a></li>
						<li><a href="../checkResto"> <i class="fas fa-menorah"></i>
								<span class="nav-item">Add Menu </span>
						</a></li>
						<li><a href="../allMenu"> <i class="fas fa-hamburger"></i>
								<span class="nav-item">All Menu </span>
						</a></li>
						<li><a href="#"> <i  class="fa fa-eye"></i> <span
								class="nav-item">View Order </span>
						</a></li>
						
						<li><a href="#"> <i class="far fa-bell"></i> <span
								class="nav-item">Notification </span>
						</a></li>
						<li><a href="ClientMyAccount.jsp"> <i class="fas fa-universal-access"></i> <span
								class="nav-item">My Account </span>
						</a></li>
						<li><a href="../login"> <i class="fas fa-cog"></i> <span
								class="nav-item">Logout </span>
						</a></li>

					</ul>
				</nav>

			</div>
		</section>
		</section>
	</div>

</body>
</html>
