import static org.junit.Assert.*;
import model.CourseDAO;

import org.junit.Before;
import org.junit.Test;

import controller.FacultyHomeServlet;
import controller.StudentHomeServlet;


public class getAcademicBoardIdforFaculty {

	CourseDAO sh;
	@Before
	public void setUp() {
		sh= new CourseDAO("test");
		
	}

@Test
public void academicBoardExistenceTest()
{
	String name = "shehab";
	String course_id = "ITCS5187";
	int u = sh.getAcademicBoardIdForFaculty(name, course_id);
	assertTrue(u != 0);
}
@Test
public void nullTest()
{
	String name = "";
	String course_id = "";
	int u = sh.getAcademicBoardIdForFaculty(name, course_id);
	assertTrue(u == 0);
	
}
@Test
public void checkIdTest()
{
	String name = "shehab";
	String course_id = "ITCS5187";
	int u = sh.getAcademicBoardIdForFaculty(name, course_id);
	assertTrue(u == 1);
}

}
