import static org.junit.Assert.*;

import java.util.List;

import model.AssignmentPostDAO;
import model.AssignmentPostDTO;
import model.GradeDAO;
import model.GradeDTO;

import org.junit.Before;
import org.junit.Test;


public class AssignmentPostDAOTest {
	AssignmentPostDAO apdao;
	@Before
	public void setUp() {
		apdao= new AssignmentPostDAO("test");
		
	}
	@Test
	public void ExistenceTest()
	{
		String boardId = "4";
		List<AssignmentPostDTO> u = apdao.getAcademicPosts(boardId);
		assertTrue(u.size() > 0);
	}
	@Test
	public void nullTest()
	{
		String boardId = "";
		List<AssignmentPostDTO> u = apdao.getAcademicPosts(boardId);
		assertTrue(u.size() == 0);
	}
	@Test
	public void checkPostIdTest()
	{
		String boardId = "4";
		List<AssignmentPostDTO> u = apdao.getAcademicPosts(boardId);
		assertEquals(26,u.get(0).getPostId());
	}
	@Test
	public void checkAcadIdTest()
	{
		String boardId = "4";
		List<AssignmentPostDTO> u = apdao.getAcademicPosts(boardId);
		assertEquals(4,u.get(0).getAcademicBoardId());
	}
	@Test
	public void checkAssignmentMaterialIdTest()
	{
		String boardId = "4";
		List<AssignmentPostDTO> u = apdao.getAcademicPosts(boardId);
		assertEquals(5,u.get(0).getAssignmentMaterialId());
	}
	@Test
	public void checktitleTest()
	{
		String boardId = "4";
		List<AssignmentPostDTO> u = apdao.getAcademicPosts(boardId);
		assertEquals("I am gonna check this",u.get(0).getTitle());
	}
}
