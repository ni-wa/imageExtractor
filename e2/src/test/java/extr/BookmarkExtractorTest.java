package extr;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BookmarkExtractorTest {

	BookmarkExtractor pdfFile;
	BookmarkExtractor pdfDirectory;
	Path pdfFile2 = Paths.get("C:\\Users\\nw\\Documents\\tmp\\A20.pdf");
	Path pdfDirectory2 = Paths.get("C:\\Users\\nw\\Documents\\tmp\\");
	
//	Extractor e;
	File f;
	
	@Before
	public void before() throws IOException{
		pdfFile = new BookmarkExtractor("C:\\Users\\nw\\Documents\\tmp\\A20.pdf");
		pdfDirectory = new BookmarkExtractor("C:\\Users\\nw\\Documents\\tmp\\");
		
	}
	
//	@Test
//	public void isStartDirAFile(){
//		assertEquals(1, pdfFile.getPdfs().size());
//	}
	
	@Test
	public void noPdfs() throws IOException{
		BookmarkExtractor e = new BookmarkExtractor("C:");
		assertEquals(0, e.getPdfs().size());
	}
	
	@Test
	public void invalidDir() throws IOException{
		BookmarkExtractor e = new BookmarkExtractor("C:\\dfsdfasdasd");
		assertEquals(0, e.getPdfs().size());
	}
	
	@Test
	public void fixBookmarkListTest() throws IOException{
		BookmarkExtractor e = new BookmarkExtractor("C:\\dfsdfasdasd");
		PageBookmark pb1 = new PageBookmark(1, "S1");
		PageBookmark pb2 = new PageBookmark(2, "S2");
		PageBookmark pb4 = new PageBookmark(4, "S4");
		PageBookmark pb7 = new PageBookmark(7, "S7");
		List<PageBookmark> pbList = new ArrayList<>(4);
		pbList.add(pb1);
		pbList.add(pb2);
		pbList.add(pb4);
		pbList.add(pb7);
		e.printBookmarksToConsole(pbList);
		e.addBookmarksToAllPagesInBookmarkList(pbList, 10);
		e.printBookmarksToConsole(pbList);
	}
	
	
		
	
//	@Test
//	public void getBookmarksTest() throws IOException{
//		Path p = pdfFile.getPdfs().get(0);
//		pdfFile.getBookmarks(p);
//	}
	
	
	
	

}
