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
		assertEquals("remove space btween digit and cm", "10,5cm ovngr", fns.removeSpaceBeforeMmAndCm("10,5 cm ovngr"));
		assertEquals("remove space btween digit and mm", "47mm ovngr", fns.removeSpaceBeforeMmAndCm("47 mm ovngr"));
	}
	@Test
	public void commaToDotInCalibresTest() {
		assertEquals("replace comma between digits", "10.5 cm ovngr", fns.commaToDotInCalibres("10,5 cm ovngr"));
	}
	
	@Test
	public void runAllStringFixes() {
		assertEquals("replace comma and remove space in calibers between digits", "10.5cm ovngr", fns.runAllStringFixes("10,5 cm ovngr"));
//		assertNotEquals("dont replace dot between digits", "47 mm ovngr", fns.commaToDotInCalibres("10.5 cm ovngr"));
	}
}
