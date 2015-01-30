package classes.car;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;
import mymap.MyMap;
import sun.misc.Queue;
import classes.bar.Bar;
import classes.foo.Foo;
import classes.gear.Gear;
import enuns.Color;

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
	private List<Object> whatever = new ArrayList<Object>();
	private Pair<Foo, Bar> pair = new Pair<Foo, Bar>(null, null);
	private Queue<Bar> queue = new Queue<Bar>();
	private Map<Foo, Bar> fooBar;
	private Set<Foo> fooSet;
	private MyMap<Foo,Bar,Object,Integer> myMap = new MyMap<Foo,Bar,Object,Integer>();
	
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
	public void setWhatever(List<Object> whatever){
		this.whatever = whatever;
	}
	public List<Object> getWhatever() {
		return whatever;
	}	
	public void setGears(List<Gear> gears) {
		this.gears = gears;
	}
	private Map<Foo, Bar> getFooBar(){
		return fooBar;
	}
	public void setFooBar(Map<Foo, Bar> fooBar){
		this.fooBar = fooBar;
	};
	public Set<Foo> getFooSet(){
		return fooSet;
	};
	public void setFooSet(Set<Foo> fooSet){
		this.fooSet = fooSet; 
	}
	public Pair<Foo, Bar> getPair(){
		return pair; 
	}
	public void setPair(Pair<Foo, Bar> pair){
		this.pair = pair;
	}
	public Queue<Bar> getQueue(){
		return queue;
	}
	public void setQueue(Queue<Bar> queue){
		this.queue = queue;
	}
	public MyMap<Foo, Bar, Object, Integer> getMyMap() {
		return myMap;
	}
	public void setMyMap(MyMap<Foo, Bar, Object, Integer> myMap) {
		this.myMap = myMap;
	}	
}
