package classes;

import java.util.LinkedList;
import java.util.List;

import enuns.Color;
import gear.Gear;

public class Car {
	
	private static String something;
	private String name;
	private Integer numberWeels;
	private Boolean flax;
	private Double price;
	private Color color;
	private Car car;
	private Gear gear;
	private List<Gear> gears = new LinkedList<Gear>();
	
	//Getters and Setters...
	
	public static String getSomething() {
		return something;
	}
	public static void setSomething(String Something) {
		something = Something;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public Integer getNumberWeels() {
		return numberWeels;
	}
	public void setNumberWeels(Integer numberWeels) {
		this.numberWeels = numberWeels;
	}
	public Boolean getFlax() {
		return flax;
	}
	public void setFlax(Boolean flax) {
		this.flax = flax;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Gear getGear(){
		return gear;
	}
	public void setGear(Gear gear){
		this.gear = gear;
	}
	public List<Gear> getGears() {
		return gears;
	}
	public void setGears(List<Gear> gears) {
		this.gears = gears;
	}	
	
}
