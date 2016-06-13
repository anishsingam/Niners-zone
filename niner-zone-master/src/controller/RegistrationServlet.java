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
import org.json.JSONObject;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db.DbConnection;
import model.DepartmentDAO;
import model.DepartmentDTO;
import model.UserBean;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameterMap().containsKey("username")){
			String username = request.getParameter("username");
		    
			Statement stmt = null;
			DbConnection conn = null;
			try{
				conn = new DbConnection();
				String sql;
				sql = "SELECT username from user where username = '" + username + "'";
				stmt = conn.DbConnectionForPreparedStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
				JSONObject obj = new JSONObject();				
				if(rs.next()){
					obj.put("val", new Boolean(true));
				}
				else{
					obj.put("val", new Boolean(false));
				}
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    obj.write(response.getWriter());
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
		if (request.getParameterMap().containsKey("val")){
			int collegeId = Integer.parseInt(request.getParameter("val")); // Value of parent DD to find associated child DD options for.
			DepartmentDAO departmentDAO = new DepartmentDAO();
		    List<DepartmentDTO> departments = departmentDAO.getDepartmentList(collegeId);
		    String json = new Gson().toJson(departments);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String password = request.getParameter("confirmPassword");
		String userType = request.getParameter("typeOfUser");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String emailId = request.getParameter("emailAddress");
		String dateOfBirthString = request.getParameter("dateOfBirth");
		int collegeId = Integer.parseInt(request.getParameter("collegeName"));
		int departmentId = Integer.parseInt(request.getParameter("department"));
		Date dateOfBirth = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			dateOfBirth = sdf.parse(dateOfBirthString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserBean user = new UserBean(userName, userType, firstName, lastName, emailId, gender, dateOfBirth);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateToBeInserted = sdf.format(user.getDateOfBirth());
		
		Statement stmt = null;
		DbConnection conn = null;
		try{
			conn = new DbConnection();
			String sql1,sql2,sql3 = "";
			sql1 = "INSERT into login_credential(username,user_password,approval_flag) values('" + userName + "','" + password + "','0');";
			sql2 = "INSERT into user(username,User_Type,First_Name,Last_Name,Date_Of_Birth,Email_Id,Gender) values('" + userName + "','" + userType + "','" + firstName + "','" + lastName + "','" + dateToBeInserted + "','" + emailId + "','" + gender +"');";			
			if(userType.equals("student")){
				sql3 = "INSERT into student(username,department_id) values('" + userName + "','" + departmentId + "');";
			}
			if(userType.equals("faculty")){
				sql3 = "INSERT into faculty(username,department_id) values('" + userName + "','" + departmentId + "');";
			}
			stmt = conn.DbConnectionForStatement();
			stmt.execute(sql1);
			stmt.execute(sql2);
			stmt.execute(sql3);
			
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
		//Redirect to display success message after insertion
		response.sendRedirect("confirmation_page.jsp");
		
	}
}
