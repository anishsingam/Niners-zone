<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="college" scope="session" class="model.CollegeDAO"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HomePage</title>
<%@ include file="faculty_header.jsp"%>
<script type="text/javascript">

$(document).ready(function() {
	//AJAX requests for college and department
	
	fillMyCourses('${sessionScope.userName}','view_courses');

	$('#view_courses').change(function(){
		goToAcademicBoard('view_courses');
	});		
				
});

function goToAcademicBoard(parentDDId) {
	var jsonURL = 'student_home?courseId='+ $('#' + parentDDId + ' :selected').val();
	 $.getJSON(jsonURL, function(opts) {
		if (opts) {
			$.each(opts, function(key1, value1) {					
				if(value1 == true){
					window.location.href = "student_academic_board.jsp";
				}
			});
		} 
	});  
}

function fillMyCourses(userName,ddId) {
	var dd = $('#' + ddId);
	var jsonURL = 'student_home?val=on';
	 $.getJSON(jsonURL, function(opts) {
		 $('>option', dd).remove(); // Clean old options first.
		 if (opts) {	
			 dd.append($('<option/>').text("Select Course"));
			$.each(opts, function(keyObject, courseObject) {
				courseName = courseObject.courseName;
				courseId = courseObject.courseId;
				dd.append($('<option/>').val(courseId).text(courseName));				
			});	
			
		} else {
			dd.append($('<option/>').text("No registered courses"));
		}
	}); 
}
</script>

</head>


<body>

	<h1 class="titles">Welcome !!</h1>

	<%@ include file="student_footer.jsp"%>

	

</body>
</html>