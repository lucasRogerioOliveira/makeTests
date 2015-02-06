package comparator;

import java.util.Comparator;

public class ObjectComparator implements Comparator<Object> {

	public int compare(Object o1, Object o2) {
		return Integer.compare(o1.hashCode(), o2.hashCode());
	}
}
