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
<body>

<%@include file="navBar.jsp"%>

</body>
<script type="text/javascript">
	function searchResto() {
		console.log("come");

		let data = $("#search").val();

		if(data!=""){
		console.log("loop"+data);
		$.ajax({
					type : "GET",
					url : "/search?search=" + data,
					success : function(data) {
						$("#hiddenSearch").show();
						/* const data1 = document.getElementById("hiddenSearch");
						data1.innerHTML = ''; */
							  $('tr').remove("#removeAnc");  
						console.log(data);
						for(i=0;i<data.length;i++){
							$("#hiddenSearch").append('<tr id="removeAnc"><td><a class="active" href="/searchMenu?searchId='+data[i].id+'&searchEmail='+data[i].clientEmail+'&restoName='+data[i].restoName+'">'+data[i].restoName+'</a></td></tr>');
						console.log("loop");
						}
						console.log("done");
						//swal("Good Job", "OTP Sent Successfull", "success");
					},
					error : function(data) {
						//swal("Opps", "OTP Not Sent Successfull", "error");

					}
				});
		}else{
			$("#hiddenSearch").hide();
		}
	}
</script>
</html>