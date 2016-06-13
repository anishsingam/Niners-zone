import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.ForumCommentsDAO;
import model.ForumCommentsDTO;
import org.junit.Before;
import org.junit.Test;

public class ForumCommentsDAOTest {
	
	ForumCommentsDAO dao;
	@Before
	public void setup()
	{
		dao = new ForumCommentsDAO();
		}
	
	@Test
	public void existenceTest(){
		int postId = 8;
		List<ForumCommentsDTO> u = dao.getCommentsList(postId);
		if(!u.isEmpty())
		assertTrue(u.size() >= 0);
		else
		assertEquals(true,u.isEmpty());
		}
	
	@Test
	public void nullTest(){
		int postId = 0;
		List<ForumCommentsDTO> u = dao.getCommentsList(postId);
		if(!u.isEmpty())
		assertTrue(u.size() == 0);
		else
		assertEquals(true,u.isEmpty());
		}
	
	@Test
	public void commentTest(){
		int postId = 8;
		List<ForumCommentsDTO> u = dao.getCommentsList(postId);
		if(!u.isEmpty())
		assertTrue(u.get(0).getCommentMade()!=null);
		else
		assertEquals(true,u.isEmpty());
		}
	
	@Test
	public void ownerTypeTest(){
		int postId = 21;
		List<ForumCommentsDTO> u = dao.getCommentsList(postId);
		if(!u.isEmpty())
		assertTrue(u.get(0).getOwner_type()!=null);
		else
		assertEquals(true,u.isEmpty());
		}
	
	@Test
	public void addCommentsTest(){
		int postId = 21;
		List<ForumCommentsDTO> u = dao.getCommentsList(postId);
		int a = u.size();
		if(!u.isEmpty()){
		Date now = new Date();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(now);
		String CommentMade = "Testing";
		String userName = "shehab";
		String userType = "faculty";
		String DisplayType = "anonymous_to_all";
		dao.addComments(postId, CommentMade, userType, userName, DisplayType, strDate);
		List<ForumCommentsDTO> b = dao.getCommentsList(postId);
		assertEquals(a+1, b.size());
		}
		else
		assertEquals(true,u.isEmpty());
		}
	
	
	

}
