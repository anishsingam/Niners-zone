<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<%@ include file="register_login_template_header.jsp"%>
<script src="js/login_page.js"></script>
</head>
<body>

	<h1 class="titles">Login</h1>
	<form id="login" method="post" action="login">
		<label for="user_name">UserName</label>
		<input type="text" id="user_name" name="userName" />
		<label for="password">Password</label>
		<input type="password" id="password" name="userPassword" />
		<input type="submit" />
	</form>
	
	<%
	String s = "";
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null){
s = login_msg.toString();
}

%>
<input id="error_message" type="hidden" name="error_msg" value="<%=s %>" />
	<%@ include file="register_login_template_footer.jsp" %>
	

</body>
</html>