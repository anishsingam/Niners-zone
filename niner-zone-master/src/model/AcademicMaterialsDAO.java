package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;

public class AcademicMaterialsDAO {

	private List<AcademicMaterialsDTO> academicMaterials;
	private List<AcademicMaterialsDTO> assignmentMaterials;
	
	public String test;
	
    public AcademicMaterialsDAO(String test) {
		super();
		this.test = test;
	}

	public AcademicMaterialsDAO()
    {
		this.test = null;
    }
    
	
	public List<AcademicMaterialsDTO> getAssignmentMaterials(String boardId) {
		assignmentMaterials = new ArrayList<AcademicMaterialsDTO>();
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
			sql = "SELECT material_id,academic_board_id,title,description,link,type,file_key FROM material where academic_board_id = '" +boardId+"' and type = 'assignment';";
			//All the details from the database
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int materialId = rs.getInt("material_id");
				int academicBoardId = rs.getInt("academic_board_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String link = rs.getString("link");
				String type = rs.getString("type");
				String file_key = rs.getString("file_key");
				AcademicMaterialsDTO assignmentMaterial = new AcademicMaterialsDTO(materialId, academicBoardId, title, description, link, type,file_key);
				assignmentMaterials.add(assignmentMaterial);
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
	return assignmentMaterials;
	}



	public List<AcademicMaterialsDTO> getAcademicMaterials(String boardId){
		academicMaterials = new ArrayList<AcademicMaterialsDTO>();
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
			sql = "SELECT material_id,academic_board_id,title,description,link,type FROM material where academic_board_id = '" +boardId+"' and type = 'academic';";
			//All the details from the database
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int materialId = rs.getInt("material_id");
				int academicBoardId = rs.getInt("academic_board_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String link = rs.getString("link");
				String type = rs.getString("type");
				AcademicMaterialsDTO academicMaterial = new AcademicMaterialsDTO(materialId, academicBoardId, title, description, link, type);
				academicMaterials.add(academicMaterial);
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
	return academicMaterials;
	}
	
	
}
