package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;

public class ForumPostDAO {
	private List<ForumPostDTO> postList ;
	 
	public String test;
	
    public ForumPostDAO(String test) {
		super();
		this.test = test;
	}

	public ForumPostDAO()
    {
		this.test = null;
    }
 
    public List<ForumPostDTO> getPostList(int board_id)
    {
    	
    	postList = new ArrayList<ForumPostDTO>() ;  
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
			sql = "Select * from discussion_forum_post where board_id = '"+board_id+"';";														
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int  post_id = rs.getInt("id");
				String owner_type = rs.getString("owner_type");
				String owner_userName = rs.getString("owner_username");
				String display_type = rs.getString("display_type") ;
				String title = rs.getString("title");
				String content = rs.getString("content");
				String date_of_post = rs.getString("date_of_post");
				
				ForumPostDTO DTO = new ForumPostDTO(post_id, board_id, owner_type, owner_userName, display_type, title, content,date_of_post);
				postList.add(DTO);
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
		System.out.println(postList.toString());
        return postList;   
        
    }
    
    public ForumPostDTO getPost(int postId)
    {
    	ForumPostDTO forumPostDTO = null;  
    	Statement stmt = null;
		DbConnection conn = null;
		
		int ForumPostId=0;
    	int BoardId=0;
    	String ownerType="";
    	String owner_username="";
    	String titleForum="";
    	String contentForum="";
    	String displayType="";
    	
    	String dateOfChange="";
    	try{
    		if(this.test == null){
    			conn = new DbConnection();
    		}
    		else{
    			conn = new DbConnection(test);
    		}
			String sql;
			
			sql = "SELECT id,board_id,owner_type,owner_username,display_type,title,content,date_of_post FROM discussion_forum_post WHERE id='"+postId+"';";
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ForumPostId = rs.getInt("id");
				BoardId = rs.getInt("board_id");
				ownerType = rs.getString("owner_type");
				owner_username=rs.getString("owner_username");
				 titleForum = rs.getString("title");
				 contentForum = rs.getString("content");
				 displayType = rs.getString("display_type");
				dateOfChange = rs.getString("date_of_post");				
			    forumPostDTO = new ForumPostDTO(ForumPostId, BoardId, ownerType, owner_username, displayType, titleForum, contentForum,dateOfChange);
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
        return forumPostDTO;   
        
    }
    
    public void deletePost(int postIdForDelete){
    	Statement stmt = null;
		DbConnection conn = null;
    	try{
    		if(this.test == null){
    			conn = new DbConnection();
    		}
    		else{
    			conn = new DbConnection(test);
    		}
			String sql1,sql2;
			
			sql2 = "Delete from discussion_forum_comments where post_id = '"+ postIdForDelete + "';";
			sql1 = "Delete from discussion_forum_post where id = '"+ postIdForDelete + "';";
			stmt = conn.DbConnectionForStatement();
			stmt.execute(sql2);
			stmt.execute(sql1);
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
    
    public void updatePost(int postId, String DisplayType, String titleForumPost, String DescriptionForumPost, String strDate)
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
			sql = "UPDATE discussion_forum_post SET display_type ='"+DisplayType+"',title =\""+titleForumPost+"\",content =\""+ DescriptionForumPost + "\",date_of_post='"+strDate+"' WHERE id = '"+postId+"';";
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
    
    public void addPost(int sessionBoard, String userType, String  userName, String DisplayType,String titleForumPost,String DescriptionForumPost, String strDate)
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
			sql = "INSERT into discussion_forum_post(board_id,owner_type,owner_username,display_type,title,content,date_of_post) values(\"" +sessionBoard+ "\",\"" + userType + "\",\""+userName+"\",\""+DisplayType+"\",\""+titleForumPost+"\",\""+DescriptionForumPost+"\",\""+strDate+"\");";
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
    
	
}
