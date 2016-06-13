package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import db.DbConnection;

public class DepartmentDAO {
	private List<DepartmentDTO> departmentList ;
 
	public String test;
	
    public DepartmentDAO(String test) {
		super();
		this.test = test;
	}

	public DepartmentDAO()
    {
		this.test = null;
    }
 
    public List<DepartmentDTO> getDepartmentList(int collegeID)
    {
    	departmentList = new ArrayList<DepartmentDTO>() ;
        
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
			sql = "SELECT department_id,department_name FROM department where college_id=" + collegeID + ";";														
			stmt = conn.DbConnectionForPreparedStatement(sql);			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int departmentId = rs.getInt("department_id");
				String departmentName = rs.getString("department_name");
				DepartmentDTO departmentDTO = new DepartmentDTO(collegeID, departmentId, departmentName);
				departmentList.add(departmentDTO);
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
        
        return departmentList;   
    }
}
