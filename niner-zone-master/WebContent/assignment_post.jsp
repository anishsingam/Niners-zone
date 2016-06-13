<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create a post for Assignment</title>
<%@ include file="faculty_header.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	//AJAX requests for college and department
	if('${sessionScope.userType}'=='faculty'){
		 $("#home").attr("href", "academic_board.jsp");
	 }
	 else{
		 $("#home").attr("href", "student_academic_board.jsp");
	 } 
	fillAssignmentMaterials('${sessionScope.currentAcademicBoardId}');
	
	js_hh_mm();
	js_yyyy_mm_dd_hh_mm_ss();
	

	$('#view_courses').change(function(){
		goToAcademicBoard('view_courses');
	});		
	$("#rad input[type='radio']").click(function(){alert("hi");});
	
	jQuery('#datetimepicker3').datetimepicker({
		  format:'Y/m/d H:i',
		  inline:true,
		  lang:'en',
		  minDate:new Date(),
		  minTime:0,
		    onSelectDate:function(ct,$i){
			  selectedDate = ct.dateFormat('Y/m/d');
			 /*  if(selectedDate == dateToday ){
				  alert("Yes");
				  minTime:0
			  } */
			  if( selectedDate == dateToday){
				    this.setOptions({
				      minTime:0
				    });
			  }
			  else{
				  
				  this.setOptions({
				      minTime:'00:00'
				    });
				  
			  }
		  }
		/*   onChangeDateTime:function(dp,$input){
			    alert($input.val())
			  }*/
		}); 
 	 s =document.getElementById('datetimepicker3').value = dateToday+" "+timeNow;
	/* alert(dateToday);
	alert(timeNow);  */
});

function js_yyyy_mm_dd_hh_mm_ss () {
	  now = new Date();
	  year = "" + now.getFullYear();
	  month = "" + (now.getMonth() + 1); if (month.length == 1) { month = "0" + month; }
	  day = "" + now.getDate(); if (day.length == 1) { day = "0" + day; }
	  hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
	  minute = "" + now.getMinutes(); if (minute.length == 1) { minute = "0" + minute; }
	  //second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
	  dateToday = year + "/" + month + "/" + day ;
	  return "'"+year + "/" + month + "/" + day ;
	  
	}

function js_hh_mm(){
	now = new Date();
	hour = "" + now.getHours(); if (hour.length == 1) { hour = "0" + hour; }
	  minute = now.getMinutes() + 15;
	  if (minute > 60){
		  var time = minute - 60;
		  minute = 0 + time
	  }else minute;
	  minute = "" + minute; if (minute.length == 1) { minute = "0" + minute; }
	  
	  //second = "" + now.getSeconds(); if (second.length == 1) { second = "0" + second; }
	  timeNow = hour + ":" + minute ;
	  return "'"+hour + ":" + minute+"'" ;//+ ":" + second;
	  
}


function fillAssignmentMaterials(boardId) {
	var assignForm = $('#assignmentPost');
	
	var jsonURL = 'AssignmentPostServlet?boardId=' + boardId;
	 $.getJSON(jsonURL, function(opts) {
		if (opts) { 
			var i =0;
			$("#rad").append("<p><label for = 'assignment_materials'>Select Assignment Materials</label></p>");
			$.each(opts, function(keyObject, assignmentMaterialObject) {
				i = i+1;
				
				
				$("#rad").append("<input type='radio' name='checked' value='" + assignmentMaterialObject.material_id + "' required>"+ assignmentMaterialObject.file_key +"<br/>");				
				assignForm.append("<input type='hidden' name='materialid"+i+"' value='" + assignmentMaterialObject.material_id + "'>");
			});
			assignForm.append("<input type='hidden' name='totalNumberOfMaterials' value='" + i + "' />");
			//assignForm.append("<input type='submit' value='submit' />");
			
		} 
	}); 
}


</script>

</head>
<body>
<!--  Check for the session so as to close it incase of inactive session -->
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
<!-- Real Work starts here -->
	<form method="post" id="assignmentPost" action="AssignmentPostServlet">
		
		<p>
			<label for="titleAssignmemtPost">Title</label> <input type="text" id="titleAssignmemtPost"
				name="titleAssignmemtPost" />
		</p>
		<p>
			<label for="descriptionAssignementPost">Description</label> 
			<textarea name="descriptionAssignementPost" rows="10" cols="50"></textarea>
		</p>
		
		<p>
			<label for="date_of_submission"> Date </label> 
			<!--  <input type="text" id="datetimepicker3" name="dateOfSubmission" class="date" required />-->
			<input id="datetimepicker3" name = "datetimepicker3" type="text"  />
		</p>
		<p>
		<div id = "rad"></div>
		</p>
		<input type="submit" value="Update"/>
		
		
	</form>
	<%@ include file="view_participants_footer.jsp"%>
		<script type="text/javascript">
	$(document).ready(function() {
		
		
		
		
		/* $("#datepicker1").datepicker({
			changeMonth:true,
			changeYear:true,
			minDate: 2,
			 onSelect: function(theDate) {
		            $("#dataEnd").datepicker('option', 'minDate', new Date(theDate));
		     }, */
			
		
		$('#assignmentPost').validate(
				{
			rules : {
				titleAssignmemtPost : "required",
				descriptionAssignementPost : "required",
			},
			messages : {
				titleAssignmemtPost : "Enter a title for the Material",
				descriptionAssignementPost : "Enter a description",
			}
		});
	});
		
 

	</script>
</body>
</html>