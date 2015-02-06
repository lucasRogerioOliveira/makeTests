package comparator;

import java.lang.reflect.Method;
import java.util.Comparator;

import utils.ComparatorUtils;

public class MethodComparator implements Comparator<Method>{

	public int compare(Method m1, Method m2) {
		return ComparatorUtils.compare(m1.getName(),m2.getName());
	}
}
