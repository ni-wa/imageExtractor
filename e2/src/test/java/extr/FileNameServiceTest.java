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
		assertEquals("remove space btween digit and cm", "10,5cm �vngr", fns.removeSpaceBeforeMmAndCm("10,5 cm �vngr"));
		assertEquals("remove space btween digit and mm", "47mm �vngr", fns.removeSpaceBeforeMmAndCm("47 mm �vngr"));
	}
	@Test
	public void commaToDotInCalibresTest() {
		assertEquals("replace comma between digits", "10.5 cm �vngr", fns.commaToDotInCalibres("10,5 cm �vngr"));
	}
	
	@Test
	public void runAllStringFixes() {
		assertEquals("replace comma and remove space in calibers between digits", "10.5cm �vngr", fns.runAllStringFixes("10,5 cm �vngr"));
//		assertNotEquals("dont replace dot between digits", "47 mm �vngr", fns.commaToDotInCalibres("10.5 cm �vngr"));
	}

}
