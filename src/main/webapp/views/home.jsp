<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<%@include file="all_js_css.jsp"%>
</head>
<body>
	<nav class="navbar navbar-expand-lg " class="navbar navbar-light"
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<h2>online-food-chaat</h2>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-4 mb-lg-0">
					
					<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbardrop"
						data-toggle="dropdown"> User </a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="signup">Login</a> <a
								class="dropdown-item" href="login">SignUp</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbardrop"
						data-toggle="dropdown"> Client </a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#section41">Login</a> <a
								class="dropdown-item" href="#section42">SignUp</a>
						</div></li>

				</ul>

			</div>
		</div>
	</nav>
</body>
</html>