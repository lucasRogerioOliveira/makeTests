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

        classes.car.Car car1 = new classes.car.Car();
        Date date2 = new Date(10L);
        car1.setDate(date2);
        car1.setFlax(true);
        car1.setName("Trem que pula");
        car1.setColor(enuns.Color.Red);
        car1.setPrice(33.44);
        car1.setSomething("something nice ;)");
        car1.setNumberWeels(999);
        car.setCar(car1);

        Date date4 = new Date(12542200L);
        car.setDate(date4);
        car.setFlax(false);

        classes.gear.Gear gear5 = new classes.gear.Gear();
        gear5.setSize(1.1);
        gear5.setSizeFit(15.261);
        gear5.setNumberOfTeeth(10);
        car.setGear(gear5);

        car.setName("Carro");
        classes.foo.Foo foo7 = new classes.foo.Foo();
        foo7.setTicarica("it's be end?");
        classes.bar.Bar bar8 = new classes.bar.Bar();
        bar8.setId(22);
        Date date9 = new Date(1555L);
        bar8.setDate(date9);
        Pair<classes.foo.Foo,classes.bar.Bar> Pair6 = new Pair<classes.foo.Foo,classes.bar.Bar>(foo7,bar8);
        car.setPair(Pair6);
        car.setColor(enuns.Color.Blue);
        List<classes.gear.Gear>gears10 = new java.util.LinkedList<classes.gear.Gear>();
        classes.gear.Gear gear11 = new classes.gear.Gear();
        gear11.setSize(1.1);
        gear11.setSizeFit(15.261);
        gear11.setNumberOfTeeth(10);
        gears10.add(gear11);
        classes.gear.Gear gear12 = new classes.gear.Gear();
        gears10.add(gear12);
        car.setGears(gears10);


        car.setPrice(22.11);
        Map<classes.foo.Foo,classes.bar.Bar> map13 = new java.util.HashMap<classes.foo.Foo,classes.bar.Bar>();
        classes.foo.Foo foo14 = new classes.foo.Foo();
        foo14.setTicarica("ticaricatica");
        classes.bar.Bar bar15 = new classes.bar.Bar();
        bar15.setId(1);
        Date date16 = new Date(5555245L);
        bar15.setDate(date16);
        bar15.setDescription("z� venancio");
        map13.put(foo14,bar15);
        classes.foo.Foo foo17 = new classes.foo.Foo();
        classes.bar.Bar bar18 = new classes.bar.Bar();
        bar18.setId(0);
        map13.put(foo17,bar18);
        car.setFooBar(map13);
        Set<classes.foo.Foo>fooset19 = new java.util.HashSet<classes.foo.Foo>();
        classes.foo.Foo foo20 = new classes.foo.Foo();
        foo20.setTicarica("curupaaaaco! kk");
        fooset19.add(foo20);
        classes.foo.Foo foo21 = new classes.foo.Foo();
        fooset19.add(foo21);
        car.setFooSet(fooset19);


        //sorry, but a can't get the Locale =(
        Calendar calendar22 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        car.setCalendar(calendar22);
        List<java.lang.Object>whatever23 = new java.util.ArrayList<java.lang.Object>();
        classes.gear.Gear gear24 = new classes.gear.Gear();
        whatever23.add(gear24);
        whatever23.add("abacate");
        whatever23.add(42);
        car.setWhatever(whatever23);


        car.setSomething("something nice ;)");
        car.setNumberWeels(4);
    }

}
