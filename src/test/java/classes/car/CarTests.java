package classes.car;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import classes.car.Car;
import javafx.util.Pair;
import java.util.Date;
import java.util.TimeZone;
import classes.gear.Gear;
import classes.foo.Foo;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Calendar;
import java.util.Map;
import java.util.LinkedList;
import classes.bar.Bar;


public class CarTests {


    private Car car = new Car();

    @Before
    public void beforeTest(){
        car.setSomething("something nice ;)");
        car.setName("Carro");
        car.setNumberWeels(4);
        car.setFlax(false);
        car.setPrice(22.11);
        car.setColor(enuns.Color.Blue);

        classes.car.Car car1 = new classes.car.Car();
        car1.setSomething("something nice ;)");
        car1.setName("Trem que pula");
        car1.setNumberWeels(999);
        car1.setFlax(true);
        car1.setPrice(33.44);
        car1.setColor(enuns.Color.Red);
        Date date2 = new Date(1422910532296L);
        car1.setDate(date2);
        car.setCar(car1);


        classes.gear.Gear gear4 = new classes.gear.Gear();
        gear4.setNumberOfTeeth(10);
        gear4.setSize(1.1);
        gear4.setSizeFit(15.261);
        car.setGear(gear4);

        List<classes.gear.Gear>gears5 = new ArrayList<classes.gear.Gear>();
        classes.gear.Gear gear6 = new classes.gear.Gear();
        gear6.setNumberOfTeeth(10);
        gear6.setSize(1.1);
        gear6.setSizeFit(15.261);
        gears5.add(gear6);
        classes.gear.Gear gear7 = new classes.gear.Gear();
        gears5.add(gear7);
        car.setGears(gears5);


        List<java.lang.Object>whatever8 = new ArrayList<java.lang.Object>();
        classes.gear.Gear gear9 = new classes.gear.Gear();
        whatever8.add(gear9);
        whatever8.add("abacate");
        whatever8.add(42);
        car.setWhatever(whatever8);


        Date date12 = new Date(12542200L);
        car.setDate(date12);
        //sorry, but a can't get the Locale =(
        Calendar calendar13 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        car.setCalendar(calendar13);
        classes.foo.Foo foo15 = new classes.foo.Foo();
        foo15.setTicarica("it's be end?");
        classes.bar.Bar bar16 = new classes.bar.Bar();
        bar16.setId(22);
        Date date17 = new Date(1422910532295L);
        bar16.setDate(date17);
        Pair<classes.foo.Foo,classes.bar.Bar> Pair14 = new Pair<classes.foo.Foo,classes.bar.Bar>(foo15,bar16);
        car.setPair(Pair14);
        Map<classes.foo.Foo,classes.bar.Bar> map18 = new java.util.HashMap<classes.foo.Foo,classes.bar.Bar>();
        classes.foo.Foo foo19 = new classes.foo.Foo();
        classes.bar.Bar bar20 = new classes.bar.Bar();
        bar20.setId(0);
        map18.put(foo19,bar20);
        classes.foo.Foo foo21 = new classes.foo.Foo();
        foo21.setTicarica("ticaricatica");
        classes.bar.Bar bar22 = new classes.bar.Bar();
        bar22.setId(1);
        bar22.setDescription("zé venancio");
        Date date23 = new Date(5555245L);
        bar22.setDate(date23);
        map18.put(foo21,bar22);
        car.setFooBar(map18);
        List<classes.foo.Foo>fooset24 = new ArrayList<classes.foo.Foo>();
        classes.foo.Foo foo25 = new classes.foo.Foo();
        foo25.setTicarica("curupaaaaco! kk");
        fooset24.add(foo25);
        classes.foo.Foo foo26 = new classes.foo.Foo();
        fooset24.add(foo26);
        car.setFooSet(fooset24);


    }

    @Test
    public void getNameTest(){
        java.lang.String actual = car.getName();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getDateTest(){
        java.util.Date actual = car.getDate();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getSomethingTest(){
    }

    @Test
    public void getNumberWeelsTest(){
        java.lang.Integer actual = car.getNumberWeels();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getFlaxTest(){
        java.lang.Boolean actual = car.getFlax();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getPriceTest(){
        java.lang.Double actual = car.getPrice();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getColorTest(){
        enuns.Color actual = car.getColor();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getWhateverTest(){
        java.util.List actual = car.getWhatever();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getGearsTest(){
        java.util.List actual = car.getGears();
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
    public void getCalendarTest(){
        java.util.Calendar actual = car.getCalendar();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getPairTest(){
        javafx.util.Pair actual = car.getPair();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getQueueTest(){
        java.util.Queue actual = car.getQueue();
        Assert.fail("make your asserts here :)");
    }

    @Test
    public void getCarTest(){
        classes.car.Car actual = car.getCar();
        Assert.fail("make your asserts here :)");
    }

}
