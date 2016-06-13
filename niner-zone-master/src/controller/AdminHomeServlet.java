package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbConnection;

/**
 * Servlet implementation class AdminHome
 */
@WebServlet("/admin_home")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameterMap().containsKey("numberOfUserRows")){
			String d = request.getParameter("numberOfUserRows");
			int length = Integer.parseInt(d);	
			for(int i = 1; i<=length ;i++){
				String myCheckBoxValue = request.getParameter("checked"+i);
				if(myCheckBoxValue != null && myCheckBoxValue.equals("on")){
					String userName = request.getParameter("username"+i);
					
					updateUserApprovals(userName);
				}
			}
			response.sendRedirect("admin_home.jsp");
		}
		if(request.getParameterMap().containsKey("numberOfCourseRequests")){
			String d = request.getParameter("numberOfCourseRequests");
			int length = Integer.parseInt(d);	
			for(int i = 1; i<=length ;i++){
				String myCheckBoxValue = request.getParameter("checked"+i);
				if(myCheckBoxValue != null && myCheckBoxValue.equals("on")){
					String facultyUserName = request.getParameter("faculty_user_name"+i);
					
					String courseId = request.getParameter("course_id"+i);
					
					updateCourseApprovals(facultyUserName, courseId);
				}
			}
			
			response.sendRedirect("admin_home.jsp");
		}
		
	}
	
	private void updateUserApprovals(String userName){
		Statement stmt = null;
		DbConnection conn = null;
		try{
			conn = new DbConnection();
			String sql;
			sql = "UPDATE login_credential SET approval_flag='1' WHERE username='" + userName + "';";			
			stmt = conn.DbConnectionForStatement();
			stmt.execute(sql);

			//STEP 8: Clean-up environment
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
	
	
	
	private void updateCourseApprovals(String facultyUserName,String courseId){
		Statement stmt = null;
		DbConnection conn = null;
		try{
			conn = new DbConnection();
			String sql;
			sql = "UPDATE faculty_course SET course_appr_flag='1' WHERE faculty_username='" + facultyUserName + "' and course_id='" + courseId + "';";			
			stmt = conn.DbConnectionForStatement();
			stmt.execute(sql);
			stmt.close();
			
			sql = "Insert into academic_board(class_id) SELECT id from faculty_course where faculty_username = '" + facultyUserName + "' and course_id='" + courseId + "';";			
			stmt = conn.DbConnectionForStatement();
			stmt.execute(sql);
			
			
			//STEP 8: Clean-up environment
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
