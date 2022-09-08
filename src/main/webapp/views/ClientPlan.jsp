<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Plan</title>
<link rel="stylesheet" href="style.css">
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
                <%
                    String input2 = (String) request.getAttribute("msgsuccess");
                if (input2 != null) {
                %>
                <div class="alert alert-success" role="alert">${msgsuccess}</div>
                <%
                    }
                %>
                <div class="card">
                    <div class="card-body">
                        <h4 class="text-center text-info">Your Plan</h4>
                        <form action="/editProfile" method="POST"
                            enctype="multipart/form-data">
                            <div class="text-center">
                                
                                    <img height="100" width="100" style="border-radius: 100px"
                                        src="profile.png" />
                               
                               
                            </div>
                            <div class="text-center">
                                <ul class="navbar-nav mr-auto">
                                <center>
                                    <li class="nav-item active"><a class="nav-link"
                                        href="/profileEdit">
                                            <h5 class="btn btn-outline-success my-2 my-sm-0">Edit
                                                Profile</h5> </span>
                                    </a></li>
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
                    <div class="col-md-4">
                        <h5>11</h5>
                    </div>
                    <div class="col-md-4">
                        <h5>11</h5>
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
                    <div class="col-md-4">
                        <h5>Abc</h5>
                    </div>
                    <div class="col-md-4">
                        <h5>Abc</h5>
                    </div>
                </div>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
    
</body>
</html>

