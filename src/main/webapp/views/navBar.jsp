<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<div class="topnav">
		<a class="active fa-solid fa-house-user" href="/">Home</a> <a class="fa fa-user" href="UserMyAccount.jsp">    MyAccount</a>
		<a class="fas fa-shopping-cart " href="userLogin.jsp">    My Cart</a>
		<a class="fa fa-sign-out" href="userLogin.jsp">     Logout</a>
		<div class="search-container">
			<form action="">
				<input type="text" placeholder="Search.." name="search"
					onkeyup="searchResto()" id="search">
				<button type="button" class="fa fa-search"></button>
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
						 