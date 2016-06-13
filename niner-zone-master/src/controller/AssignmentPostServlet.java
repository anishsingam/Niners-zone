package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db.DbConnection;
import model.AcademicMaterialsDAO;
import model.AcademicMaterialsDTO;
import model.UserBean;

/**
 * Servlet implementation class AssignmentPostServlet
 */
@WebServlet("/AssignmentPostServlet")
public class AssignmentPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignmentPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currentAcademicBoardId = (Integer) request.getSession().getAttribute("currentAcademicBoardId");
		if(request.getParameter("boardId") != null){		
			AcademicMaterialsDAO academicMaterialsDAO = new AcademicMaterialsDAO();
			List<AcademicMaterialsDTO> assignmentMaterials = academicMaterialsDAO.getAssignmentMaterials(currentAcademicBoardId+"");
		    String json2 = new Gson().toJson(assignmentMaterials);
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
		//get the parameters to insert into db
		String titleAsPost = request.getParameter("titleAssignmemtPost");
		String descAsPost = request.getParameter("descriptionAssignementPost");
		String duedateAsPo = request.getParameter("datetimepicker3");
		String duedateAsPost = duedateAsPo+":00";
		String materialIdAsPost = request.getParameter("materialid");
		String MaterialCount = request.getParameter("totalNumberOfMaterials");
		int sessionBoard = (Integer)request.getSession().getAttribute("currentAcademicBoardId");
		String acadBoard = sessionBoard+""; 
		String Assignment_Material = null;
		for(int i = 1; i<=Integer.parseInt(MaterialCount) ;i++){
			if(request.getParameter("checked") != null){
				Assignment_Material = request.getParameter("checked");
				break;
			}
			
		}
		System.out.println(duedateAsPost);
		Date dueDate = null;		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			dueDate = sdf.parse(duedateAsPost);
			System.out.println("Final dueDate" +dueDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dueDateToBeInserted = sdf.format(dueDate);
		System.out.println("Date Time to be inserted" + dueDateToBeInserted);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date date = new Date();
		 String currDateToBeInserted = dateFormat.format(date).toString();
		 
		 System.out.println("Final date here you go" +currDateToBeInserted);
		
		insertAssignmentPost(acadBoard,Assignment_Material,titleAsPost,descAsPost,currDateToBeInserted,dueDateToBeInserted);
		response.sendRedirect("academic_board.jsp");
	}

	private void insertAssignmentPost(String academic_board_id,String material_id,String title,String description,String creation_date,String due_date){
		//Insert the content to assignment post
		Statement stmt = null;
		DbConnection conn = null;
		try{
			conn = new DbConnection();
			String sql1 = "",sql="";
			String assignment_material_id = null;
			
			sql = "select id from assignment_material where material_id='" + material_id + "';";
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				assignment_material_id = rs.getInt("id")+"";
			}
			rs.close();
			stmt.close();
			
			
			sql1 = "INSERT into assignment_post(academic_board_id,assignment_material_id,title,description,creation_date,due_date) values('" + academic_board_id + "','" + assignment_material_id + "','"+title+"','"+description+"','"+creation_date+"','"+due_date+"');";
			stmt = conn.DbConnectionForStatement();
			stmt.execute(sql1);
			
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
