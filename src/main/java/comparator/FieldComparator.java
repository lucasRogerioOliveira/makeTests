package comparator;

import java.lang.reflect.Field;
import java.util.Comparator;

public class FieldComparator implements Comparator<Field>{

	public int compare(Field f1, Field f2) {
		return ComparatorUtils.compare(f1.getName(), f2.getName());
	}

}
