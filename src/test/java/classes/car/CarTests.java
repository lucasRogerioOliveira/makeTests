package classes.car;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import classes.car.Car;
import javafx.util.Pair;
import java.util.TimeZone;
import java.util.Date;
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
        bar8.setDescription("z� venancio");
        map6.put(foo7,bar8);
        car.setFooBar(map6);
        Set<classes.foo.Foo>fooset10 = new java.util.HashSet<classes.foo.Foo>();
        classes.foo.Foo foo11 = new classes.foo.Foo();
        fooset10.add(foo11);
        classes.foo.Foo foo12 = new classes.foo.Foo();
        foo12.setTicarica("curupaaaaco! kk");
        fooset10.add(foo12);
        car.setFooSet(fooset10);



        classes.gear.Gear gear13 = new classes.gear.Gear();
        gear13.setNumberOfTeeth(10);
        gear13.setSize(1.1);
        gear13.setSizeFit(15.261);
        car.setGear(gear13);

        List<classes.gear.Gear>gears14 = new java.util.LinkedList<classes.gear.Gear>();
        classes.gear.Gear gear15 = new classes.gear.Gear();
        gear15.setNumberOfTeeth(10);
        gear15.setSize(1.1);
        gear15.setSizeFit(15.261);
        gears14.add(gear15);
        classes.gear.Gear gear16 = new classes.gear.Gear();
        gears14.add(gear16);
        car.setGears(gears14);


        car.setName("Carro");
        car.setNumberWeels(4);
        classes.foo.Foo foo18 = new classes.foo.Foo();
        foo18.setTicarica("it's be end?");
        classes.bar.Bar bar19 = new classes.bar.Bar();
        Date date20 = new Date(1555L);
        bar19.setDate(date20);
        Pair<classes.foo.Foo,classes.bar.Bar> Pair17 = new Pair<classes.foo.Foo,classes.bar.Bar>(foo18,bar19);
        car.setPair(Pair17);
        car.setPrice(22.11);
        car.setSomething("something nice ;)");
        List<java.lang.Object>whatever21 = new java.util.ArrayList<java.lang.Object>();
        whatever21.add("abacate");
        classes.gear.Gear gear23 = new classes.gear.Gear();
        whatever21.add(gear23);
        whatever21.add(42);
        car.setWhatever(whatever21);


    }

}
