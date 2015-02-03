package comparator;

import java.lang.reflect.Method;
import java.util.Comparator;

//TODO OPTIMIZE THIS CODE....
public class MethodComparator implements Comparator<Method>{

	public int compare(Method o1, Method o2) {
		if (o1.getName().toLowerCase().length() < o2.getName().length()){
			return -1;
		}
		if (o1.getName().length() > o2.getName().length()){
			return 1;
		}
		char[] chars1  = o1.getName().toCharArray();
		char[] chars2  = o2.getName().toCharArray();
		for(int i = 0; i < chars1.length; i++){
			if  (chars1[i] < chars2[i]){
				return -1;
			}
			if (chars1[i] > chars2[i]){
				return 1;
			}
		}
		return 0;
	}
}
