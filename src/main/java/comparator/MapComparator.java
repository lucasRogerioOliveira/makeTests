package comparator;

import java.util.Comparator;
import java.util.Map;

public class MapComparator implements Comparator<Object>{
	
	Map<?, ?> map;
	
	public int numerosAleatorios[] = {9,2,4,6,5,3,0,7,8,1};
	
	public MapComparator(Map<?, ?> map){
		this.map = map;
	}

	
	public int compare(Object o1, Object o2) {		
		return Integer.compare(o1.hashCode(), o2.hashCode());
	}
}
