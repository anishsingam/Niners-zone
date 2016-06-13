package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;

public class AssignmentPostDAO {
	private List<AssignmentPostDTO> academicPosts;
	
	public String test;
	
    public AssignmentPostDAO(String test) {
		super();
		this.test = test;
	}

	public AssignmentPostDAO()
    {
		this.test = null;
    }
	
	public List<AssignmentPostDTO> getAcademicPosts(String boardId){
		academicPosts = new ArrayList<AssignmentPostDTO>();
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
			sql = "SELECT assignment_post.post_id,assignment_post.academic_board_id,assignment_post.assignment_material_id,assignment_post.title, assignment_post.description,assignment_post.creation_date,assignment_post.due_date, assignment_material.material_id, material.link from assignment_post inner join assignment_material on assignment_post.assignment_material_id=assignment_material.id INNER JOIN material on assignment_material.material_id=material.material_id where assignment_post.academic_board_id = '"+boardId+"';";
 
			//All the details from the database
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int postId = rs.getInt("post_id");
				int academicBoardId = rs.getInt("academic_board_id");
				int assignmentMaterialId = rs.getInt("assignment_material_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String creationDate = rs.getString("creation_date");
				String dueDate = rs.getString("due_date");
				String materialLink = rs.getString("link");
				AssignmentPostDTO academicPost = new AssignmentPostDTO(postId, academicBoardId, assignmentMaterialId, title, description, creationDate, dueDate,  materialLink);
				academicPosts.add(academicPost);
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
	return academicPosts;
	}
	
	
}


