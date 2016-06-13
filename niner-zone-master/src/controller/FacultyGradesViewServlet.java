package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AcademicMaterialsDAO;
import model.AcademicMaterialsDTO;
import model.GradeDAO;
import model.GradeDTO;
import model.UserBean;

import com.google.gson.Gson;

/**
 * Servlet implementation class FacultyGradesViewServlet
 */
@WebServlet("/FacultyGradesViewServlet")
public class FacultyGradesViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultyGradesViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currentAcademicBoardId = (Integer) request.getSession().getAttribute("currentAcademicBoardId");
		String userName = (String) request.getSession().getAttribute("userName");
		
		if(request.getParameterMap().containsKey("type") && request.getParameter("type").equals("faculty")){					
			GradeDAO gradeDAO = new GradeDAO();
			List<GradeDTO> gradesOfAllStudents = gradeDAO.getGradesOfAllStudents(currentAcademicBoardId+"");
		    String json2 = new Gson().toJson(gradesOfAllStudents);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json2);	
		}
		
		if(request.getParameterMap().containsKey("type") && request.getParameter("type").equals("student")){					
			GradeDAO gradeDAO = new GradeDAO();
			List<GradeDTO> gradesOfAllStudents = gradeDAO.getGradesOfStudent(userName, currentAcademicBoardId+"");
		    String json2 = new Gson().toJson(gradesOfAllStudents);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json2);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
