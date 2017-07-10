package extr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

public class FileFinder {

	public List<Path> listSourceFiles(Path dir) throws IOException {
		final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(null, "pdf"); // whatever other extensions you want);
		List<Path> dirResult = new ArrayList<>();
		final File sourceDirectory = new File(dir.toString());
		
		for (final File child : sourceDirectory.listFiles()) {
			if (child.isFile() && extensionFilter.accept(child)) {
				dirResult.add(child.toPath());
				System.out.println("child = " + child.toPath().toString());
			}
		}
		return dirResult;
	}

	public boolean isPdf(String fileName) {
		String fileEnding = fileName.substring((int) fileName.length() - 3);
		if ("pdf".equals(fileEnding.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
}
