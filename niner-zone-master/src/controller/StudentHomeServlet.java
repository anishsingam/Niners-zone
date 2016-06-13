package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import model.CourseDAO;
import model.CourseDTO;

import com.google.gson.Gson;

import db.DbConnection;

/**
 * Servlet implementation class StudentHomeServlet
 */
@WebServlet("/student_home")
public class StudentHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute("userName");
		CourseDAO courseDAO = new CourseDAO();
		
		if(request.getParameter("val") != null && request.getParameter("val").equals("on")){
	    	//Method to get the courses for Faculty
		    List<CourseDTO> courseListOfStudent = courseDAO.getCourseListforStudents(userName);
		    String json2 = new Gson().toJson(courseListOfStudent);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json2);
	    }
		
		if(request.getParameter("courseId") != null){
	    	//Method to get the courses for Faculty
		    String courseId = request.getParameter("courseId");
		    int academicBoardId = 0;
			academicBoardId = courseDAO.getAcademicBoardId(userName, courseId);
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
	}
	
	}
