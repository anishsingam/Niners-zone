import static org.junit.Assert.*;

import java.util.List;

import model.AcademicMaterialsDAO;
import model.UserApprovalDAO;
import model.UserBean;

import org.junit.Before;
import org.junit.Test;

import controller.ViewParticipants;


public class ViewparticipantsTest {
	UserApprovalDAO vp;
	@Before
	public void setUp() {
		vp= new UserApprovalDAO("test");
		
	}

	@Test
	public void participantsExistenceTest()
	{
		String name = "rohana";
		String board_id = "1";
		List<UserBean> u = vp.getParticipants(name, board_id);
		assertTrue(u.size() > 0);
	}
	@Test
	public void nullParticipantsTest()
	{
		String name = "";
		String board_id = "";
		List<UserBean> u = vp.getParticipants(name, board_id);
		assertTrue(u.size() == 0);
	}
	@Test
	public void participantsCountTest()
	{
		String name = "rohana";
		String board_id = "1";
		List<UserBean> u = vp.getParticipants(name, board_id);
		assertTrue(u.size() > 0);
	}
	@Test
	public void checkrollnumberTest()
	{
		String name = "rohana";
		String board_id = "1";
		List<UserBean> u = vp.getParticipants(name, board_id);
		assertEquals(804, u.get(0).getUserRollNumber());
	}
	@Test
	public void checkNameTest()
	{
		String name = "sashank146";
		String board_id = "1";
		List<UserBean> u = vp.getParticipants(name, board_id);
		assertEquals("sashank146", u.get(0).getUserName());
	}
}
