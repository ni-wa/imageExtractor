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
	public static String space(String inputString){
		return inputString.replace(' ', '_');
	}
	public static String capADots(String inputString){
		return inputString.replace('Ä', 'A');
	}
	public static String capACircle(String inputString){
		return inputString.replace('Å', 'A');
	}
	public static String capODots(String inputString){
		return inputString.replace('Ö', 'O');
	}
	public static String aDots(String inputString){
		return inputString.replace('ä', 'a');
	}
	public static String aCircle(String inputString){
		return inputString.replace('å', 'a');
	}
	public static String oDots(String inputString){
		return inputString.replace('ö', 'o');
	}
	public static String capUDots(String inputString){
		return inputString.replace('Ü', 'U');
	}
	public static String uDots(String inputString){
		return inputString.replace('ü', 'u');
	}
	public static String half(String inputString){
		return inputString.replaceAll("½", "half");
	}
	
	public static String multipleUnderscore(String inputString){
		String pattern = "(_+)";
		String r = inputString.replaceAll(pattern, "_");
		System.out.println(r);
		return r;
	}
	
	
	public static String removeBadChars(String inputString){
//		String pattern = "(\\d)(\\s+)([cm]m)";
//		String r = inputString.replaceAll(pattern, "$1$3");
//		System.out.println(r);
		return inputString.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
	}
	
	public String runAllStringFixes(String input){
		String result = removeSpaceBeforeMmAndCm(input);
		result = commaToDotInCalibres(result);
		result = space(result);
		result = capADots(result);
		result = capACircle(result);
		result = capODots(result);
		result = aDots(result);
		result = aCircle(result);
		result = oDots(result);
		result = capUDots(result);
		result = uDots(result);
		result = removeBadChars(result);
		result = multipleUnderscore(result);
		return result;
	}
	
	
}
