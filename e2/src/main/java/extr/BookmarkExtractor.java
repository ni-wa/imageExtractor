package extr;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class BookmarkExtractor {

	Path rootPath;
	List<Path> pdfPathList = new ArrayList<>();
	PDDocument document;
	PDPageTree pageTree;
//	List<PageBookmark> pageBookmarks = new ArrayList<>();

	public BookmarkExtractor() {
	}

	public BookmarkExtractor(String string) {
		rootPath = Paths.get(string);
	}

	// help method during testing
	public void pdfsToString() {
		// System.out.println("\n\npdfs size = " + pdfs.size());
		for (Path path : pdfPathList) {
			// System.out.println(path.toString());
		}
	}

	public List<Path> getPdfs() {
		return pdfPathList;
	}

	public File getFile() {
		return rootPath.toFile();
	}

	public List<PageBookmark> getBookmarks(Path path) throws InvalidPasswordException, IOException {
		// System.out.println(path.toFile().toString());
		List<PageBookmark> pageBookmarks = new ArrayList<>();
		document = PDDocument.load(path.toFile());
		pageTree = document.getDocumentCatalog().getPages();
		PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();
		pageBookmarks = printBookmark(pageBookmarks, outline, "");
		addBookmarksToAllPagesInBookmarkList(pageBookmarks, document.getNumberOfPages());
		document.close();
		return pageBookmarks;
	}

	public List<PageBookmark> printBookmark(List<PageBookmark> pageBookmarks, PDOutlineNode bookmark, String indentation) throws IOException {
		PDOutlineItem current = bookmark.getFirstChild();
		int pageNumber = -1;
		String bookmarkTitle = "";

		while (current != null) {
			PDPage currentPage = current.findDestinationPage(document);
			if (currentPage != null) {
				pageNumber = pageTree.indexOf(currentPage) + 1;
				bookmarkTitle = current.getTitle();
				// System.out.println("Page:" + pageNumber + "\t" +
				// bookmarkTitle);
				if (pageBookmarks.size() > 0 && pageNumber == pageBookmarks.get(pageBookmarks.size() - 1).getPage()) {
					pageBookmarks.get(pageBookmarks.size() - 1).setBookmark(bookmarkTitle);
				} else {
					PageBookmark pBookmarkTewst = new PageBookmark(7, "swdfsdf");
					PageBookmark pBookmark = new PageBookmark(pageNumber, bookmarkTitle);
					pageBookmarks.add(pBookmark);
				}
				printBookmark(pageBookmarks, current, indentation + "    ");
			}
			current = current.getNextSibling();
		}
		return pageBookmarks;
	}

	public void printBookmarksToConsole(List<PageBookmark> pb) {
		System.out.println(pb.toString());
	}

	public List<PageBookmark> addBookmarksToAllPagesInBookmarkList(List<PageBookmark> pageBookmarks, int noOfPages) {
		// Fill list so every page has a bookmark text
		int currPage;
		pageBookmarks.removeIf(Objects::isNull);
		for (int i = 0; i < noOfPages; i++) {
			if (i >= pageBookmarks.size()) {
				PageBookmark pg = new PageBookmark(i + 1, pageBookmarks.get(i - 1).getBookmark());
				pageBookmarks.add(i, pg);
			} else {
				currPage = pageBookmarks.get(i).getPage();
				if (i < pageBookmarks.size() - 1 && pageBookmarks.get(i + 1).getPage() > (currPage + 1)) {
					PageBookmark pg = new PageBookmark(currPage + 1, pageBookmarks.get(i).getBookmark());
					pageBookmarks.add(i + 1, pg);
				}
			}
		}
		return pageBookmarks;
	}

	public void iterateOverFiles() throws InvalidPasswordException, IOException {
		for (Path pathToFile : pdfPathList) {
			System.out.println("Starting with: " + pathToFile.toString());
			getBookmarks(pathToFile);
			System.out.println("Done with: " + pathToFile.toString() + "\n\n");
		}
	}
}
