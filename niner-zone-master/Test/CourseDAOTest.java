import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;
import model.CourseDAO;
import model.CourseDTO;

import org.junit.Before;
import org.junit.Test;

public class CourseDAOTest {
	CourseDAO coList;

	@Before
	public void setUp() {
		coList = new CourseDAO("test");
	}

	@Test
	public void proftest() {
		String s = "shehab";
		List<CourseDTO> courseList = coList.getCourseList(s);
		assertTrue(courseList.size() == 0);
	}

	@Test
	public void nulltest() {
		List<CourseDTO> courseList = coList.getCourseList("");
		assertTrue(courseList.size() == 0);
	}

	@Test
	public void courseApprovedCount() {
		List<CourseDTO> approvedList = coList.getapprovedList("shehab");
		assertTrue(approvedList.size() > 0);
	}

	@Test
	public void courseApprovedCountShehab() {
		List<CourseDTO> approvedList = coList.getapprovedList("shehab");
		assertTrue(approvedList.size() == 4);
	}

	@Test
	public void noCoursesforStudCount() {
		List<CourseDTO> approvedList = coList.getapprovedList("rohan123");
		assertTrue(approvedList.size() == 0);
	}

	@Test
	public void checkCourseCode() {
		List<CourseDTO> approvedList = coList.getapprovedList("shehab");
		assertEquals("ITCS5187", (approvedList.get(0).getCourseId()));
	}

	@Test
	public void courseMobile() {
		List<CourseDTO> approvedList = coList.getapprovedList("shehab");
		assertEquals("Mobile Application Development",
				(approvedList.get(0).getCourseName()));
	}
	@Test
	public void course2Database() {
		List<CourseDTO> approvedList = coList.getapprovedList("shehab");
		assertEquals("SSDI",(approvedList.get(1).getCourseName()));
	}
	
	
}
