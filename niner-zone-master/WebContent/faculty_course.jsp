<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="courses" scope="request" class="model.CourseDAO"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="faculty_header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All courses available to a faculty</title>
</head>
<body>
	<h1 class="titles">Request for Course Addition</h1>
Welcome, <c:out value="${sessionScope.userName}" /><br/>
	
	

<%@ include file="academic_board_footer.jsp"%>
</body>
</html>