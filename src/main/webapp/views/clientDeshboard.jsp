<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="all_js_css.jsp"%> 

<title>Client Dashboard</title>
</head>


<body class="loginBody">
	<center>
		<h1 id="deshboardHading">Welcome <%=request.getAttribute("restoName")%></h1>
	</center>
								<button type="button" class="btn btn-primary  position-relative far fa-bell offset-lg-9"><a href="/clientNotification"> 
  Notifications
  <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger ">
    99+
    <span class="visually-hidden">unread messages</span>
  </span>
  </button>
	<div class="container">
	
		<section class="attendance">
			<div class="attendance-list">
				<nav class="deshboradNav">
					<ul>
						<li><a href="#" class="logo"> <img src="./css/profile.png">
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
						<li><a href="../viewOrder"> <i  class="fa fa-eye"></i> <span
								class="nav-item">View Order </span>
						</a></li>
	
						<li><a href="../clientMyAccount"> <i class="fas fa-universal-access"></i> <span
								class="nav-item">My Account </span>
						</a></li>
						<li><a href="../login"> <i class="fas fa-cog"></i> <span
								class="nav-item">Logout </span>
						</a></li>

					</ul>
				</nav>

			</div>
	

		</section>
		
	</div>

</body>
</html>
