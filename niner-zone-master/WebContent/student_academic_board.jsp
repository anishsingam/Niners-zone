
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Academic Board</title>
<%@ include file="faculty_header.jsp"%>
<script type="text/javascript">

$(document).ready(function() {
	
	fillAcademicBoard('${sessionScope.userName}','${sessionScope.currentAcademicBoardId}');
	fillAssignmentPosts('${sessionScope.currentAcademicBoardId}');
	
	$('#view_participants').click(function() {						
		window.location.href = "view_participants.jsp";
	});
	
	$('#view_board').click(function() {						
		location.reload();
	});
});	


function fillAcademicBoard(userName,boardId) {
	var jsonURL = "faculty_academic_board?type=academic";
	 $.getJSON(jsonURL, function(opts) {
		 if (opts) {	 
				
				var paragraph = $('<p/>', {id : 'acadMaterial'}).appendTo('#academic_board_list');
				
				$.each(opts, function(keyObject, acadMaterialObject) {
										
					paragraph.append("<strong><h1>Title: </h1></strong>" + acadMaterialObject.title + "<br/><br/>");
					paragraph.append("<strong><h1>Description: </h1></strong>" + acadMaterialObject.description + "<br/><br/>");
					paragraph.append("<strong><h1>Link: </h1></strong> <a href='" + acadMaterialObject.link + "'>Download</a><br/>");
					paragraph.append("<hr/>");
										
				});
				
			
		} 
	}); 
}

function fillAssignmentPosts(boardId) {
	var jsonURL = "faculty_academic_board?type=assignmentpost";
	 $.getJSON(jsonURL, function(opts) {
		 if (opts) {	 
				
				var paragraph = $('<p/>', {id : 'assignmentPost'}).appendTo('#assignment_posts_list'); 
				i =0;
				$.each(opts, function(keyObject, assignmentPostObject) {
					i = i+1;					
					paragraph.append("<strong><h1>Title: </h1></strong>" + assignmentPostObject.title + "<br/><br/>");
					paragraph.append("<strong><h1>Description: </h1></strong>" + assignmentPostObject.description + "<br/><br/>");
					paragraph.append("<strong><h1>Link: </h1></strong> <a href='" + assignmentPostObject.materialLink + "'>Download</a><br/><br/>");
					paragraph.append("<strong><h1>Due Date: </h1></strong>" + assignmentPostObject.dueDate + "<br/><br/>");
//paragraph.append("<strong><h1>Remaining Hours: </h1></strong><span id='clock" + i +"'></span><br/><br/>");
paragraph.append("<strong><h1>Remaining Hours: </h1></strong><br/><div id='clock" + i +"' style='margin:0em;'></div>");
var clock;
var t = assignmentPostObject.dueDate.split(/[- :]/);
var currentDate = new Date();
/* alert("0"+t[0]);
alert("1"+t[1]);
alert("2"+t[2]);
alert("3"+t[3]);
alert("4"+t[4]); */
//alert(t[5]);  
// Set some date in the future. In this case, it's always Jan 1
var futureDate  = new Date(t[0],t[1]-1,t[2],t[3], t[4], 00, 00);
//alert(futureDate);
// Calculate the difference in seconds between the future and current date
if(futureDate <= currentDate){
	diff = 0;
	//alert('#clock'+i);
	$( '#clock'+i ).hide();
	
	
}else{
	//alert(futureDate);
 diff = futureDate.getTime() / 1000 - currentDate.getTime() / 1000;
}
// Instantiate a coutdown FlipClock
clock = $('#clock'+i).FlipClock(diff, {
	clockFace: 'DailyCounter',
	countdown: true,
	showSeconds: true
});
					paragraph.append("<hr/>"); 
					//alert(assignmentPostObject.dueDate);
					//2015-04-28 01:22:00.0
				}); 
		} 
	}); 
}

</script>
</head>
<body>
<h1 class="titles">Academic Board Page</h1>
Welcome,  <c:out value="${sessionScope.userName}" />
This is the course page for <c:out value="${sessionScope.currentCourseId}" />
The id for this academic board is <c:out value="${sessionScope.currentAcademicBoardId}" />
<br/>
<p>
	<h2 id ="academic_board_title">
		The available academic materials are: <br />
	</h2>
</p>

<div id ="academic_board_list"></div>

<p>
	<h2 id ="assignment_posts_title">
		The available assignment posts are: <br />
	</h2>
</p>

<div id ="assignment_posts_list"></div>


<%@ include file="student_academic_board_footer.jsp"%>
</body>
</html>