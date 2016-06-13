<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Post</title>
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

<form id = forumPostEdit method ="post" action = "discussion_forum">
<p>
			<label for="title">Title</label> 
			<input type="text" id="title" name="postAssignmentTitle" value = '<%= request.getAttribute("title") %>' />
		</p>
	
		<p>
			<label for="descriptionForumPost">Description</label>
			<textarea  name="descriptionForumPost" rows="15" cols="10" ><%=request.getAttribute("contentForum") %></textarea>
		</p>
<input type="hidden" value = "username" name ="UserType"/>		
<input type="hidden" name ="postId" value=<%= request.getAttribute("postId") %> />
<input type = "hidden" name="actionPerformed" value="edit"/>
<input type ="submit" value="Update">
</form>
<%@ include file="discussion_forum_footer.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#forumPostEdit').validate({
				rules : {
					postAssignmentTitle : "required",
					descriptionForumPost : "required"
				},
				messages : {
					postAssignmentTitle : "Enter a title for the Post",
					descriptionForumPost : "Enter a meaningful description"
				}
			});
		});
	</script>
<%@ include file="discussion_forum_footer.jsp"%>
</body>
</html>