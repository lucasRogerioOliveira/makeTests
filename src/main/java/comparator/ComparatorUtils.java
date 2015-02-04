package comparator;


public class ComparatorUtils {
	
	public static int compare(String s1, String s2) {
		if(s1.compareTo(s2) == 0){
			for(int i = 0; i < s1.length() && compareChar(s1.charAt(i), s2.charAt(i)) == 0; i++);
			return 0;
		}
		return s1.compareTo(s2) < 0 ? -1 : 1;
	}
	
	public static int compareChar(char c1, char c2){
		return (c1 < c2 ? -1 : (c1 > c2 ? 1 : 0));
	}
	
}
