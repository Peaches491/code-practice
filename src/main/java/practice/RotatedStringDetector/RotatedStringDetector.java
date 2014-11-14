package practice.RotatedStringDetector;

public class RotatedStringDetector {
	
	private static int isSubstring(String baseString, String substring){
		int idx = baseString.indexOf(substring);
		return idx;
	}
	
	public static boolean isRotation(String baseString, String testString){
		if(baseString == null && testString == null) return true;
		else if(baseString == null || testString == null) return false;
		if(baseString.length() != testString.length()) return false;
		if(baseString.equals(testString)) return true;
		
		int splitPoint = 1;
		
		for(splitPoint = 1; splitPoint < baseString.length(); splitPoint++){
			System.out.println(baseString.substring(0, splitPoint) + ", " + 
							   baseString.substring(splitPoint, baseString.length()));
			
			String lhs = baseString.substring(0, splitPoint);
			String rhs = baseString.substring(splitPoint, baseString.length());
			if(isSubstring(baseString, lhs)>=0 && 
			   isSubstring(baseString, rhs)>=0) {
				return true;
			}
		}
		
		return false;
	}
}
