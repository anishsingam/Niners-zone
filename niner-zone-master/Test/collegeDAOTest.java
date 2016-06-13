import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;
import model.CollegeDAO;
import model.CollegeDTO;

import org.junit.Test;


public class collegeDAOTest {
	CollegeDAO cList = new CollegeDAO("test");
	@Test
	public void Existencetest() {
		List<CollegeDTO> collegeList = cList.getCollegeList();
		assertTrue(collegeList.size() > 0);
	 }
	
	
	}


