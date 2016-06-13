<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="course" scope="request" class="model.CourseDAO"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HomePage</title>
<%@ include file="faculty_header.jsp"%>
<script type="text/javascript">

$(document).ready(function() {
	 fillMyCourses('${sessionScope.userName}','view_courses');
	 
	//AJAX requests for college and department
	var result;
	$('#course_addition_title').hide();
	$('#course_addition_form').hide();
	
	
	$('#add_courses').click(function() {						
		fillCourses('${sessionScope.userName}');

	});
	
	$('#view_courses').change(function(){
		goToAcademicBoard('view_courses');
	});
	
	
});

function fillCourses(userName) {
	var jsonURL = 'faculty_home';
	 $.getJSON(jsonURL, function(opts) {
		if (opts) {
			 $('#course_addition_title').show();
			$('#course_addition_form').show(); 
			var i =0;
			var form = $('<form/>', {action : 'faculty_home', method : 'POST', id: 'approval'}).appendTo('#course_addition_form');
			var table = $('<table/>', {align : 'right', id:'coursetable'}).appendTo('#approval');
			var i =0;
			$.each(opts, function(keyObject, courseObject) {
				i = i+1;
				var tablerow = $('<tr/>', {id : 'tablerow'}).appendTo('#coursetable');
				
				tablerow.append("<td><input type='checkbox' name='checked" + i + "' /></td>");
				tablerow.append("<td><input type='text' name='courseid' value='" + courseObject.courseId + "' disabled='disabled'/></td>");
				tablerow.append("<td><input type='text' value='" + courseObject.courseName + "' disabled='disabled' /></td>");
				tablerow.append("<td><input type='text' name='username' value='" + courseObject.courseLevel + "' disabled='disabled' />");
				tablerow.append("<td><input type='hidden' name='courseid"+i+"' value='" + courseObject.courseId + "'/></td>");
			});
			form.append("<input type='hidden' name='username' value='" + userName + "' />");
			form.append("<input type='hidden' name='totalNumberOfCourses' value='" + i + "' />");
			form.append("<br/><input type='submit' id='savebutton' value='Save' />");
		} 
	}); 
}

function fillMyCourses(userName,ddId) {
	var dd = $('#' + ddId);
	var jsonURL = 'faculty_home?val=on';
	 $.getJSON(jsonURL, function(opts) {
		 $('>option', dd).remove(); // Clean old options first.
		 if (opts) {	
			 dd.append($('<option/>').text("Select Course"));
			 var i =1;
			$.each(opts, function(keyObject, courseObject) {
				courseName = courseObject.courseName;
				courseId = courseObject.courseId;
				optid = 'optid';
				dd.append($('<option/>').val(courseId).text(courseName));				
			});	
			
		} else {
			dd.append($('<option/>').text("No approved courses"));
		}
	}); 
}
 
function goToAcademicBoard(parentDDId) {
	var jsonURL = 'faculty_home?courseId='+ $('#' + parentDDId + ' :selected').val();
	 $.getJSON(jsonURL, function(opts) {
		if (opts) {
			$.each(opts, function(key1, value1) {					
				if(value1 == true){
					window.location.href = "academic_board.jsp";
				}
				else{
					
				}					
				
			});
		} 
	});  
}
</script>



<script type="text/javascript">




</script>
<style>
    .stick-to-right {
        width: 250px;
        float: right;
    }
</style>
</head>

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
Welcome,  <c:out value="${sessionScope.userName}" />
<p>
	<h2 id ="course_addition_title">
		The available course you can add are: <br />
	</h2>
</p>
	<%
		int i = 0;
	%>
	<div id ="course_addition_form">
	
	
	
	<%-- <form id="approval" method="post" action="admin_home">

		<table align="right">
			<c:set var="courseList" value="${courses.courseList}">
			</c:set>
			<c:forEach items="${courses.courseList}" var="course">
				<%
					i = i+1;
				%>
				<tr>
					<td><input type="checkbox" name="checked<%=i%>" /></td>
					<td><input type="text" name="courseid" value="abc" disabled="disabled" /></td>
					<td><input type="text" value="${course.courseName}" disabled="disabled" /></td>
					<td><input type="text" name="username" value="${course.courseLevel}" disabled="disabled" /></td>
				</tr>				
				<input type="hidden" name="courseid<%=i%>" value="${course.courseId}" />				
			</c:forEach>
		</table>
		<input type="hidden" name="username" value="${sessionScope.userName}" />
		<input type="hidden" name="totalNumberOfCourses" value="${fn:length(courseList)}" /> 
		<input type="submit" value="Approve the selected users" />
	</form> --%>

	
	
	</div>
	







<%@ include file="faculty_footer.jsp"%>

</body>
</html>