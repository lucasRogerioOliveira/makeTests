package classes.car;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import classes.car.Car;
import javafx.util.Pair;
import org.joda.time.DateTimeZone;
import java.util.TimeZone;
import java.util.Date;
import org.joda.time.DateTime;
import classes.gear.Gear;
import classes.foo.Foo;
import java.util.Set;
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
        car2.setName("Trem que pula");
        car2.setNumberWeels(999);
        car2.setPrice(33.44);
        car2.setSomething("something nice ;)");
        car.setCar(car2);

        car.setColor(enuns.Color.Blue);
        Date date5 = new Date(12542200L);
        car.setDate(date5);
        car.setFlax(false);
        Map<classes.foo.Foo,classes.bar.Bar> map6 = new java.util.HashMap<classes.foo.Foo,classes.bar.Bar>();
        classes.foo.Foo foo7 = new classes.foo.Foo();
        classes.bar.Bar bar8 = new classes.bar.Bar();
        Date date9 = new Date(5555245L);
        bar8.setDate(date9);
        bar8.setDescription("zé venancio");
        map6.put(foo7,bar8);
        car.setFooBar(map6);
        Set<classes.foo.Foo>fooset10 = new java.util.HashSet<classes.foo.Foo>();
        classes.foo.Foo foo11 = new classes.foo.Foo();
        fooset10.add(foo11);
        classes.foo.Foo foo12 = new classes.foo.Foo();
        DateTime dateTime13 = new DateTime(222L, DateTimeZone.forID("America/Sao_Paulo"));
        foo12.setDateTime(dateTime13);
        foo12.setTicarica("curupaaaaco! kk");
        fooset10.add(foo12);
        car.setFooSet(fooset10);



        classes.gear.Gear gear14 = new classes.gear.Gear();
        gear14.setNumberOfTeeth(10);
        gear14.setSize(1.1);
        gear14.setSizeFit(15.261);
        car.setGear(gear14);

        List<classes.gear.Gear>gears15 = new java.util.LinkedList<classes.gear.Gear>();
        classes.gear.Gear gear16 = new classes.gear.Gear();
        gear16.setNumberOfTeeth(10);
        gear16.setSize(1.1);
        gear16.setSizeFit(15.261);
        gears15.add(gear16);
        classes.gear.Gear gear17 = new classes.gear.Gear();
        gears15.add(gear17);
        car.setGears(gears15);


        car.setName("Carro");
        car.setNumberWeels(4);
        classes.foo.Foo foo19 = new classes.foo.Foo();
        foo19.setTicarica("it's be end?");
        classes.bar.Bar bar20 = new classes.bar.Bar();
        Date date21 = new Date(1555L);
        bar20.setDate(date21);
        Pair<classes.foo.Foo,classes.bar.Bar> Pair18 = new Pair<classes.foo.Foo,classes.bar.Bar>(foo19,bar20);
        car.setPair(Pair18);
        car.setPrice(22.11);
        car.setSomething("something nice ;)");
        List<java.lang.Object>whatever22 = new java.util.ArrayList<java.lang.Object>();
        whatever22.add("abacate");
        classes.gear.Gear gear24 = new classes.gear.Gear();
        whatever22.add(gear24);
        whatever22.add(42);
        car.setWhatever(whatever22);


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
    public void getPairTest(){
        javafx.util.Pair actual = car.getPair();
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
