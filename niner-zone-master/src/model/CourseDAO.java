package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbConn_Test;
import db.DbConnection;

public class CourseDAO {

	private List<CourseDTO> courseList ;

	private List<CourseDTO> approvedList;

	private List<CourseDTO> courseAdditionRequestList ;

	private List<CourseDTO> courseListforStudents ;
 
    public String test;
	
    public CourseDAO(String test) {
		super();
		this.test = test;
	}

	public CourseDAO()
    {
		this.test = null;
    }

    public List<CourseDTO> getCourseAdditionRequestList() {
		courseAdditionRequestList = new ArrayList<CourseDTO>() ;
        
    	Statement stmt = null;
		DbConnection conn = null;
		try{
			if(this.test == null){
				conn = new DbConnection();
			}
			else{
				conn = new DbConnection(test);
			}
			String sql;
			sql = "Select fc.course_id,fc.faculty_id,fc.faculty_username,c.course_id,c.course_name,c.course_level from faculty_course fc inner join course c on fc.course_id = c.course_id where fc.course_appr_flag= '0'";														
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int facultyId = rs.getInt("faculty_id");
				String facultyUserName = rs.getString("faculty_username");
				String courseName = rs.getString("course_name");
				String courseId = rs.getString("course_id");
				String courseLevel = rs.getString("course_level");
				CourseDTO courseDTO = new CourseDTO(courseId, courseName, courseLevel, facultyId, facultyUserName);
				courseAdditionRequestList.add(courseDTO);
			}
			//STEP 8: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			if(conn!=null)
				conn.close();
		}//end try
        
        return courseAdditionRequestList; 
	}




	public void setCourseAdditionRequestList(
			List<CourseDTO> courseAdditionRequestList) {
		this.courseAdditionRequestList = courseAdditionRequestList;
	}




	public void setCourseList(List<CourseDTO> courseList) {
		this.courseList = courseList;
	}

	public List<CourseDTO> getCourseList(String userName)
    {
		courseList = new ArrayList<CourseDTO>() ;
        
    	Statement stmt = null;
		DbConnection conn = null;
		try{
			if(this.test == null){
				conn = new DbConnection();
			}
			else{
				conn = new DbConnection(test);
			}
			String sql;
			sql = "(SELECT * from course where department_id in (Select department_id FROM faculty where username='" + userName + "') AND course_id NOT IN (Select course_id from faculty_course where (course_appr_flag = '0' or course_appr_flag = '1') AND faculty_username = '" + userName + "'));";														
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int departmentId = rs.getInt("department_id");
				String courseName = rs.getString("course_name");
				String courseId = rs.getString("course_id");
				String courseLevel = rs.getString("course_level");
				CourseDTO courseDTO = new CourseDTO(courseId, courseName, courseLevel, departmentId);
				courseList.add(courseDTO);
			}
			
			//STEP 8: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			if(conn!=null)
				conn.close();
		}//end try
        
        return courseList;   
    }
	
	public List<CourseDTO> getapprovedList(String userName){
		approvedList = new ArrayList<CourseDTO>() ;
        
    	Statement stmt = null;
		DbConnection conn = null;
		try{
			if(this.test == null){
				conn = new DbConnection();
			}
			else{
				conn = new DbConnection(test);
			}
			String sql;
			sql = "(SELECT course_name,course_id FROM course where course_id in (Select course_id FROM faculty_course WHERE faculty_username='" + userName + "' AND  course_appr_flag='1'))";
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String courseName = rs.getString("course_name");
				String courseId= rs.getString("course_id");
				CourseDTO courseDTO = new CourseDTO(courseName,courseId);
				approvedList.add(courseDTO);
			}
			
			//STEP 8: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			if(conn!=null)
				conn.close();
		}//end try
        return approvedList;   
    }
	


public List<CourseDTO> getCourseListforStudents(String userName){
	courseListforStudents = new ArrayList<CourseDTO>() ;
    
	Statement stmt = null;
	DbConnection conn = null;
	try{
		if(this.test == null){
			conn = new DbConnection();
		}
		else{
			conn = new DbConnection(test);
		}
		String sql;
		sql = "(select course_name,course_id from course where course_id in (select course_id from faculty_course where id in (select class_id from student_course where stud_roll_number in (select roll_number from student where username = '" + userName + "'))))";													
		stmt = conn.DbConnectionForPreparedStatement(sql);			
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			
			String courseName = rs.getString("course_name");
			String course_id = rs.getString("course_id");
			CourseDTO courseDTO = new CourseDTO(courseName,course_id);
			courseListforStudents.add(courseDTO);
		}
		
		//STEP 8: Clean-up environment
		rs.close();
		stmt.close();
		conn.close();
	}catch(SQLException se){
		//Handle errors for JDBC
		se.printStackTrace();
	}catch(Exception e){
		//Handle errors for Class.forName
		e.printStackTrace();
	}finally{
		//finally block used to close resources
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}// nothing we can do
		if(conn!=null)
			conn.close();
	}//end try
    return courseListforStudents;   
}


public void setCourseListforStudents(List<CourseDTO> courseListforStudents) {
		this.courseListforStudents = courseListforStudents;
	}

public int getAcademicBoardId(String userName,String courseId){
	int academicBoardId = 0;
	Statement stmt = null;
	DbConnection conn = null;
	try{
		if(this.test == null){
			conn = new DbConnection();
		}
		else{
			conn = new DbConnection(test);
		}
		String sql;
		
		sql = "SELECT id from academic_board where class_id in (SELECT id from faculty_course where course_id = '" + courseId + "' and class_id in (Select class_id from student_course where stud_roll_number in (select roll_number from student where username = '" + userName + "')));";
		stmt = conn.DbConnectionForPreparedStatement(sql);			
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			academicBoardId = rs.getInt("id");
		}
		
		
		//STEP 8: Clean-up environment
		rs.close();
		stmt.close();
		conn.close();
	}catch(SQLException se){
		//Handle errors for JDBC
		se.printStackTrace();
	}catch(Exception e){
		//Handle errors for Class.forName
		e.printStackTrace();
	}finally{
		//finally block used to close resources
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}// nothing we can do
		if(conn!=null)
			conn.close();
	}//end try				
return academicBoardId;
}

public int getAcademicBoardIdForFaculty(String userName,String courseId){
	int academicBoardId = 0;
	Statement stmt = null;
	DbConnection conn = null;
	try{
		if(this.test == null){
			conn = new DbConnection();
		}
		else{
			conn = new DbConnection(test);
		}
		String sql;
		
		sql = "SELECT id from academic_board where class_id in (SELECT id from faculty_course where faculty_username = '" + userName + "' and course_id = '" + courseId + "');";
		stmt = conn.DbConnectionForPreparedStatement(sql);			
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			academicBoardId = rs.getInt("id");
		}
		
		
		//STEP 8: Clean-up environment
		rs.close();
		stmt.close();
		conn.close();
	}catch(SQLException se){
		//Handle errors for JDBC
		se.printStackTrace();
	}catch(Exception e){
		//Handle errors for Class.forName
		e.printStackTrace();
	}finally{
		//finally block used to close resources
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}// nothing we can do
		if(conn!=null)
			conn.close();
	}//end try				
return academicBoardId;
}

public void updateFacultyCourseApprovals(String userName,String courseId){
	Statement stmt = null;
	DbConnection conn = null;
	int facultyId = 0;
	try{
		if(this.test == null){
			conn = new DbConnection();
		}
		else{
			conn = new DbConnection(test);
		}
		String sql;
		
		sql = "select faculty_id from faculty where username='" + userName + "';";
		stmt = conn.DbConnectionForPreparedStatement(sql);			
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			facultyId = rs.getInt("faculty_id");
		}
		rs.close();
		stmt.close();
		
		
		sql = "insert into faculty_course(course_id,faculty_id,course_appr_flag,faculty_username) values('" + courseId + "','" + facultyId + "','0','" + userName + "')";			
		stmt = conn.DbConnectionForStatement();
		stmt.execute(sql);
		
		//STEP 8: Clean-up environment
		rs.close();
		stmt.close();
		conn.close();
	}catch(SQLException se){
		//Handle errors for JDBC
		se.printStackTrace();
	}catch(Exception e){
		//Handle errors for Class.forName
		e.printStackTrace();
	}finally{
		//finally block used to close resources
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}// nothing we can do
		if(conn!=null)
			conn.close();
	}//end try				
}

}