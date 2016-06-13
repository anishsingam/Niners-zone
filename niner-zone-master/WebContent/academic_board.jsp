
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="acadMaterials" scope="session" class="model.AcademicMaterialsDAO">
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Academic Board</title>
<%@ include file="faculty_header.jsp"%>
<script type="text/javascript">

$(document).ready(function() {
	
	$('#delete_academic_board_title').hide();
	$('#delete_academic_board_list').hide();
	
	$('#delete_assignment_board_title').hide();
	$('#delete_assignment_board_list').hide();
	
	$('#delete_assignment_post_title').hide();
	$('#delete_assignment_post_list').hide();
	
	fillAcademicBoard('${sessionScope.userName}','${sessionScope.currentAcademicBoardId}');
	fillAssignmentPosts('${sessionScope.currentAcademicBoardId}');
	
	$('#view_participants').click(function() {						
		window.location.href = "view_participants.jsp";
	});
	
	$('#delete_Academic_Materials').click(function() {						
		fillAvailableAcadMaterials('${sessionScope.currentAcademicBoardId}');
	});
	
	$('#delete_Assignment_Materials').click(function() {						
		fillAvailableAssignmentMaterials('${sessionScope.currentAcademicBoardId}');
	});
	
	$('#delete_Assignment_Post').click(function() {						
		fillAvailableAssignmentPosts('${sessionScope.currentAcademicBoardId}');
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

function fillAvailableAcadMaterials(boardId) {
	$('#academic_board_title').hide();
	$('#academic_board_list').hide();
	
	$('#assignment_posts_title').hide();
	$('#assignment_posts_list').hide();
	
	$('#delete_assignment_post_title').hide();
	$('#delete_assignment_post_list').hide();
	
	$('#delete_assignment_board_title').hide();
	$('#delete_assignment_board_list').hide();
	
	$('#delete_academic_board_title').show();
	$('#delete_academic_board_list').show();
	$('#delete_academic_board_list').empty();
	
	var jsonURL = "faculty_academic_board?type=academic";
	 $.getJSON(jsonURL, function(opts) {
		if (opts) { 
			var i =0;
			var form = $('<form/>', {action : 'faculty_academic_board', method : 'POST', id: 'deleteForm'}).appendTo('#delete_academic_board_list');
			var table = $('<table/>', {align : 'right', id:'acadtable'}).appendTo('#deleteForm');
			var i =0;
			$.each(opts, function(keyObject, acadMaterialObject) {
				i = i+1;
				var tablerow = $('<tr/>', {id : 'tablerow'}).appendTo('#acadtable');
				
				tablerow.append("<td><input type='checkbox' name='checked" + i + "' /></td>");
				tablerow.append("<td><input type='text' name='title' value='" + acadMaterialObject.title + "' disabled='disabled'/></td>");
				tablerow.append("<td><input type='hidden' name='materialid"+i+"' value='" + acadMaterialObject.material_id + "'/></td>");
			});
			form.append("<input type='hidden' name='totalNumberOfMaterials' value='" + i + "' />");
			form.append("<input type='hidden' name='typeOfDeletion' value='academic' />");
			form.append("<br/><input type='submit' id='savebutton' value='Save' />");
		} 
	}); 
}


function fillAvailableAssignmentMaterials(boardId) {
	$('#academic_board_title').hide();
	$('#academic_board_list').hide();
	
	$('#assignment_posts_title').hide();
	$('#assignment_posts_list').hide();
	
	$('#delete_academic_board_title').hide();
	$('#delete_academic_board_list').hide();
	
	$('#delete_assignment_post_title').hide();
	$('#delete_assignment_post_list').hide();
	
	$('#delete_assignment_board_title').show();
	$('#delete_assignment_board_list').show();
	 $('#delete_assignment_board_list').empty(); 
	
	var jsonURL = "faculty_academic_board?type=assignment";
	 $.getJSON(jsonURL, function(opts) {
		if (opts) { 
			var i =0;
			var form = $('<form/>', {action : 'faculty_academic_board', method : 'POST', id: 'deleteAssignmentMaterialsForm'}).appendTo('#delete_assignment_board_list');
			var table = $('<table/>', {align : 'right', id:'assignmenttable'}).appendTo('#deleteAssignmentMaterialsForm');
			var i =0;
			$.each(opts, function(keyObject, assignmentMaterialObject) {
				i = i+1;
				var tablerow = $('<tr/>', {id : 'tablerow'}).appendTo('#assignmenttable');
				
				tablerow.append("<td><input type='checkbox' name='checked" + i + "' /></td>");
				tablerow.append("<td><input type='text' name='title' value='" + assignmentMaterialObject.title + "' disabled='disabled'/></td>");
				tablerow.append("<td><input type='hidden' name='materialid"+i+"' value='" + assignmentMaterialObject.material_id + "'/></td>");
			});
			form.append("<input type='hidden' name='totalNumberOfMaterials' value='" + i + "' />");
			form.append("<input type='hidden' name='typeOfDeletion' value='assignment' />");
			form.append("<br/><input type='submit' id='savebutton' value='Save' />");
		} 
	}); 
}


function fillAvailableAssignmentPosts(boardId) {
	$('#academic_board_title').hide();
	$('#academic_board_list').hide();
	
	$('#assignment_posts_title').hide();
	$('#assignment_posts_list').hide();
	
	$('#delete_academic_board_title').hide();
	$('#delete_academic_board_list').hide();
	
	$('#delete_assignment_board_title').hide();
	$('#delete_assignment_board_list').hide();
	
	$('#delete_assignment_post_title').show();
	$('#delete_assignment_post_list').show();
	 $('#delete_assignment_post_list').empty(); 
	
	var jsonURL = "faculty_academic_board?type=assignmentpost";
	 $.getJSON(jsonURL, function(opts) {
		if (opts) { 
			var i =0;
			var form = $('<form/>', {action : 'faculty_academic_board', method : 'POST', id: 'deleteAssignmentPostsForm'}).appendTo('#delete_assignment_post_list');
			var table = $('<table/>', {align : 'right', id:'assignmentposttable'}).appendTo('#deleteAssignmentPostsForm');
			var i =0;
			$.each(opts, function(keyObject, assignmentPostObject) {
				i = i+1;
				var tablerow = $('<tr/>', {id : 'tablerow'}).appendTo('#assignmentposttable');
				
				tablerow.append("<td><input type='checkbox' name='checked" + i + "' /></td>");
				tablerow.append("<td><input type='text' name='title' value='" + assignmentPostObject.title + "' disabled='disabled'/></td>");
				tablerow.append("<td><input type='hidden' name='postid"+i+"' value='" + assignmentPostObject.postId + "'/></td>");
			});
			form.append("<input type='hidden' name='totalNumberOfPosts' value='" + i + "' />");
			form.append("<input type='hidden' name='typeOfDeletion' value='assignmentpost' />");
			form.append("<br/><input type='submit' id='savebutton' value='Save' />");
		} 
	}); 
}


</script>


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

<div id ="delete_academic_board_title">
	<h2 id ="academic_board_title">
			The academic materials available for deletion are: <br />
	</h2>
</div>
<div id ="delete_academic_board_list"></div>

<div id ="delete_assignment_board_title">
	<h2 id ="academic_board_title">
			The assignment materials available for deletion are: <br />
	</h2>
</div>
<div id ="delete_assignment_board_list"></div>

<div id ="delete_assignment_post_title">
	<h2 id ="academic_board_title">
			The assignment posts available for deletion are: <br />
	</h2>
</div>
<div id ="delete_assignment_post_list"></div>


<%-- <table align="right">
			<c:set var="academicMaterialsList" value="${acadMaterials.academicMaterials}">
			</c:set>
			<c:forEach items="${acadMaterials.academicMaterials}" var="acad">
				
				<tr>
					<td><c:out value="${acad.title}" /></td>
					<td><c:out value="${acad.description}" /></td>
					<td><c:out value="${acad.link}" /></td>
				</tr>
				
			</c:forEach>

		</table> --%>

<%@ include file="academic_board_footer.jsp"%>
</body>
</html>