import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.ForumPostDAO;
import model.ForumPostDTO;
import model.GradeDAO;
import model.GradeDTO;

import org.junit.Before;
import org.junit.Test;


public class ForumPostDAOTest {
	private static final String DisplayType = null;
	ForumPostDAO fpdao;
	@Before
	public void setUp() {
		fpdao= new ForumPostDAO("test");
	}
	
	
	@Test
	public void ExistenceTest()
	{
		int boardId = 1;
		List<ForumPostDTO> u = fpdao.getPostList(boardId);
		assertTrue(u.size() > 0);
	}
	@Test
	public void nullTest()
	{
		int boardId = 0;
		List<ForumPostDTO> u = fpdao.getPostList(boardId);
		assertTrue(u.size() == 0);
	}
	
	@Test
	public void checkIdTest()
	{
		int boardId = 1;
		List<ForumPostDTO> u = fpdao.getPostList(boardId);
		assertEquals(17,u.get(0).getPost_id());
	}
	
	@Test
	public void checkTitleTest()
	{
		int boardId = 1;
		List<ForumPostDTO> u = fpdao.getPostList(boardId);
		assertEquals("Title1",u.get(0).getTitle());
	}
	@Test
	public void checkOwnerTypeTest()
	{
		int boardId = 1;
		List<ForumPostDTO> u = fpdao.getPostList(boardId);
		assertEquals("faculty",u.get(0).getOwner_type());
	}
	
	@Test
	public void checkContentTest()
	{
		int boardId = 1;
		List<ForumPostDTO> u = fpdao.getPostList(boardId);
		assertEquals("Content1",u.get(0).getContent());
	}
	

	@Test
			public void editTest()
			{
				Date now = new Date();
				int postId = 8;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				int boardId = 1;
				List<ForumPostDTO> u = fpdao.getPostList(boardId);
				int a = u.size();
				String strDate = sdf.format(now);;
				String Description_ForumPost = "Content1 j Testing";
				String title_ForumPost = "Title1 Testing";
				String displayType = "anonymous_to_all";
				fpdao.updatePost(postId,displayType, title_ForumPost, Description_ForumPost, strDate);
				List<ForumPostDTO> b = fpdao.getPostList(boardId);
				assertEquals(a, b.size());
			}
}
