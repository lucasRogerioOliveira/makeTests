import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import classes.bar.Bar;
import classes.car.Car;
import classes.foo.Foo;
import classes.gear.Gear;
import classes.genericclass.GenericClass;
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
		barPair.setDate(new Date(1555L));
//		car.setPair(new Pair<Foo,Bar>(fooPair,barPair));
		
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
		bar1.setDescription("zé venancio");
		bar1.setDate(new Date(5555245L));
		
		fooBar.put(new Foo(), new Bar());
		fooBar.put(foo1, bar1);
		car.setFooBar(fooBar);
		
		Set<Foo> fooSet = new HashSet<Foo>();
		
		Foo foo2 = new Foo();
		foo2.setTicarica("curupaaaaco! kk");
		
		foo2.setDateTime(new DateTime(222L, DateTimeZone.forID("America/Sao_Paulo")));
		
		fooSet.add(new Foo());
		fooSet.add(foo2);
		car.setFooSet(fooSet);
		
		Car car2 = new Car();
		car2.setColor(Color.Red);
		car2.setFlax(true);
		car2.setName("Trem que pula");
		car2.setPrice(33.44);
		car2.setNumberWeels(999);
		
		
		GenericClass<List<Integer>> gc = new GenericClass<List<Integer>>();
		gc.setName("test");
		gc.setPerformance(2.1D);
		car2.setGenericClass2(gc);
		car2.setGenericClass(new GenericClass());
		
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
		
		Car.setStaticCar(car);
	}
	
//	@Test
//	public void makeSetters() throws IllegalArgumentException, IllegalAccessException, IOException{
//		String pathStr = MakeTests.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target", "src/test/java");
//		pathStr = pathStr.substring(0,pathStr.indexOf("src/test/java") + 13) + "/tocompare/makeSetters.txt";
//		
//		String actual = MakeTests.makeSetters(car, null);
//		String expected = readFile(pathStr);
//		System.out.println(actual);
//		Assert.assertEquals(expected,actual);
//	}

	@Test
	public void makeSettersWithMethodsFalseTest() throws IllegalArgumentException, IllegalAccessException, IOException {
			String pathStr = MakeTests.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target", "src/test/java");
			pathStr = pathStr.substring(0,pathStr.indexOf("src/test/java") + 13) + "/tocompare/makeSettersWithMethodsFalseCar.txt";
			
			String actual = MakeTests.makeSettersWith(car, false);
			String expected = readFile(pathStr);
			System.out.println(actual);
			Assert.assertEquals(expected,actual);
	}
	
	@Test
	public void makeSettersWithMethodsTrueTest() throws IllegalArgumentException, IllegalAccessException, IOException {
			String pathStr = MakeTests.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target", "src/test/java");
			pathStr = pathStr.substring(0,pathStr.indexOf("src/test/java") + 13) + "/tocompare/makeSettersWithMethodsTrueCar.txt";
			
			String actual = MakeTests.makeSettersWith(car, true);
			String expected = readFile(pathStr);
			System.out.println(actual);			
			Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void generateMethodsTest() throws IOException{
		String pathStr = MakeTests.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target", "src/test/java");
		pathStr = pathStr.substring(0,pathStr.indexOf("src/test/java") + 13) + "/tocompare/generateMethods.txt";
		
		String actual = MakeTests.generateMethods(Arrays.asList(car.getClass().getMethods()), "car");
		String expected = readFile(pathStr);

		Assert.assertEquals(expected,actual);
	}
	
	@Test
	public void isBasicTypesStringTest(){
		Assert.assertTrue(MakeTests.isBasicTypes(String.class)); 		
	}
	
	@Test
	public void isBasicTypesIntegerTest(){
		Assert.assertTrue(MakeTests.isBasicTypes(Integer.class)); 		
	}

	@Test
	public void isBasicTypesBooleanTest(){
		Assert.assertTrue(MakeTests.isBasicTypes(Boolean.class)); 		
	}

	@Test
	public void isBasicTypesDoubleTest(){
		Assert.assertTrue(MakeTests.isBasicTypes(Double.class)); 		
	}

	@Test
	public void isBasicTypesFloatTest(){
		Assert.assertTrue(MakeTests.isBasicTypes(Float.class)); 		
	}
	
	@Test
	public void isBasicTypesIsCharacterTest(){
		Assert.assertTrue(MakeTests.isBasicTypes(Character.class));
	}

	@Test
	public void isBasicTypesIsPrimitiveTest(){
		Assert.assertTrue(MakeTests.isBasicTypes(int.class));
	}
	
	@Test
	public void isBasicTypesFalseTest(){
		Assert.assertFalse(MakeTests.isBasicTypes(Object.class));
	}


	@Test
	public void isValidClassTrueTest(){
		Assert.assertTrue(MakeTests.isValidClass("org.junit.Test"));
	}

	@Test
	public void isValidClassFalseWithDollarTest(){
		Assert.assertFalse(MakeTests.isValidClass("org.junit.Test$HashMap"));
	}	
	
	@Test
	public void isValidClassFalseTest(){
		Assert.assertFalse(MakeTests.isValidClass("ticarica"));
	}
	
	
	@Test
	public void isAlphaStringTest(){
		Assert.assertTrue(MakeTests.isAlpha("ticarica"));
	}
	
	@Test
	public void isAlphaCharacterTest(){
		Assert.assertTrue(MakeTests.isAlpha(new Character('a')));
	}
	
	@Test
	public void isAlphaCharTest(){
		Assert.assertTrue(MakeTests.isAlpha('a'));
	}
	
	@Test
	public void isAlphaFalseTest(){
		Assert.assertFalse(MakeTests.isAlpha(1));
	}
	
	@Test
	public void isDateTimeTest(){
		Assert.assertTrue(MakeTests.isDate(DateTime.class));
	}

	@Test
	public void isDateSqlTest(){
		Assert.assertTrue(MakeTests.isDate(java.sql.Date.class));
	}
	
	@Test
	public void isDateUtilTest(){
		Assert.assertTrue(MakeTests.isDate(java.util.Date.class));
	}
	
	@Test
	public void isDateCalendarTest(){
		Assert.assertTrue(MakeTests.isDate(Calendar.class));
	}
	
	private String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
}
