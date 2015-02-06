package classes.gear;

public class Gear {
	
	private static int id = 0;
	private Integer numberOfTeeth;
	private Double size;
	private Double sizeFit;
	
	
	public Gear(){
		id++;
	}
	
	@Override
	public int hashCode() {
		return id;
	}	
	
	//Getters and Setters
	
	public Integer getNumberOfTeeth() {
		return numberOfTeeth;
	}
	public void setNumberOfTeeth(Integer numberOfTeeth) {
		this.numberOfTeeth = numberOfTeeth;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public Double getSizeFit() {
		return sizeFit;
	}
	public void setSizeFit(Double sizeFit) {
		this.sizeFit = sizeFit;
	}


}
