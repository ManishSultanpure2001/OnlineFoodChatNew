<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<div class="topnav">
		<a class="active" href="#home">Home</a> <a href="#about">MyAccount</a>
		<a href="#contact">Contact</a>
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
 						<div  class="row align-items-end">
 						
						<div  class="col-3 offset-lg-9" role="tablist" id="hiddenSearch">
						</div>
						 </div>