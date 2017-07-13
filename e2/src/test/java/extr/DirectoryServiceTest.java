package extr;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class DirectoryServiceTest {

	DirectoryService ds;
	
	@Before
	public void Before	() throws Exception {
		ds = new DirectoryService(Paths.get(""));
	}

//	@Test
//	public void testDirectoryService() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetDestDir() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void removeDigitDotUnderscoreTest() {
//		assertEquals("Datablad._Rorliga_minor_", ds.removeDigitOrLetterDotUnderscore("6._Datablad._Rorliga_minor_"));
//		assertEquals("Losa_skott", ds.removeDigitOrLetterDotUnderscore("B._Losa_skott"));
//	}
//	
//	@Test
//	public void removeStartingRomanNumeralsTest() {
//		assertEquals("Datablad._Rorliga_minor_", ds.removeStartingRomanNumerals("I_Datablad._Rorliga_minor_"));
//		assertEquals("Losa_skott", ds.removeStartingRomanNumerals("VI_Losa_skott"));
//		assertEquals("Losa_skott", ds.removeStartingRomanNumerals("VII_Losa_skott"));
//		assertEquals("Losa_skott", ds.removeStartingRomanNumerals("XIII_Losa_skott"));
//	}
	
	
//	@Test
//	public void getDestDirTest() {
//		assertEquals("Datablad._Rorliga_minor_", ds.getDestDir("6._Datablad._Rorliga_minor_"));
//
//	}

//	@Test
//	public void testChangeKnownCmCalibersToMm() {
//		fail("Not yet implemented");
//	}

}
