
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Participants</title>
<%@ include file="faculty_header.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	
	 if('${sessionScope.userType}'=='faculty'){
		 $("#home").attr("href", "academic_board.jsp");
	 }
	 else{
		 $("#home").attr("href", "student_academic_board.jsp");
	 } 
	getGrades('${sessionScope.userName}','${sessionScope.currentAcademicBoardId}');
	 

});

function getGrades(userName,boardId) {
	var jsonURL = "FacultyGradesViewServlet?type=student";
	
	$.getJSON(jsonURL, function(opts) {
		if (opts) {		
			
			var table = $('<table/>', {align : 'right', id:'gradestable'}).appendTo('#grades_form');
			$.each(opts, function(keyObject, gradeObject) {
				grade = gradeObject.grade;
				if(grade == null){
					grade = "NA";
				}
				var tablerow = $('<tr/>', {id : 'tablerow'}).appendTo('#gradestable');				
				tablerow.append("<td><input type='text' value='" + gradeObject.assignmentPostTitle + "' disabled='disabled' /></td>");
				tablerow.append("<td><input type='text' value='" + grade + "' disabled='disabled' /></td>");
			});				
		} 
	}); 
}



</script>
</head>
<body>

<p>
	<h2 id ="grades_title">
		The grades in this class are: <br />
	</h2>
</p>

<div id ="grades_form">
</div>
<%@ include file="view_participants_footer.jsp"%>
</body>
</html>