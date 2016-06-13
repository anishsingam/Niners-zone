package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;


public class ForumCommentsDAO {
	private List<ForumCommentsDTO> commentsList ;
public String test;
	
    public ForumCommentsDAO(String test) {
		super();
		this.test = test;
	}

	public ForumCommentsDAO()
    {
		this.test = null;
    }
	
	 public List<ForumCommentsDTO> getCommentsList(int post_id)
	    {
	    	
	    	commentsList = new ArrayList<ForumCommentsDTO>() ;  
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
				sql = "Select * from discussion_forum_comments where post_id = '"+post_id+"';";														
				stmt = conn.DbConnectionForPreparedStatement(sql);			
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					int  id = rs.getInt("id");
					String commentMade = rs.getString("comment_made");
					String owner_type = rs.getString("owner_type");
					String owner_userName = rs.getString("owner_username");
					String display_type = rs.getString("display_type") ;
					String dateOfComment = rs.getString("date_of_comment");
					
					ForumCommentsDTO DTO = new ForumCommentsDTO(id, post_id, owner_type, owner_userName, display_type, commentMade, dateOfComment);
					commentsList.add(DTO);
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
	        return commentsList;   
	        
	    }
	 
	 public void addComments(int postId, String CommentMade, String userType, String userName ,String DisplayType ,  String strDate)
	    { 
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
				sql = "INSERT into discussion_forum_comments(post_id,comment_made,owner_type,owner_username,display_type,date_of_comment) values(\"" +postId+ "\",\"" + CommentMade + "\",\""+userType+"\",\""+userName+"\",\""+DisplayType+"\",\""+strDate+"\");";
				stmt = conn.DbConnectionForStatement();
				stmt.execute(sql);
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
	 
	 public ForumCommentsDTO getComments(int postId)
	    {
	    	ForumCommentsDTO forumCommentsDTO = null;  
	    	Statement stmt = null;
			DbConnection conn = null;
			
			int comment_id=0;
	    	int post_id=0;
	    	String commentMade="";
	    	String ownerType="";
	    	String owner_username="";
	    	String displayType="";
	    	
	    	String dateOfComment="";
	    	try{
	    		if(this.test == null){
	    			conn = new DbConnection();
	    		}
	    		else{
	    			conn = new DbConnection(test);
	    		}
				String sql;
				
				sql = "SELECT id,post_id,comment_made,owner_type,owner_username,display_type,date_of_comment FROM discussion_forum_comments WHERE post_id='"+postId+"';";
				stmt = conn.DbConnectionForPreparedStatement(sql);			
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					comment_id = rs.getInt("id");
					post_id = rs.getInt("post_id");
					commentMade = rs.getString("comment_made");
					ownerType = rs.getString("owner_type");
					owner_username=rs.getString("display_type");
					 displayType = rs.getString("display_type");
					dateOfComment = rs.getString("date_of_comment");
					forumCommentsDTO = new ForumCommentsDTO(comment_id, post_id, ownerType, owner_username, displayType, commentMade, dateOfComment);
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
	        return forumCommentsDTO;   
	        
	    }
	
}
