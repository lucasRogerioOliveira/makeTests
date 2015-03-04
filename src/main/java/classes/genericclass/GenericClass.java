package classes.genericclass;

public class GenericClass<T> {
	
	private static int id;
	private String name;
	private Double performance;
	
	public GenericClass(){
		id++;
	}
	
	@Override
	public int hashCode(){
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPerformance() {
		return performance;
	}
	public void setPerformance(Double performance) {
		this.performance = performance;
	}
	
}
