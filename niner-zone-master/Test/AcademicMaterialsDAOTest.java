import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;
import model.AcademicMaterialsDAO;
import model.AcademicMaterialsDTO;


import org.junit.Before;
import org.junit.Test;

public class AcademicMaterialsDAOTest {
	AcademicMaterialsDAO acadMaterial;

	@Before
	public void setUp() {
	    
		acadMaterial = new AcademicMaterialsDAO("test");
	}

	@Test
	public void materialExistencetest() {
		String board_id = "1";
		List<AcademicMaterialsDTO> material = acadMaterial.getAcademicMaterials(board_id);
		assertTrue(material.size() > 0);
	}

	@Test
	public void nulltest() {
		String board_id = "";
		List<AcademicMaterialsDTO> material = acadMaterial.getAcademicMaterials(board_id);
		assertTrue(material.size() == 0);
	}

	@Test
	public void materialsCount() {
		String board_id = "1";
		List<AcademicMaterialsDTO> material = acadMaterial.getAcademicMaterials(board_id);
		assertTrue(material.size() > 0);
	}

	@Test
	public void checkMaterial_ID() {
		String board_id = "4";
		boolean check = false ;
		List<AcademicMaterialsDTO> material = acadMaterial.getAcademicMaterials(board_id);
		for ( AcademicMaterialsDTO dto : material){
			if (dto.getMaterial_id() == 19)
				check = true ;
		}
		assertTrue("Successfull validation of Material ID",check);
	}

	@Test
	public void CheckTitle() {
		String board_id = "4";
		boolean check = false ;
		List<AcademicMaterialsDTO> material = acadMaterial.getAcademicMaterials(board_id);
		for ( AcademicMaterialsDTO dto : material){
			if (dto.getTitle().equals("Test y"))
				check = true ;
		}
		assertTrue("Successfull validation of Tile",check);
	}
	@Test
	public void CheckType() {
		String board_id = "1";
		List<AcademicMaterialsDTO> material = acadMaterial.getAcademicMaterials(board_id);
		assertEquals("academic",(material.get(0).getType()));
	}
	
}