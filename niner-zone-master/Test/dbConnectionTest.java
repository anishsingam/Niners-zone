import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import db.DbConnection;


public class dbConnectionTest {

	@Test
	public void testDbConnectionForStatement() throws SQLException {
		DbConnection dbtest = new DbConnection("test");
		Statement stmt = null;
		stmt = dbtest.DbConnectionForStatement();
		String sql = "select first_name from user where username = 'deepakrohan'";
		assertTrue(stmt.execute(sql));
	}	
}
