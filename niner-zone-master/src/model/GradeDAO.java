package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;

public class GradeDAO {

	private List<GradeDTO> gradesOfAllStudents;
	
	private List<GradeDTO> gradesOfStudent;
	
	public String test;
	
    public GradeDAO(String test) {
		super();
		this.test = test;
	}

	public GradeDAO()
    {
		this.test = null;
    }
	
	
	public List<GradeDTO> getGradesOfStudent(String userName, String boardId) {
		gradesOfStudent = new ArrayList<GradeDTO>();
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
			//Getting the results of the Academic View
			sql = "Select g.student_roll_number,g.assignment_post_id,p.title,g.grade from student_class_assignment_grade g inner join assignment_post p where g.assignment_post_id = p.post_id and g.student_roll_number in (Select roll_number from student where username='" + userName + "') and g.class_id in (SELECT class_id from academic_board where id = '" + boardId + "');";
			//All the details from the database
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				
				int studentRollNumber = rs.getInt("student_roll_number");
				int assignmentPostId = rs.getInt("assignment_post_id");
				String title = rs.getString("title");
				String grade = rs.getString("grade");
				GradeDTO gradeDTO = new GradeDTO(studentRollNumber, assignmentPostId, title, grade);
				gradesOfStudent.add(gradeDTO);
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
	return gradesOfStudent;
	}



	public void setGradesOfStudent(List<GradeDTO> gradesOfStudent) {
		this.gradesOfStudent = gradesOfStudent;
	}



	public List<GradeDTO> getGradesOfAllStudents(String boardId) {
		gradesOfAllStudents = new ArrayList<GradeDTO>();
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
			//Getting the results of the Academic View
			sql = "Select g.student_roll_number,g.assignment_post_id,p.title,g.grade from student_class_assignment_grade g inner join assignment_post p where g.student_roll_number in (SELECT sc.stud_roll_number from student_course sc where class_id in (SELECT class_id from academic_board where id = '" + boardId + "')) and g.class_id in (SELECT class_id from academic_board where id = '" + boardId + "') and g.assignment_post_id = p.post_id;";
			//All the details from the database
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				
				int studentRollNumber = rs.getInt("student_roll_number");
				int assignmentPostId = rs.getInt("assignment_post_id");
				String title = rs.getString("title");
				String grade = rs.getString("grade");
				GradeDTO gradeDTO = new GradeDTO(studentRollNumber, assignmentPostId, title, grade);
				gradesOfAllStudents.add(gradeDTO);
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
	return gradesOfAllStudents;
	}



	public void setGradesOfAllStudents(List<GradeDTO> gradesOfAllStudents) {
		this.gradesOfAllStudents = gradesOfAllStudents;
	}

}