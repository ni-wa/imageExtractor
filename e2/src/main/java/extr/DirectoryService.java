package extr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryService {

	/*
	 * Set files to correct dir by first char in file name 
	 */

	Path resultBasePath;
	Path _1;
	Path _2;
	Path _3;
	Path _4;
	Path _5;
	Path _6;
	Path _7;
	Path _8;
	Path _9;
	Path AToZ;
	String sysDirSplitter = "\\";
	String sysBasePath;


	public DirectoryService(Path resultBasePath) throws IOException{
		this.resultBasePath = resultBasePath;
		String resultBasePathString = resultBasePath.toString();
		String sysBasePath = resultBasePathString + sysDirSplitter;
		_1 = Paths.get(sysBasePath + "1");
		_2 = Paths.get(sysBasePath + "2");
		_3 = Paths.get(sysBasePath + "3");
		_4 = Paths.get(sysBasePath + "4");
		_5 = Paths.get(sysBasePath + "5");
		_6 = Paths.get(sysBasePath + "6");
		_7 = Paths.get(sysBasePath + "7");
		_8 = Paths.get(sysBasePath + "8");
		_9 = Paths.get(sysBasePath + "9");
		AToZ = Paths.get(sysBasePath + "A_To_Z");
		createDirsIfNotExists();
	}

	private  void createDirsIfNotExists() throws IOException{
		List<Path> paths = new ArrayList<>();
		paths.add(_1);
		paths.add(_2);
		paths.add(_3);
		paths.add(_4);
		paths.add(_5);
		paths.add(_6);
		paths.add(_7);
		paths.add(_8);
		paths.add(_9);
		paths.add(AToZ);
		for (Path path : paths) {
			if ( ! path.toFile().exists()) {
				Files.createDirectories(path);
			}
		}
	}

	public Path sort(Path fileName){
//		Path fName = fileName.toPath();
		switch(fileName.toString().charAt(0)) {
		case '1' :
			return _1; 
		case '2' :
			return _2; 
		case '3' :
			return _3; 
		case '4' :
			return _4; 
		case '5' :
			return _5; 
		case '6' :
			return _6; 
		case '7' :
			return _7; 
		case '8' :
			return _8; 
		case '9' :
			return _9; 
		default :
			return AToZ; 

		}
	}
}
