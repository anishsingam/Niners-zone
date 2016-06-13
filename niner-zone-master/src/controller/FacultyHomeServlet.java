package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AcademicMaterialsDAO;
import model.CourseDAO;
import model.CourseDTO;
import model.DepartmentDAO;
import model.DepartmentDTO;
import model.UserBean;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import db.DbConnection;

/**
 * Servlet implementation class FacultyHome
 */
@WebServlet("/faculty_home")
public class FacultyHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultyHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userName = (String) request.getSession().getAttribute("userName");
		
		CourseDAO courseDAO = new CourseDAO();
		
		
		if(request.getParameter("val") != null && request.getParameter("val").equals("on")){
	    	//Method to get the courses for Faculty
		    List<CourseDTO> approvedCourses = courseDAO.getapprovedList(userName);
		    String json2 = new Gson().toJson(approvedCourses);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json2);
	    }
		if(request.getParameterMap() == null || request.getParameterMap().size() == 0){
			//Method to get the courses
		    List<CourseDTO> courses = courseDAO.getCourseList(userName);
		    String json = new Gson().toJson(courses);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json); 
		}
		if(request.getParameter("courseId") != null){
	    	//Method to get the courses for Faculty
		    String courseId = request.getParameter("courseId");
		    
		    
		    int academicBoardId = getAcademicBoardId(userName,courseId);
		    
		    
		    request.getSession().setAttribute("currentCourseId", courseId);
		    request.getSession().setAttribute("currentAcademicBoardId", academicBoardId);
		    
		    
		    
		    JSONObject obj = new JSONObject();				
			try {
				obj.put("val", new Boolean(true));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				obj.write(response.getWriter());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		
		
			
	    
	    
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = (String) request.getSession().getAttribute("userName");
		String d = request.getParameter("totalNumberOfCourses");
		int length = Integer.parseInt(d);	
		for(int i = 1; i<=length ;i++){
			String myCheckBoxValue = request.getParameter("checked"+i);
			if(myCheckBoxValue != null && myCheckBoxValue.equals("on")){
				String courseId = request.getParameter("courseid"+i);
				
				updateFacultyCourseApprovals(userName,courseId);
			}
		}
		response.sendRedirect("faculty_home.jsp");
	}
	
	private void updateFacultyCourseApprovals(String userName,String courseId){
		Statement stmt = null;
		DbConnection conn = null;
		int facultyId = 0;
		try{
			conn = new DbConnection();
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
	
	
	private int getAcademicBoardId(String userName,String courseId){
		int academicBoardId = 0;
		Statement stmt = null;
		DbConnection conn = null;
		try{
			conn = new DbConnection();
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
	
	public int getAcademicBoardTest(String userName, String courseId)
	{
		int u = getAcademicBoardId(userName, courseId);
		return u;
	}
	
	
	

}
