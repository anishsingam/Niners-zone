
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
	fillParticipants('${sessionScope.userName}','${sessionScope.currentAcademicBoardId}');
	 

});

function fillParticipants(userName,boardId) {
	var jsonURL = 'view_participants?currAcBdId='+ boardId;
	$.getJSON(jsonURL, function(opts) {
		if (opts) {		
			var table = $('<table/>', {align : 'right', id:'participantstable'}).appendTo('#participant_form');
			$.each(opts, function(keyObject, participantObject) {
				var tablerow = $('<tr/>', {id : 'tablerow'}).appendTo('#participantstable');
				tablerow.append("<td><input type='text' name='participantrollnumber' value='" + participantObject.userName + "' disabled='disabled'/></td>");
				tablerow.append("<td><input type='text' value='" + participantObject.userRollNumber + "' disabled='disabled' /></td>");
			});			
		} 
	}); 
}



</script>
</head>
<body>

<p>
	<h2 id ="participant_title">
		The participants for this course are: <br />
	</h2>
</p>

<div id ="participant_form">
</div>
<%@ include file="view_participants_footer.jsp"%>
</body>
</html>