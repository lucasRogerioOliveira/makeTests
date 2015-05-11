package classes.car;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import classes.car.Car;
import org.joda.time.DateTimeZone;
import java.util.TimeZone;
import java.util.Date;
import org.joda.time.DateTime;
import classes.gear.Gear;
import classes.foo.Foo;
import java.util.Set;
import classes.genericclass.GenericClass;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Calendar;
import java.util.Map;
import classes.bar.Bar;
import java.util.LinkedList;


public class CarTests {


    private Car car = new Car();

    @Before
    public void beforeTest(){
        //sorry, but a can't get the Locale =(
        Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        car.setCalendar(calendar1);

        classes.car.Car car2 = new classes.car.Car();
        car2.setColor(enuns.Color.Red);
        Date date3 = new Date(10L);
        car2.setDate(date3);
        car2.setFlax(true);

        classes.genericclass.GenericClass<Object> genericclass4 = new classes.genericclass.GenericClass<Object>();
        car2.setGenericClass(genericclass4);


        classes.genericclass.GenericClass<Object> genericclass25 = new classes.genericclass.GenericClass<Object>();
        genericclass25.setName("test");
        genericclass25.setPerformance(2.1);
        car2.setGenericClass2(genericclass25);

        car2.setName("Trem que pula");
        car2.setNumberWeels(999);
        car2.setPrice(33.44);
        classes.car.Car.setSomething("something nice ;)");

        classes.car.Car staticcar6 = new classes.car.Car();
        //sorry, but a can't get the Locale =(
        Calendar calendar7 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        staticcar6.setCalendar(calendar7);

        classes.car.Car car8 = new classes.car.Car();
        car8.setColor(enuns.Color.Red);
        Date date9 = new Date(10L);
        car8.setDate(date9);
        car8.setFlax(true);

        classes.genericclass.GenericClass<Object> genericclass10 = new classes.genericclass.GenericClass<Object>();
        car8.setGenericClass(genericclass10);


        classes.genericclass.GenericClass<Object> genericclass211 = new classes.genericclass.GenericClass<Object>();
        genericclass211.setName("test");
        genericclass211.setPerformance(2.1);
        car8.setGenericClass2(genericclass211);

        car8.setName("Trem que pula");
        car8.setNumberWeels(999);
        car8.setPrice(33.44);
        staticcar6.setCar(car8);

        staticcar6.setColor(enuns.Color.Blue);
        Date date12 = new Date(12542200L);
        staticcar6.setDate(date12);
        staticcar6.setFlax(false);
        Map<classes.foo.Foo,classes.bar.Bar> map13 = new java.util.HashMap<classes.foo.Foo,classes.bar.Bar>();
        classes.foo.Foo foo14 = new classes.foo.Foo();
        classes.bar.Bar bar15 = new classes.bar.Bar();
        Date date16 = new Date(5555245L);
        bar15.setDate(date16);
        bar15.setDescription("zé venancio");
        map13.put(foo14,bar15);
        staticcar6.setFooBar(map13);
        Set<classes.foo.Foo>fooset17 = new java.util.HashSet<classes.foo.Foo>();
        classes.foo.Foo foo18 = new classes.foo.Foo();
        fooset17.add(foo18);
        classes.foo.Foo foo19 = new classes.foo.Foo();
        DateTime dateTime20 = new DateTime(222L, DateTimeZone.forID("America/Sao_Paulo"));
        foo19.setDateTime(dateTime20);
        foo19.setTicarica("curupaaaaco! kk");
        fooset17.add(foo19);
        staticcar6.setFooSet(fooset17);



        classes.gear.Gear gear21 = new classes.gear.Gear();
        gear21.setNumberOfTeeth(10);
        gear21.setSize(1.1);
        gear21.setSizeFit(15.261);
        staticcar6.setGear(gear21);

        List<classes.gear.Gear>gears22 = new java.util.LinkedList<classes.gear.Gear>();
        classes.gear.Gear gear23 = new classes.gear.Gear();
        gear23.setNumberOfTeeth(10);
        gear23.setSize(1.1);
        gear23.setSizeFit(15.261);
        gears22.add(gear23);
        classes.gear.Gear gear24 = new classes.gear.Gear();
        gears22.add(gear24);
        staticcar6.setGears(gears22);


        staticcar6.setName("Carro");
        staticcar6.setNumberWeels(4);
        staticcar6.setPrice(22.11);
        List<java.lang.Object>whatever25 = new java.util.ArrayList<java.lang.Object>();
        whatever25.add("abacate");
        classes.gear.Gear gear27 = new classes.gear.Gear();
        whatever25.add(gear27);
        whatever25.add(42);
        staticcar6.setWhatever(whatever25);


        classes.car.Car.setStaticCar(staticcar6);

        car.setCar(car2);

        car.setColor(enuns.Color.Blue);
        Date date1 = new Date(12542200L);
        car.setDate(date1);
        car.setFlax(false);
        Map<classes.foo.Foo,classes.bar.Bar> map2 = new java.util.HashMap<classes.foo.Foo,classes.bar.Bar>();
        classes.foo.Foo foo3 = new classes.foo.Foo();
        classes.bar.Bar bar4 = new classes.bar.Bar();
        Date date5 = new Date(5555245L);
        bar4.setDate(date5);
        bar4.setDescription("zé venancio");
        map2.put(foo3,bar4);
        car.setFooBar(map2);
        Set<classes.foo.Foo>fooset6 = new java.util.HashSet<classes.foo.Foo>();
        classes.foo.Foo foo7 = new classes.foo.Foo();
        fooset6.add(foo7);
        classes.foo.Foo foo8 = new classes.foo.Foo();
        DateTime dateTime9 = new DateTime(222L, DateTimeZone.forID("America/Sao_Paulo"));
        foo8.setDateTime(dateTime9);
        foo8.setTicarica("curupaaaaco! kk");
        fooset6.add(foo8);
        car.setFooSet(fooset6);



        classes.gear.Gear gear10 = new classes.gear.Gear();
        gear10.setNumberOfTeeth(10);
        gear10.setSize(1.1);
        gear10.setSizeFit(15.261);
        car.setGear(gear10);

        List<classes.gear.Gear>gears11 = new java.util.LinkedList<classes.gear.Gear>();
        classes.gear.Gear gear12 = new classes.gear.Gear();
        gear12.setNumberOfTeeth(10);
        gear12.setSize(1.1);
        gear12.setSizeFit(15.261);
        gears11.add(gear12);
        classes.gear.Gear gear13 = new classes.gear.Gear();
        gears11.add(gear13);
        car.setGears(gears11);


        car.setName("Carro");
        car.setNumberWeels(4);
        car.setPrice(22.11);
        List<java.lang.Object>whatever14 = new java.util.ArrayList<java.lang.Object>();
        whatever14.add("abacate");
        classes.gear.Gear gear16 = new classes.gear.Gear();
        whatever14.add(gear16);
        whatever14.add(42);
        car.setWhatever(whatever14);


    }

    @Test
    public void getCalendarTest(){
        java.util.Calendar actual = car.getCalendar();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getCarTest(){
        classes.car.Car actual = car.getCar();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getColorTest(){
        enuns.Color actual = car.getColor();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getDateTest(){
        java.util.Date actual = car.getDate();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getFlaxTest(){
        java.lang.Boolean actual = car.getFlax();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getFooBarTest(){
        java.util.Map actual = car.getFooBar();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getFooSetTest(){
        java.util.Set actual = car.getFooSet();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getGearTest(){
        classes.gear.Gear actual = car.getGear();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getGearsTest(){
        java.util.List actual = car.getGears();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getGenericClassTest(){
        classes.genericclass.GenericClass actual = car.getGenericClass();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getGenericClass2Test(){
        classes.genericclass.GenericClass actual = car.getGenericClass2();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getNameTest(){
        java.lang.String actual = car.getName();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getNumberWeelsTest(){
        java.lang.Integer actual = car.getNumberWeels();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getPriceTest(){
        java.lang.Double actual = car.getPrice();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getQueueTest(){
        java.util.Queue actual = car.getQueue();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getSomethingTest(){
    }

    @Test
    public void getStaticCarTest(){
    }

    @Test
    public void getWhateverTest(){
        java.util.List actual = car.getWhatever();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void hashCodeTest(){
        int actual = car.hashCode();
        Assert.fail("make your asserts here :)");
    }

}
