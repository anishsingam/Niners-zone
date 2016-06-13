<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="college" scope="session" class="model.CollegeDAO"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration</title>
<%@ include file="register_login_template_header.jsp"%>
<script src="js/registration_validation.js"></script>	
</head>


<body>

	<h1 class="titles">User Registration Page</h1>

	<form id="myform" method="post" action="register">

		<p>
			<label for="type_of_user">User Type</label> <select id="type_of_user"
				name="typeOfUser">
				<option value="faculty">Faculty</option>
				<option value="student" selected>Student</option>
				<option value="admin">Admin</option>
			</select>
		</p>
		<p>
			<label for="user_name">UserName</label> <input type="text"
				id="user_name" name="userName" />
				<label id ="vaildateUser" class="error"></label>
		</p>
		<p>
			<label for="first_name">FirstName</label> <input type="text"
				id="first_name" name="firstName" size="5" />
		</p>
		<p>
			<label for="last_name">LastName</label> <input type="text"
				id="last_name" name="lastName" />
		</p>
		<p>
			<label for="password">Password</label> <input type="password"
				id="password" name="userPassword" />
		</p>
		<p>
			<label>ConfirmPassword</label> <input type="password"
				id="confirm_password" name="confirmPassword" />
		</p>
		<p>
			<label for="select_gender">Gender</label> <select id="select_gender"
				name="gender" required>
				<option value="male">Male</option>
				<option value="female">Female</option>
			</select>
		</p>
		<p>
			<label for="date_of_birth"> Date </label> <input type="date"
				id="datepicker1" name="dateOfBirth" class="date" required/>
		</p>
		<p>
			<label>College</label> 
			<select id="college_name" name="collegeName" required>
				<option value="">Please select a College</option>
				<c:forEach items="${college.collegeList}" var="colg">
            		<option value="${colg.collegeId}">
            			<c:out value="${colg.collegeName}"/>
            		</option>
      			</c:forEach>	
			</select>
		</p> 
		<p>
		<label>Department</label> 
		<select id="select_department" name="department" required> 		
		</select>
		</p>
		
		<p>
			<label>Email</label> <input type="email" id="email_address"
				name="emailAddress" required />
		</p>
		<p>
			<input type="submit" onclick="return confirm('Are you sure?')" />
		</p>
	</form>
	<%@ include file="register_login_template_footer.jsp"%>
	

</body>
</html>