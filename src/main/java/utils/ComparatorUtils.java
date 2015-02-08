package utils;


public class ComparatorUtils {
	
	public static int compare(String s1, String s2) {
		int i;
		if(s1.compareTo(s2) == 0){
			for(i = 0; i < s1.length() && compareChar(s1.charAt(i), s2.charAt(i)) == 0; i++);
			return (i == s1.length() ? 0 : i);
		}
		return s1.compareTo(s2) < 0 ? -1 : 1;
	}
	
	public static int compareChar(Character c1, Character c2){
		return c1.compareTo(c2);
	}
}
