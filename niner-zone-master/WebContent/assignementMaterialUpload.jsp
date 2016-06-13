<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Assignment Material</title>
</head>
<%@ include file="faculty_header.jsp"%>
<body>
<%
String userName = null;
String sessionID = null;
if (session.getAttribute("userName") == null){
	response.sendRedirect("login.jsp");
}else{
	userName = (String)session.getAttribute("userName");
	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
	for(Cookie cookie : cookies){
	    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
	}
	}else{
	    sessionID = session.getId();
	}
}
%>
	<form method="post" id="amazon_uploader" action="AmazonUpload"
		enctype="multipart/form-data">
		<p>
			<label for="title">Title</label> <input type="text" id="titleMat"
				name="titleMaterial" />
		</p>
		<p>
			<label for="description">Description</label> 
			<textarea name="descriptionMaterial" rows="10" cols="50"></textarea>
		</p>
		<p>
			<label for = "fileName" >FileName</label> <br /> <input
				type="file" name="fileName" required> <br />
		</p>
		<input type="hidden" name="files" value="fileName">
		<input type="hidden" name="materialType" value="assignment">
		<input type="submit" />
	</form>
	<%@ include file="academic_board_footer.jsp"%>
		<script type="text/javascript">
	$(document).ready(function() {
		
		$('#amazon_uploader').validate(
				{
			rules : {
				titleMaterial : "required",
				descriptionMaterial : "required"
			},
			messages : {
				titleMaterial : "Enter a title for the Material",
				descriptionMaterial : "Enter a description"
			}
		});
	});

	</script>
</body>
</html>