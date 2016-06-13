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

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.google.gson.Gson;

import db.DbConnection;
import model.AcademicMaterialsDAO;
import model.AcademicMaterialsDTO;
import model.AssignmentPostDAO;
import model.AssignmentPostDTO;
import model.CollegeDTO;

/**
 * Servlet implementation class FacultyAcademicBoardServlet
 */
@WebServlet(name = "faculty_academic_board", urlPatterns = { "/faculty_academic_board" })
public class FacultyAcademicBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultyAcademicBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currentAcademicBoardId = (Integer) request.getSession().getAttribute("currentAcademicBoardId");
		
		if(request.getParameterMap().containsKey("type") && request.getParameter("type").equals("academic")){		
			AcademicMaterialsDAO academicMaterialsDAO = new AcademicMaterialsDAO();
			List<AcademicMaterialsDTO> academicMaterials = academicMaterialsDAO.getAcademicMaterials(currentAcademicBoardId+"");
		    String json2 = new Gson().toJson(academicMaterials);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json2);
	
		}
		
		if(request.getParameterMap().containsKey("type") && request.getParameter("type").equals("assignment")){		
			AcademicMaterialsDAO academicMaterialsDAO = new AcademicMaterialsDAO();
			List<AcademicMaterialsDTO> assignmentMaterials = academicMaterialsDAO.getAssignmentMaterials(currentAcademicBoardId+"");
		    String json2 = new Gson().toJson(assignmentMaterials);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json2);
	
		}
		
		if(request.getParameterMap().containsKey("type") && request.getParameter("type").equals("assignmentpost")){		
			
		    AssignmentPostDAO assignmentPostDAO = new AssignmentPostDAO();
		    List<AssignmentPostDTO> assignmentPosts = assignmentPostDAO.getAcademicPosts(currentAcademicBoardId+"");
			String json2 = new Gson().toJson(assignmentPosts);
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
		String userName = (String) request.getSession().getAttribute("userName");
		int currentAcademicBoardId = (Integer) request.getSession().getAttribute("currentAcademicBoardId");
		
		if(request.getParameterMap().containsKey("typeOfDeletion") && request.getParameter("typeOfDeletion").equals("academic")){
			String d = request.getParameter("totalNumberOfMaterials");
			int length = Integer.parseInt(d);	
			for(int i = 1; i<=length ;i++){
				String myCheckBoxValue = request.getParameter("checked"+i);
				if(myCheckBoxValue != null && myCheckBoxValue.equals("on")){
					String materialId = request.getParameter("materialid"+i);
					
					deleteMaterialFileFromCloud(currentAcademicBoardId,materialId);
					deleteAcademicMaterials(currentAcademicBoardId,materialId);
				}
			}
			response.sendRedirect("academic_board.jsp");
		}
		
		if(request.getParameterMap().containsKey("typeOfDeletion") && request.getParameter("typeOfDeletion").equals("assignment")){
			String d = request.getParameter("totalNumberOfMaterials");
			int length = Integer.parseInt(d);	
			for(int i = 1; i<=length ;i++){
				String myCheckBoxValue = request.getParameter("checked"+i);
				if(myCheckBoxValue != null && myCheckBoxValue.equals("on")){
					String materialId = request.getParameter("materialid"+i);
					
					deleteMaterialFileFromCloud(currentAcademicBoardId,materialId);
					deleteAssignmentMaterials(currentAcademicBoardId,materialId);
				}
			}
			response.sendRedirect("academic_board.jsp");
		}
		
		if(request.getParameterMap().containsKey("typeOfDeletion") && request.getParameter("typeOfDeletion").equals("assignmentpost")){
			String d = request.getParameter("totalNumberOfPosts");
			int length = Integer.parseInt(d);	
			for(int i = 1; i<=length ;i++){
				String myCheckBoxValue = request.getParameter("checked"+i);
				if(myCheckBoxValue != null && myCheckBoxValue.equals("on")){
					String postId = request.getParameter("postid"+i);					
					deleteAssignmentPosts(currentAcademicBoardId,postId);
				}
			}
			response.sendRedirect("academic_board.jsp");
		}
		
	}
	
	private void deleteAcademicMaterials(int boardId,String materialId){
		Statement stmt = null;
		DbConnection conn = null;
		try{
			conn = new DbConnection();
			String sql1,sql2;

			sql1 = "delete from academic_material where material_id ='" + materialId + "'";
			sql2 = "delete from material where material_id ='" + materialId + "' and academic_board_id ='" + boardId + "'";			
			stmt = conn.DbConnectionForStatement();
			stmt.execute(sql1);
			stmt.execute(sql2);
			
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
	
	private void deleteAssignmentPosts(int boardId,String postId){
		Statement stmt = null;
		DbConnection conn = null;
		try{
			conn = new DbConnection();
			String sql1;

			sql1 = "delete from assignment_post where post_id ='" + postId + "' and academic_board_id = '" + boardId + "'";		
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
	
	
	
	private void deleteAssignmentMaterials(int boardId,String materialId){
		Statement stmt = null;
		DbConnection conn = null;
		try{
			conn = new DbConnection();
			String sql1,sql2;

			sql1 = "delete from assignment_material where material_id ='" + materialId + "'";
			sql2 = "delete from material where material_id ='" + materialId + "' and academic_board_id ='" + boardId + "'";			
			stmt = conn.DbConnectionForStatement();
			stmt.execute(sql1);
			stmt.execute(sql2);
			
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
	
	
	
	//delete from Amazon cloud
	private void deleteMaterialFileFromCloud(int boardId,String materialId){
		String bucketName = "uncccsboard"+boardId;
     	String access_key = "AKIAJAQMBKKRDVJVQFQA";
		String secret_key = "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";
		AWSCredentials credentials = new BasicAWSCredentials(access_key,secret_key);
		AmazonS3 conn = new AmazonS3Client(credentials);
		Statement stmt = null;
		DbConnection dbconn = null;
		try{
			dbconn = new DbConnection();
			String sql;
			sql = "SELECT file_key from material where material_id='"+materialId+"';";
			stmt = dbconn.DbConnectionForStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String keyName = rs.getString("file_key");
				conn.deleteObject(new DeleteObjectRequest(bucketName, keyName));
			}
			rs.close();
			stmt.close();
		}catch(SQLException se){
		
		
	}catch (AmazonServiceException ase) {
        System.out.println("Caught an AmazonServiceException.");
        System.out.println("Error Message:    " + ase.getMessage());
        System.out.println("HTTP Status Code: " + ase.getStatusCode());
        System.out.println("AWS Error Code:   " + ase.getErrorCode());
        System.out.println("Error Type:       " + ase.getErrorType());
        System.out.println("Request ID:       " + ase.getRequestId());
	}catch (AmazonClientException ace) {
        System.out.println("Caught an AmazonClientException.");
        System.out.println("Error Message: " + ace.getMessage());
	}
	}

}
