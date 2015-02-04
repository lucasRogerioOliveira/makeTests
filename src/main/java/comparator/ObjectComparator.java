package comparator;

import java.util.Comparator;

public class ObjectComparator implements Comparator<Object> {

	public int compare(Object o1, Object o2) {
		return ComparatorUtils.compare(o1.getClass().getName(), o2.getClass().getName());
	}

}
