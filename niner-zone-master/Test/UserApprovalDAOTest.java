import static org.junit.Assert.*;

import java.util.List;

import model.UserApprovalDAO;
import model.UserApprovalDTO;

import org.junit.Before;
import org.junit.Test;

public class UserApprovalDAOTest {

	UserApprovalDTO dto;
	UserApprovalDAO DAO;

	@Before
	public void setUp() throws Exception {
		dto = new UserApprovalDTO();
		DAO = new UserApprovalDAO("test");
	}

	@Test
	public void validateUsernameSuccess() {
		dto.setUserName("prabhu");
		boolean check = false;
		for (UserApprovalDTO dto2 : DAO.getUserList())
			if (dto.getUserName().equals(dto2.getUserName())) {
				check = true;
				break ;
			}
		assertTrue(check);
	}

	@Test
	public void validateUserNameFailure() {
		dto.setUserName("king");
		boolean check = false;
		for (UserApprovalDTO dto2 : DAO.getUserList())
			if (dto.getUserName().equals(dto2.getUserName())) {
				check = true;
				break;
			}
		assertFalse("Success validation of Username for failure case", check);
	}
	@Test
	public void userListHasValue(){
		List<UserApprovalDTO> userList = DAO.getUserList();
		assertTrue(userList.size() > 0);
	}
	
	@Test
	public void objNotNull(){
		assertNotNull(DAO);
		assertNotNull(dto);
	}
}
