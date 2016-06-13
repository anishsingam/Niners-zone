import static org.junit.Assert.*;

import java.util.List;

import model.CourseDAO;
import model.UserBean;

import org.junit.Before;
import org.junit.Test;

import controller.StudentHomeServlet;
import controller.ViewParticipants;


public class AcademicBoardTest {
	CourseDAO sh;
		@Before
		public void setUp() {
			sh= new CourseDAO("test");
			
		}

	@Test
	public void academicBoardExistenceTest()
	{
		String name = "srinivas";
		String course_id = "ITCS5187";
		int u = sh.getAcademicBoardId(name, course_id);
		assertTrue(u != 0);
	}
	
	@Test
	public void nullTest()
	{
		String name = "";
		String course_id = "";
		int u = sh.getAcademicBoardId(name, course_id);
		assertTrue(u == 0);
		
	}
	@Test
	public void checkIdTest()
	{
		String name = "srinivas";
		String course_id = "ITCS5187";
		int u = sh.getAcademicBoardId(name, course_id);
		assertTrue(u == 1);
	}

}
