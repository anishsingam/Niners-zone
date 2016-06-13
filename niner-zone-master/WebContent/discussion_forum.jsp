
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Discussion Forum</title>
<%@ include file="faculty_header.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	
	 if('${sessionScope.userType}'=='faculty'){
		 $("#home").attr("href", "academic_board.jsp");
	 }
	 else{
		 $("#home").attr("href", "student_academic_board.jsp");
	 } 
	
	 getPosts('${sessionScope.currentAcademicBoardId}');

});

function getPosts(boardId) {
	var jsonURL = "discussion_forum?type=post";
	
	$.getJSON(jsonURL, function(opts) {
		if (opts) {	
			var paragraph = $('<p/>', {id : 'posts'}).appendTo('#forum_posts_list');
			
			$.each(opts, function(keyObject, postObject) {
				var id = postObject.post_id;
				paragraph.append("<strong><h1>Title: </h1></strong>" + postObject.title + "<br/><br/>");
				paragraph.append("<strong><h1>Description: </h1></strong>" + postObject.content + "<br/><br/>");
				var postedBy = "";
				if(postObject.display_type == 'username'){
					postedBy = postObject.owner_userName;
				}
				else if(postObject.display_type == 'anonymous_to_all'){
					postedBy = "Anonymous";
				}
				else if(postObject.display_type == 'anonymous_to_all_but_faculty'){
					if('${sessionScope.userType}'=='faculty'){
						postedBy = postObject.owner_userName;
					}
					else{
						postedBy = "Anonymous";
					}
					
				}
				paragraph.append("<strong><h1>Posted By: </h1></strong>" + postedBy +"<br/>");
				
				if('${sessionScope.userName}' == postObject.owner_userName){
					var form = $('<form/>', {action : 'discussion_forum', method : 'POST', id: 'editPost'+id}).appendTo('#posts');
					form.append("<input type='hidden' name='actionPerformed' value='toEdit' />");
					form.append("<input type='hidden' name='postIdForEdit' value='" + id + "' />");
					form.append("<br/><input type='submit' id='editButton' value='Edit' style='float:right;' />");
				}
				
				
				var form = $('<form/>', {action : 'discussion_forum', method : 'POST', id: 'viewPost'+id}).appendTo('#posts');
				form.append("<input type='hidden' name='actionPerformed' value='viewPost' />");
				form.append("<input type='hidden' name='postIdForView' value='" + id + "' />");
				form.append("<input type='submit' id='viewButton' value='View' style='float:right;' />");
				
				if('${sessionScope.userType}'=='faculty' || '${sessionScope.userName}' == postObject.owner_userName){
					var form = $('<form/>', {action : 'discussion_forum', method : 'POST', id: 'deletePost'+id}).appendTo('#posts');
					form.append("<input type='hidden' name='actionPerformed' value='delete' />");
					form.append("<input type='hidden' name='postIdForDelete' value='" + id + "' />");
					form.append("<input type='submit' id='deleteButton' value='Delete' style='float:right;' />");
				}
				
				
				paragraph.append("<hr style='margin-top: 50px;'></hr>");
			});				
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

<p>
	<h2 id ="post_list_title">
		The discussion posts for this class are: <br />
	</h2>
</p>

<div id ="forum_posts_list"></div>
<%@ include file="discussion_forum_footer.jsp"%>
</body>
</html>