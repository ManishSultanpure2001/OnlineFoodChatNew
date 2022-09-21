<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="all_js_css.jsp"%> 
	<div class="topnav">
	<!-- <button type="button" class="btn btn-primary position-relative"><a href="UserNotification.jsp">  -->
 <button type="button" class="btn btn-primary position-relative"><a href="/userNotification"> 
  Notifications
  <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    99+
    <span class="visually-hidden">unread messages</span>
  </span>
</button>
		<a class="active fa-solid fa-house-user" href="/">Home</a> <a class="fa fa-user" style="color:green;" href="/userMyAccount">    MyAccount</a>
	
		<a class="fas fa-shopping-cart " style="color:green;" href="/myOrder">    My Cart</a>
		<a class="fa-brands fa-first-order" style="color:green;" href="/allOrders">    My Orders</a>
		<a class="fa fa-sign-out" style="color:green;" href="/userLogout">     Logout</a>
		<div class="search-container">
			<form action="">
				<input type="text" placeholder="Search.." name="search"
					onkeyup="searchResto()" id="search">
				<button type="button" class="fa fa-search" style="color:green;"></button>
			</form>
		</div>
	</div>
	<div style="padding-left: 16px">
		<!-- <h2>Responsive Search Bar</h2>
  <p>Navigation bar with a search box and a submit button inside of it.</p>
  <p>Resize the browser window to see the responsive effect.</p> -->
	</div>
 						<div class="card col-md-2 offset-lg-10 fixed-top mt-5" style="" id="hiddenSearch">
 						 
				 		<!-- <div  class="dropdown-menu"  role="tablist" id="hiddenSearch">  -->
					   <!-- <ul class="col-3 offset-lg-9   dropdown-menu"  role="menu"  aria-labelledby="dropdownMenu" id="hiddenSearch"> 
						  </ul> -->  
						  <table class="col-3 offset-lg-9">
			<tbody    id="searchData">
			</tbody>
		</table>
					  </div>  
						 