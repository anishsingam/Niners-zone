package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbConnection;

public class UserApprovalDAO {
	private List<UserApprovalDTO> userList ;
 
	public String test;
	
    public UserApprovalDAO(String test) {
		super();
		this.test = test;
	}

	public UserApprovalDAO()
    {
		this.test = null;
    }
 
    public List<UserApprovalDTO> getUserList()
    {
    	
    	userList = new ArrayList<UserApprovalDTO>() ;  
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
			sql = "Select username,user_type from user where username in (SELECT username FROM login_credential where approval_flag = '0');";														
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String userName = rs.getString("username");
				String userType = rs.getString("user_type");
				String approvalFlag = "0";
				UserApprovalDTO userApprovalDTO = new UserApprovalDTO(userName, userType, approvalFlag);
				userList.add(userApprovalDTO);
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
		
        return userList;   
        
    }
    
    public List<UserBean> getParticipants(String userName,String boardId){
		List<UserBean> result = new ArrayList<UserBean>();
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
			
			sql = "SELECT sc.stud_roll_number,st.username from student_course sc inner join student st on sc.stud_roll_number = st.roll_number where class_id in (SELECT class_id from academic_board where id = '" + boardId + "');";
			
			
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int studentRollNumber = rs.getInt("stud_roll_number");
				String studentUserName = rs.getString("username");
				UserBean user = new UserBean(studentUserName,studentRollNumber);
				result.add(user);
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
	return result;
	}
}
