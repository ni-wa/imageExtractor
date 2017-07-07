package extr;

public class FileNameService {
	
	
	public static String removeSpaceBeforeMmAndCm(String inputString){
		String pattern = "(\\d)(\\s+)([cm]m)";
		String r = inputString.replaceAll(pattern, "$1$3");
		System.out.println(r);
		return r;
	}
	
	public static String commaToDotInCalibres(String inputString){
		String pattern = "(\\d)(,)(\\d)";
		String r = inputString.replaceAll(pattern, "$1.$3");
		System.out.println(r);
		return r;
	}
	public static String spaceToUnderscore(String inputString){
		return inputString.replace(' ', '_');
	}
	
	public String runAllStringFixes(String input){
		String result = removeSpaceBeforeMmAndCm(input);
		result = commaToDotInCalibres(result);
//		result = spaceToUnderscore(result);
		return result;
	}
	
	
}
