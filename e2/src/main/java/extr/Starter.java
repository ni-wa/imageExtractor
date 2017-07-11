package extr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Starter {
	public static void main(String[] args) throws IOException {
		Path startDirectory;
		Path resultDirectory;
		
		if (System.getProperty("os.name").contains("Windows")) {
			startDirectory = Paths.get("C:\\Users\\nw\\Documents\\tmp\\input");	
			resultDirectory = Paths.get("C:\\Users\\nw\\Documents\\tmp\\output");
		} else {
			startDirectory = Paths.get("/mnt/c/dev/pdfToJpg/input");
			resultDirectory = Paths.get("/mnt/c/dev/pdfToJpg/output");
		}
		
		DirectoryService ds = new DirectoryService(resultDirectory);
		
		/*
		 * 1 get pdf files as array
		 * 2 for each file get bookmarks as array
		 * 3 extract images for each file
		 */
		
		FileFinder ff = new FileFinder();
		List<Path> pathList = ff.listSourceFiles(startDirectory);
		
		BookmarkExtractor bookmarkExtractor = new BookmarkExtractor();
		ImageExtractor ie = new ImageExtractor(ds);
		
		for (Path path : pathList) {
			System.out.println("PATH=" + path.toString());
			List<PageBookmark> bookmarkList  = new ArrayList<>();
			bookmarkList = bookmarkExtractor.getBookmarks(path);
			bookmarkList.removeIf(Objects::isNull);
			ie.extractImages(path, bookmarkList, ds);
		}
	}
}
