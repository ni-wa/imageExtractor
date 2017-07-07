package extr;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FileNameServiceTest {
	
	FileNameService fns;

	@Before
	public void before() {
		fns = new FileNameService();
	}

	@Test
	public void removeSpaceBeforemmTest() {
		assertEquals("remove space btween digit and cm", "10,5cm övngr", fns.removeSpaceBeforeMmAndCm("10,5 cm övngr"));
		assertEquals("remove space btween digit and mm", "47mm övngr", fns.removeSpaceBeforeMmAndCm("47 mm övngr"));
	}
	@Test
	public void commaToDotInCalibresTest() {
		assertEquals("replace comma between digits", "10.5 cm övngr", fns.commaToDotInCalibres("10,5 cm övngr"));
	}
	
	@Test
	public void runAllStringFixes() {
		assertEquals("replace comma and remove space in calibers between digits", "10.5cm övngr", fns.runAllStringFixes("10,5 cm övngr"));
//		assertNotEquals("dont replace dot between digits", "47 mm övngr", fns.commaToDotInCalibres("10.5 cm övngr"));
	}

}
