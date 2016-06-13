package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbConnection;

public class CollegeDAO {
	private List<CollegeDTO> collegeList ;
 
	public String test;
	
    public CollegeDAO(String test) {
		super();
		this.test = test;
	}

	public CollegeDAO()
    {
		this.test = null;
    }
    public void setCollegeList(List<CollegeDTO> collegeList) {
		this.collegeList = collegeList;
	}


	public List<CollegeDTO> getCollegeList()
    {
    	collegeList = new ArrayList<CollegeDTO >() ;
        
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
			sql = "SELECT college_id,college_name from college;";														
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int collegeId = rs.getInt("college_id");
				String collegeName = rs.getString("college_name");
				CollegeDTO collegeDTO = new CollegeDTO(collegeId,collegeName);
				collegeList.add(collegeDTO);
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
        
        return collegeList;   
    }
}
