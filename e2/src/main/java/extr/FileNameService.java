package extr;

public class FileNameService {
	
	public static String removeSpaceBeforeMmAndCm(String inputString){
		String pattern = "(\\d)(\\s+)(cm|mm|tum)";
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
		return inputString.replace('k', 'U');
	}
	public static String uDots(String inputString){
		return inputString.replace('k', 'u');
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
		return inputString.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
	}
	
	public String removeDigitDotUnderscore(String inputString){
//		System.out.println(inputString);
		String pattern = "([0-9]\\.)(_)";
		String result = inputString.replaceAll(pattern, "");
//		System.out.println(result);
		return result;
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
//		result = capUDots(result);
//		result = uDots(result);
		result = removeDigitDotUnderscore(result);
		result = removeStartingRomanNumerals(result);
		result = removeDigitOrLetterDotUnderscore(result);
		result = removeBadChars(result);
		result = multipleUnderscore(result);
//		result = cmToMm(result);
		
		return result;
	}
	
	public String removeDigitOrLetterDotUnderscore(String inputString){
		System.out.println(inputString);
		String pattern = "([0-9A-Z]\\.)(_)";
		String result = inputString.replaceAll(pattern, "");
		System.out.println(result);
		return result;
	}
	
	public String removeStartingRomanNumerals(String fileName) {
		int underscorePosition; 
		if (fileName.length() > 5 &&  fileName.indexOf('_') <= 5){
			underscorePosition = fileName.indexOf('_');
			String romanNumeral = fileName.substring(0, underscorePosition);
			switch (romanNumeral) {
			case "I": fileName = fileName.substring(2);
			break;
			case "II": fileName = fileName.substring(3);
			break;
			case "III": fileName = fileName.substring(4);
			break;
			case "IV": fileName = fileName.substring(3);
			break;
			case "V": fileName = fileName.substring(2);
			break;
			case "VI": fileName = fileName.substring(3);
			break;
			case "VII": fileName = fileName.substring(4);
			break;
			case "VIII": fileName = fileName.substring(5);
			break;
			case "IX": fileName = fileName.substring(3);
			break;
			case "X": fileName = fileName.substring(2);
			break;
			case "XI": fileName = fileName.substring(3);
			break;
			case "XII": fileName = fileName.substring(4);
			break;
			case "XIII": fileName = fileName.substring(5);
			break;
			}
		}
		return fileName;
	}
}
