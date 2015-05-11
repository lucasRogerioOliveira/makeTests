import java.util.HashSet;
import java.util.Set;



public class Main {
	
	public int numeros[] = new int[10];
	
	public static void main(String[] args) {
		Set<Object> set = new HashSet<Object>();
		set.add(1);
		set.add("string");
		set.add(false);
		set.remove(0); //pelo objeto
		set.contains(1);		
	}	

}
