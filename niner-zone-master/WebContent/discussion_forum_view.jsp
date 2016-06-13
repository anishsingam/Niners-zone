<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Post</title>
<%@ include file="faculty_header.jsp"%>
<script type="text/javascript">

$(document).ready(function() {
	//alert('${requestScope.postId}');
	fillPost('${requestScope.postId}');
	fillComments('${requestScope.postId}');
	
	
	
	if('${sessionScope.userType}'=='faculty'){
		$("#user_type option[value='anonymous_to_all']").remove();
		$("#user_type option[value='anonymous_to_all_but_faculty']").remove();
	}
	
});



function fillPost(postId) {
	var jsonURL = "discussion_forum?type=clickedPost&clickedPostId="+postId;
	//alert(jsonURL);
	$.getJSON(jsonURL, function(opts) {
		 if (opts) {	 			
			var paragraph = $('<p/>', {id : 'forumPostDisplay'}).appendTo('#postDisplay');
			paragraph.append("<strong><h1>Title: </h1></strong>" + opts.title + "<br/><br/>");
			paragraph.append("<strong><h1>Content: </h1></strong>" + opts.content + "<br/><br/>");
			
			var postedBy = "";
			
			if(opts.display_type == 'username'){
				postedBy = opts.owner_userName;
			}
			else if(opts.display_type == 'anonymous_to_all'){
				postedBy = "Anonymous";
			}
			else if(opts.display_type == 'anonymous_to_all_but_faculty'){
				if('${sessionScope.userType}' == 'faculty'){
					postedBy = opts.owner_userName;
				}
				else{
					postedBy = "Anonymous";
				}
			}
			paragraph.append("<strong><h1>Posted By: </h1></strong>" + postedBy +"<br/>");
			paragraph.append("<strong><h1>On: </h1></strong>" + opts.postDate + "<br/><br/>");
			paragraph.append("<hr/>"); 	
		} 
	}); 
}


function fillComments(postId) {
	var jsonURL = "discussion_forum?type=comments&clickedPostId="+postId;
	 $.getJSON(jsonURL, function(opts) {
		 if (opts) {	 
				
				var paragraph = $('<p/>', {id : 'forumCommentDisplay'}).appendTo('#commentsDisplay'); 
				
				
				$.each(opts, function(keyObject, postCommentObject) {
										
					paragraph.append("<strong>Comment: </strong><br/>" + postCommentObject.commentMade + "<br/>");
					paragraph.append("<strong>Date Added: </strong>" + postCommentObject.dateOfComment + "<br/>");
					var postedBy = "";
					if(postCommentObject.display_type == 'username'){
						postedBy = postCommentObject.owner_userName;
					}
					else if(postCommentObject.display_type == 'anonymous_to_all'){
						postedBy = "Anonymous";
					}
					else if(postCommentObject.display_type == 'anonymous_to_all_but_faculty'){
						if('${sessionScope.userType}'=='faculty'){
							postedBy = postCommentObject.owner_userName;
						}
						else{
							postedBy = "Anonymous";
						}
					}
					paragraph.append("<strong>Posted By: </strong>" + postedBy +"<br/><br/><br/>"); 
					
					
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
	<hr/>
	<div id ="postDisplay"></div>
	<strong>Comments for this post are : </strong><br/><br/>
	<div id ="commentsDisplay"></div>	
	
	
	<!--  Need to Add Comments -->
	<br/><br/><br/><br/>
	<form id = addComment method ="post" action = "discussion_forum ">
	<p>
			<label for="userType">UserType</label> 
			<select id = "user_type" name = "UserType">
				<option value="username" selected>As User</option>
				<option value="anonymous_to_all">Anonymous To All</option>
				<option value="anonymous_to_all_but_faculty">Anonymous to All but Faculty</option>
			</select>
		</p>
<p>
			<label for="addCommentArea">Comment</label>
			<textarea id ="addCommentAreaText" name="addCommentArea" rows="4" cols="4" ></textarea>
		</p>
		
<input type="hidden" name ="postId" value=<%= request.getAttribute("postId") %> />
<input type = "hidden" name="actionPerformed" value="addComment"/>
<input type ="submit" value="Add">
</form>
<script>
$(document).ready(function() {
			$('#addComment').validate({
				rules : {
					addCommentArea : "required",
				},
				messages : {
					addCommentArea : "Add a valid Comment",
				}
			});
		});
$( "#addComment" ).$( "#book" ).load(function()  {
	  /* alert( "Handler for .submit() called." );
	  event.preventDefault(); */
	$('#addCommentAreaText').val("");
	});
</script>
	<%@ include file="discussion_forum_footer.jsp"%>
</body>
</html>