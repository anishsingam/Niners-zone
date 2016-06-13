<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="users1" scope="session" class="model.UserApprovalDAO">
</jsp:useBean>
<jsp:useBean id="courseRequests" scope="session" class="model.CourseDAO">
</jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HomePage</title>
<%@ include file="faculty_header.jsp"%>
</head>


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

<body>

	<h1 class="titles">Home Page</h1>
	Welcome,
	<%=userName%><br /> This is your session id :
	<%=sessionID%>
	<c:set var="i" value="1"></c:set>
	<%
		int i = 0;
	%>
	<p>
	<h2>
		Users pending approval<br />
	</h2>
	</p>
	<p>
	<form id="approval" method="post" action="admin_home">

		<table align="right">
			<c:set var="usersList" value="${users1.userList}">
			</c:set>
			<c:forEach items="${users1.userList}" var="user">
				<%
					i = i+1;
				%>
				<tr>
					<td><input type="checkbox" name="checked<%=i%>" /></td>
					<td><input type="text" name="username"
						value="${user.userName}" disabled="disabled" /></td>
					<td><input type="text" name="usertype<%=i%>"
						value="${user.userType}" disabled="disabled" /></td>
				</tr>
				<input type="hidden" name="username<%=i%>" value="${user.userName}" />
			</c:forEach>

		</table>
		<input type="hidden" name="numberOfUserRows"
			value="${fn:length(usersList)}" /> <input type="submit"
			value="Approve the selected users" />
	</form>

	</p>
	
	
	<p>
	<h2>
		Course Additions pending approval<br />
	</h2>
	</p>
	<p>
	


<form id="courseApproval" method="post" action="admin_home">
	<%
		i = 0;
	%>
		<table align="right">
			<c:set var="courseList" value="${courseRequests.courseAdditionRequestList}">
			</c:set>
			<c:forEach items="${courseRequests.courseAdditionRequestList}" var="courseRequest">
				<%
					
				i = i+1;
				%>
				<tr>
					<td><input type="checkbox" name="checked<%=i%>" /></td>
					<td><input type="text" name="username"
						value="${courseRequest.facultyUserName}" disabled="disabled" /></td>
					<td><input type="text" name="courseId"
						value="${courseRequest.courseId}" disabled="disabled" /></td>
					<td><input type="text" name="courseName"
					value="${courseRequest.courseName}" disabled="disabled" /></td>
					<td><input type="text" name="courseLevel"
					value="${courseRequest.courseLevel}" disabled="disabled" /></td>
				</tr>
				<input type="hidden" name="faculty_user_name<%=i%>" value="${courseRequest.facultyUserName}" />
				<input type="hidden" name="course_id<%=i%>" value="${courseRequest.courseId}" />
			</c:forEach>

		</table>
		<input type="hidden" name="numberOfCourseRequests"
			value="${fn:length(courseList)}" /> <input type="submit"
			value="Approve the selected course addition requests" />
	</form>
	</p>
	
	<%@ include file="admin_footer.jsp"%>




</body>
</html>