package extr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.codegen.TypeMap;

public class DirectoryService {

	Path resultBasePath;
	Path AToZ;

	String sysDirSplitter;
	String sysBasePath;

	/*
	 * Set files to correct dir by first char in file name 
	 */
	String[][] ammotypes = {
			{"amregister", "Anv_Best_etc"},
			{"anvisningar", "Anv_Best_etc"},
			{"bestammelser", "Anv_Best_etc"},
			{"burkladdn", "laddningar"},
			{"dagligen", "Anv_Best_etc"},
			{"sprangmedel", "laddningar"},
			{"foreskrift", "Anv_Best_etc"},
			{"forkortningar", "Abbrevations"},
			{"forteckningar", "Forteckningar"},
			{"handgranat", "Hgr"},
			{"hgr", "Hgr"},
			{"handgr", "Hgr"},
			{"inledning", "Anv_Best_etc"},
			{"innehall", "Anv_Best_etc"},
			{"malning", "Malning_Markning"},
			{"minor", "Mine"},
			{"mina", "Mine"},
			{"mine", "Mine"},
			{"minf", "Mine"},
			{"minp", "Mine"},
			{"minroj", "Mine"},
			{"minutr", "Mine"},
			{"narlys", "narlys"},
			{"nodsignal", "nodsignal"},
			{"overbefalhavaren", "medd_fran_ob"},

			{"paketladdning", "laddningar"},
			{"pund", "oldDesignation"},
			{"pansarskott", "LAW"},
			{"pskott", "LAW"},

			{"rattelser", "Anv_Best_etc"},
			{"rb_", "Robot"},
			{"robot", "Robot"},
			{"rsv", "RSV"},


			{"raket", "Rocket"},
			{"spetsplugg", "Spetsplugg"},

			{"stubin", "stubin"},
			{"tabell", "tabell"},
			{"tandh", "primer"},
			{"tandskruv", "tandskruv"},
			{"tandskruf", "tandskruv"},
			{"tskr", "tandskruv"},
			{"tums", "inch"},

			{"lyspatron", "signalCtg"},
			{"lyspistol", "signalCtg"},
			{"signalam", "signalCtg"},
			{"signalpatron", "signalCtg"},

			// Fuzes
			{"_sar", "fuze"},
			{"bar_", "fuze"},
			{"bpr_", "fuze"},
			{"btr_", "fuze"},
			{"dblr", "fuze"},
			{"f_bar", "fuze"},
			{"f_k_sar", "fuze"},
			{"f_sar", "fuze"},
			{"fbar", "fuze"},			
			{"fk_sar", "fuze"},
			{"hk_dblr", "fuze"},
			{"hk_dblr", "fuze"},
			{"hk_sar", "fuze"},
			{"li_", "fuze"}, // LIAB
			{"ror", "fuze"},
			{"sar_", "fuze"},
			{"stid", "fuze"},
			{"tidror", "fuze"},
			{"zon", "fuze"}
	};

	public DirectoryService(Path resultBasePath) throws IOException{
		this.resultBasePath = resultBasePath;
		String resultBasePathString = resultBasePath.toString();
		String sysBasePath = resultBasePathString + sysDirSplitter;

		AToZ = Paths.get(sysBasePath + "A_To_Z");
		createDirsIfNotExists();
		if (System.getProperty("os.name").contains("Windows")) {
			this.sysDirSplitter = "\\";	
		} else {
			this.sysDirSplitter = "/";
		}
	}

	private  void createDirsIfNotExists() throws IOException{
		List<Path> paths = new ArrayList<>();
		paths.add(AToZ);
		for (Path path : paths) {
			if ( ! path.toFile().exists()) {
				Files.createDirectories(path);
			}
		}
	}

	public Path getDestDir(String fileName){
		String fileNameLower = fileName.toLowerCase();
		String type = "";
		String subType = "";
		String caliber = ""; // i e 10.5cm, 5.56mm, 8mm, 8cm etc
		Path destDir;

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
					break;
				}
			} 
		}
		if (type.length() == 0) {
			type = fileNameLower.toUpperCase().substring(0, 1) + sysDirSplitter;
		}
		destDir = Paths.get(resultBasePath.toString() + sysDirSplitter + type + sysDirSplitter);
		if ( ! Files.exists(destDir)) {
			System.out.println("destDir=" + destDir.toString());
			new File(destDir.toString()).mkdirs();
		}
		return destDir;
	}

	private String getStartingDigits(String fileName) {
		String caliber = "";
		if (fileName.charAt(0) == '_') {
			fileName = fileName.substring(1);
		}
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

	public String changeKnownCmCalibersToMm(String caliber){
		String[][] caliberChanges = {
				{"7.5cm", "75mm"},
				{"13.5cm", "135mm"},
				{"14.5cm", "245mm"},
				{"30.5cm", "305mm"},
				{"cm", "mm"},
				{"cm", "mm"}};
		for (String[] cal : caliberChanges) {
			if (cal[0].equals(caliber)) {
				return cal[1];
			}
		}
		return caliber;
	}
}

