<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.onlinefoodchat.entity.Notification"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

 <%@include file="all_js_css.jsp"%> 

</head>
<body>
<section class="section-50">
    <div class="container">
        <h3 class="m-b-50 heading-line">Notifications <i class="fa fa-bell text-muted"></i></h3>
     <%List<Notification> data=(List<Notification>)request.getAttribute("clientNotification"); 
    if (data != null) {
		Iterator<Notification> iterator = data.iterator();
		while (iterator.hasNext()) {
			 Notification notification= iterator.next();
			 if(notification.getClientDeleteStatus()==0){
    %>
		
        <div class="notification-ui_dd-content">
            <div class="notification-list notification-list--unread">
                <div class="notification-list_content">
                    <div class="notification-list_img">
                        <img src="https://i.imgur.com/zYxDCQT.jpg" alt="user">
                    </div>
                    <div class="notification-list_detail">
                       
                    
                     <p><b>Order ID    </b><b style="color:blue;"><%= notification.getOrderid()%></b></p>
                        <p><b>Resto Name   </b><b style="color:red;"><%=notification.getRestoName() %></b></p>
                        <p class="text-muted" ><%=notification.getClintMessage() %></p>
                        <p class="text-muted"><small style="color:green;"><%=notification.getStatus() %></small></p>
                    </div>
                </div>
                 <div class="pull-right">
                        <button class="fa-sharp fa-solid fa-circle-xmark" onclick="deleteNotification(<%= notification.getOrderid()%>)" style="color:red;"></button>
						
                    </div>
               </div>
               </div>
        
<%
			 }
		}
		}
%>
       
    </div>
</section>
</body>
<script type="text/javascript">
function deleteNotification(orderId) {


		$.ajax({

			type : "GET",
			url : "/clientDeleteNotification?orderId=" + orderId,
			success : function(data) {
				console.log("done");
				swal("Good Job", "Delete Successfull", "success");
				location.reload();
			},
			error : function(data) {
				swal("Opps", "Delete Not Successfull", "error");

			}	
		});
	}
	</script>
</html>