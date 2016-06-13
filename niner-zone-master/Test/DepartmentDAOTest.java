import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;
import model.DepartmentDAO;
import model.DepartmentDTO;

import org.junit.Before;
import org.junit.Test;

public class DepartmentDAOTest {
	DepartmentDAO DeDAO;

	@Before 
	public void setUp(){
		DeDAO = new DepartmentDAO("test");
	}
	@Test
	public void getDeptListSize() {
		List<DepartmentDTO> departmentList = DeDAO.getDepartmentList(1);
		assertTrue(departmentList.size() > 0);
	}

	@Test
	public void getDeptName() {
		List<DepartmentDTO> departmentList = DeDAO.getDepartmentList(1);
		assertEquals("SIS", (departmentList.get(1)).getDepartmentName());
	}

	@Test
	public void noDepartment() {
		List<DepartmentDTO> departmentList = DeDAO.getDepartmentList(0);
		assertTrue(departmentList.size() == 0);
	}

	@Test
	public void invalidDeptName() {
		List<DepartmentDTO> departmentList = DeDAO.getDepartmentList(1);
		assertNotSame("SIS", (departmentList.get(0)).getDepartmentName());
	}

	@Test
	public void objNotNull() {
		assertNotNull(DeDAO);
	}
}
