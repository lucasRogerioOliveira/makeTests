import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.Car;
import enuns.Color;
import gear.Gear;

public class MakeTestsTests {
	
	public static Car car = new Car();
	
	@BeforeClass
	public static void init(){
		car.setColor(Color.Blue);
		car.setFlax(false);
		car.setName("Carro");
		car.setPrice(22.11);
		car.setNumberWeels(4);
		
		Car car2 = new Car();
		car2.setColor(Color.Red);
		car2.setFlax(true);
		car2.setName("Trem que pula");
		car2.setPrice(33.44);
		car2.setNumberWeels(999);
		
		car.setCar(car2);
		
		Gear gear = new Gear();
		gear.setNumberOfTeeth(20);
		gear.setSize(2.2);
		gear.setSizeFit(30.551);
		car.setGear(gear);
		
		
		List<Gear> gears = new LinkedList<Gear>();		
		Gear gear2 = new Gear();
		gear.setNumberOfTeeth(10);
		gear.setSize(1.1);
		gear.setSizeFit(15.261);
		gears.add(gear);
		gears.add(gear2);
		car.setGears(gears);
		
		Car.setSomething("something nice ;)");
	}
	
	@Test
	public void makeSetters(){
		MakeTests.makeSetters(car, null);	
	}

	@Test
	public void makeAllTestsWithTest() {
		try {
			MakeTests.makeAllTestsWith(car);
//			MakeTests.makeSettersWichJson(car,"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void makeTestsWithPowerMock(){
		
	}
	
	@Test
	public void makeTestsWithMockito(){
		
	}
	
}
