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
		
		Path startDirectory = Paths.get("C:\\Users\\nw\\Documents\\tmp\\");
		
		/*
		 * 1 get pdf files as array
		 * 2 for each file get bookmarks as array
		 * 3 extract images for each file
		 */
		
		FileFinder ff = new FileFinder();
		List<Path> pathList = ff.listSourceFiles(startDirectory);
		
		BookmarkExtractor bookmarkExtractor = new BookmarkExtractor();
		ImageExtractor ie = new ImageExtractor();
		
		for (Path path : pathList) {
			List<PageBookmark> bookmarkList  = new ArrayList<>();
			bookmarkList = bookmarkExtractor.getBookmarks(path);
			bookmarkList.removeIf(Objects::isNull);
			ie.extractImages(path, bookmarkList);
		}
		
		
		
		
		
	}
}
