import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javafx.util.Pair;

import org.junit.BeforeClass;
import org.junit.Test;

import classes.bar.Bar;
import classes.car.Car;
import classes.foo.Foo;
import classes.gear.Gear;
import enuns.Color;

public class MakeTestsTests {
	
	public static Car car = new Car();
	
	@BeforeClass
	public static void init(){
		car.setColor(Color.Blue);
		car.setFlax(false);
		car.setName("Carro");
		car.setPrice(22.11);
		car.setNumberWeels(4);
		car.setDate(new Date(12542200L));
		
		Foo fooPair = new Foo();
		fooPair.setTicarica("it's be end?");
		Bar barPair = new Bar();
		barPair.setDate(new Date());
		barPair.setId(22);
		car.setPair(new Pair<Foo,Bar>(fooPair,barPair));
		
		Locale locale = new Locale("pt", "BR");
		TimeZone tz = TimeZone.getTimeZone("GMT");
		Calendar calendar = Calendar.getInstance(tz, locale);
		calendar.setTimeInMillis(458755000L);		
		car.setCalendar(calendar);
		
		List<Object> listObject = new ArrayList<Object>();
		
		listObject.add(new Gear());
		listObject.add(new String("abacate"));
		listObject.add(new Integer(42));
		car.setWhatever(listObject);
		
		Map<Foo,Bar> fooBar = new HashMap<Foo, Bar>();
		Foo foo1 = new Foo();
		foo1.setTicarica("ticaricatica");
		Bar bar1 = new Bar();
		bar1.setId(1);
		bar1.setDescription("zé venancio");
		bar1.setDate(new Date(5555245L));
		
		fooBar.put(new Foo(), new Bar());
		fooBar.put(foo1, bar1);
		car.setFooBar(fooBar);
		
		Set<Foo> fooSet = new HashSet<Foo>();
		
		Foo foo2 = new Foo();
		foo2.setTicarica("curupaaaaco! kk");
		
		fooSet.add(new Foo());
		fooSet.add(foo2);
		car.setFooSet(fooSet);
		
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
	
//	@Test
//	public void makeSetters() throws IllegalArgumentException, IllegalAccessException{
//		MakeTests.makeSetters(car, null);	
//	}

	@Test
	public void makeAllTestsWithTest() {
		try {
			MakeTests.makeAllTestsWith(car);
//			MakeTests.makeSettersWichJson(car,"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void makeTestsWithPowerMock(){
//		
//	}
//	
//	@Test
//	public void makeTestsWithMockito(){
//		
//	}
	
}
