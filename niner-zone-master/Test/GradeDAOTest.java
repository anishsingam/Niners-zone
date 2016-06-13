import static org.junit.Assert.*;

import java.util.List;

import model.GradeDAO;
import model.GradeDTO;
import model.UserBean;

import org.junit.Before;
import org.junit.Test;

import controller.ViewParticipants;


public class GradeDAOTest {

	GradeDAO gdao;
	@Before
	public void setUp() {
		gdao= new GradeDAO("test");
		
	}
	@Test
	public void ExistenceTest()
	{
		String boardId = "4";
		List<GradeDTO> u = gdao.getGradesOfAllStudents(boardId);
		System.out.println(u.toString());
		assertTrue(u.size() > 0);
	}
	@Test
	public void nullTest()
	{
		String boardId = "";
		List<GradeDTO> u = gdao.getGradesOfAllStudents(boardId);
		assertTrue(u.size() == 0);
	}

	@Test
	public void checkrollnumbertest()
	{
		String boardId = "4";
		List<GradeDTO> u = gdao.getGradesOfAllStudents(boardId);
		assertEquals(801, u.get(0).getStudentRollNumber());
	}
	
	
}
