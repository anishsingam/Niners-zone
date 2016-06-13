import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;
import model.CourseDAO;
import model.CourseDTO;

import org.junit.Before;
import org.junit.Test;

public class ListCoursesDAOTest {
	CourseDAO coList;

	@Before
	public void setUp() {
		coList = new CourseDAO("test");
	}

	@Test
	public void coursetest() {
		String s = "chandar";
		List<CourseDTO> courseListforStudents = coList.getCourseListforStudents(s);
		assertTrue(courseListforStudents.size() > 0);
	}

	@Test
	public void nulltest() {
		List<CourseDTO> courseListforStudents = coList.getCourseListforStudents("");
		assertTrue(courseListforStudents.size() == 0);
	}

	@Test
	public void coursesCount() {
		String s = "chandar";
		List<CourseDTO> courseListforStudents = coList.getCourseListforStudents(s);
		assertTrue(courseListforStudents.size() > 0);
	}

	@Test
	public void checkCourseCode() {
		String s = "chandar";
		List<CourseDTO> courseListforStudents = coList.getCourseListforStudents(s);
		assertEquals("ITCS6160", (courseListforStudents.get(0).getCourseId()));
	}

	@Test
	public void CheckCourseName() {
		String s = "chandar";
		List<CourseDTO> courseListforStudents = coList.getCourseListforStudents(s);		
		assertEquals("Database Systems",
				(courseListforStudents.get(0).getCourseName()));
	}
	@Test
	public void course2Database() {
		String s = "chandar";
		List<CourseDTO> courseListforStudents = coList.getCourseListforStudents(s);
		assertEquals("Database Systems",(courseListforStudents.get(0).getCourseName()));
	}
	
}