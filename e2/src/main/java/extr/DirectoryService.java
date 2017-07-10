package extr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.codegen.TypeMap;

public class DirectoryService {

	/*
	 * Set files to correct dir by first char in file name 
	 */
	Map<String,String> ammoType = new HashMap<String, String>();
	String[][] ammotypes = {
			{"handgranat", "Hgr"},
			{"hgr", "Hgr"},
			{"minor", "Mine"},
			{"mina", "Mine"},
			{"robot", "Robot"},
			{"raket", "Rocket"},
			{"zon", "fuze"},
			{"ror", "fuze"},
			{"stid", "fuze"},
			{"tums", "inch"},
			{"pund", "oldDesignation"},
	};

	//    types.put("John", "Taxi Driver");
	//    types.put("Mark", "Professional Killer");

	//	

	Path resultBasePath;
	Path _00_19;
	Path _20_39;
	//	Path _3;
	Path _40_89;
	//	Path _5;
	//	Path _6;
	//	Path _7;
	//	Path _8;
	Path _99_;
	Path AToZ;

	//	Windows
	//	String sysDirSplitter = "\\";

	//	Linux
	String sysDirSplitter = "/";
	String sysBasePath;


	public DirectoryService(Path resultBasePath) throws IOException{
		this.resultBasePath = resultBasePath;
		String resultBasePathString = resultBasePath.toString();
		String sysBasePath = resultBasePathString + sysDirSplitter;

		//		ammoType.put(arg0, arg1)
		//
		//		_00_19 = Paths.get(sysBasePath + "00-19");
		//		_20_39 = Paths.get(sysBasePath + "20-39");
		//		//		_3 = Paths.get(sysBasePath + "3");
		//		_40_89 = Paths.get(sysBasePath + "40-89");
		//		//		_5 = Paths.get(sysBasePath + "5");
		//		//		_6 = Paths.get(sysBasePath + "6");
		//		//		_7 = Paths.get(sysBasePath + "7");
		//		//		_8 = Paths.get(sysBasePath + "8");
		//		_99_ = Paths.get(sysBasePath + "90-");
		AToZ = Paths.get(sysBasePath + "A_To_Z");
		createDirsIfNotExists();
	}

	private  void createDirsIfNotExists() throws IOException{
		List<Path> paths = new ArrayList<>();
		//		paths.add(_1);
		//		paths.add(_2);
		//		paths.add(_3);
		//		paths.add(_4);
		//		paths.add(_5);
		//		paths.add(_6);
		//		paths.add(_7);
		//		paths.add(_8);
		//		paths.add(_9);
		paths.add(AToZ);
		for (Path path : paths) {
			if ( ! path.toFile().exists()) {
				Files.createDirectories(path);
			}
		}
	}

	//	public Path sort(Path fileName){
	//		//		Path fName = fileName.toPath();
	//		switch(fileName.toString().charAt(0)) {
	//		case '1' :
	//			return _1; 
	//		case '2' :
	//			return _2; 
	//		case '3' :
	//			return _3; 
	//		case '4' :
	//			return _4; 
	//		case '5' :
	//			return _5; 
	//		case '6' :
	//			return _6; 
	//		case '7' :
	//			return _7; 
	//		case '8' :
	//			return _8; 
	//		case '9' :
	//			return _9; 
	//		default :
	//			return AToZ; 
	//		}
	//	}

	public Path getDestDir(String fileName){
		String fileNameLower = fileName.toLowerCase();
		String type = "";
		String subType = "";
		String caliber = "";
		if (Character.isDigit(fileName.charAt(0))) {
			caliber = getStartingDigits(fileName);
			if (fileNameLower.contains("tum")) {
				type = "inch";
			} else if (fileNameLower.contains("pund")) {
				type = "pund";
			} else {

				type = "caliber";
				if (caliber.length() > 0) {
					type = type + "/" + caliber; 
				} 
			}
		} else {
			for (String[] arr : ammotypes) {
				if (fileNameLower.contains(arr[0])) {
					type = arr[1];
				}
			}
		}
		Path destDir = Paths.get(resultBasePath.toString() + "/" + type + "/");
		if ( ! Files.exists(destDir)) {
			System.out.println("destDir=" + destDir.toString());
			new File(destDir.toString()).mkdirs();
		}
		return destDir;
	}

	private String getStartingDigits(String fileName) {
		String caliber = "";
		for (int i = 0; i <= 4; i++) {
			if (Character.isDigit(fileName.charAt(i)) ) {
				//				System.out.println("cal=" + caliber + "\tfileName.charAt(i)="+ fileName.charAt(i) + "\t " + Character.isDigit(fileName.charAt(i)));
				//				System.out.println("cal * 10 = "+ caliber*10 + "\t class=" + fileName.substring(i,1).getClass().toString());
				//				System.out.println("filename =" + fileName.toString() + "\ti=" + i);
				//				System.out.println("Integer.parseInt(fileName.substring(i,1)=" + Integer.parseInt(fileName.substring(i,i+1)));
				caliber = caliber + fileName.substring(i,i+1);
			} else if (fileName.charAt(i) == '.') {
				caliber = caliber + "." ;
			}  else if (fileName.substring(i, i +1).contains("c")) {
				caliber = caliber + "cm";
				break;
			}  else if (fileName.substring(i, i +1).contains("m")) {
				caliber = caliber + "mm";
				break;
			}
		}
		return caliber;
	}
}
