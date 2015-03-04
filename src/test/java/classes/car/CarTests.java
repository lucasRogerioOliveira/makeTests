package classes.car;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javafx.util.Pair;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;


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

        classes.car.Car staticcar7 = new classes.car.Car();
        //sorry, but a can't get the Locale =(
        Calendar calendar8 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        staticcar7.setCalendar(calendar8);

        classes.car.Car car9 = new classes.car.Car();
        car9.setColor(enuns.Color.Red);
        Date date10 = new Date(10L);
        car9.setDate(date10);
        car9.setFlax(true);

        classes.genericclass.GenericClass<Object> genericclass11 = new classes.genericclass.GenericClass<Object>();
        car9.setGenericClass(genericclass11);


        classes.genericclass.GenericClass<Object> genericclass212 = new classes.genericclass.GenericClass<Object>();
        genericclass212.setName("test");
        genericclass212.setPerformance(2.1);
        car9.setGenericClass2(genericclass212);

        car9.setName("Trem que pula");
        car9.setNumberWeels(999);
        car9.setPrice(33.44);
        staticcar7.setCar(car9);

        staticcar7.setColor(enuns.Color.Blue);
        Date date14 = new Date(12542200L);
        staticcar7.setDate(date14);
        staticcar7.setFlax(false);
        Map<classes.foo.Foo,classes.bar.Bar> map15 = new java.util.HashMap<classes.foo.Foo,classes.bar.Bar>();
        classes.foo.Foo foo16 = new classes.foo.Foo();
        classes.bar.Bar bar17 = new classes.bar.Bar();
        Date date18 = new Date(5555245L);
        bar17.setDate(date18);
        bar17.setDescription("z� venancio");
        map15.put(foo16,bar17);
        staticcar7.setFooBar(map15);
        Set<classes.foo.Foo>fooset19 = new java.util.HashSet<classes.foo.Foo>();
        classes.foo.Foo foo20 = new classes.foo.Foo();
        fooset19.add(foo20);
        classes.foo.Foo foo21 = new classes.foo.Foo();
        DateTime dateTime22 = new DateTime(222L, DateTimeZone.forID("America/Sao_Paulo"));
        foo21.setDateTime(dateTime22);
        foo21.setTicarica("curupaaaaco! kk");
        fooset19.add(foo21);
        staticcar7.setFooSet(fooset19);



        classes.gear.Gear gear23 = new classes.gear.Gear();
        gear23.setNumberOfTeeth(10);
        gear23.setSize(1.1);
        gear23.setSizeFit(15.261);
        staticcar7.setGear(gear23);

        List<classes.gear.Gear>gears24 = new java.util.LinkedList<classes.gear.Gear>();
        classes.gear.Gear gear25 = new classes.gear.Gear();
        gear25.setNumberOfTeeth(10);
        gear25.setSize(1.1);
        gear25.setSizeFit(15.261);
        gears24.add(gear25);
        classes.gear.Gear gear26 = new classes.gear.Gear();
        gears24.add(gear26);
        staticcar7.setGears(gears24);


        staticcar7.setName("Carro");
        staticcar7.setNumberWeels(4);
        classes.foo.Foo foo28 = new classes.foo.Foo();
        foo28.setTicarica("it's be end?");
        classes.bar.Bar bar29 = new classes.bar.Bar();
        Date date30 = new Date(1555L);
        bar29.setDate(date30);
        Pair<classes.foo.Foo,classes.bar.Bar> Pair27 = new Pair<classes.foo.Foo,classes.bar.Bar>(foo28,bar29);
        staticcar7.setPair(Pair27);
        staticcar7.setPrice(22.11);
        List<java.lang.Object>whatever31 = new java.util.ArrayList<java.lang.Object>();
        whatever31.add("abacate");
        classes.gear.Gear gear33 = new classes.gear.Gear();
        whatever31.add(gear33);
        whatever31.add(42);
        staticcar7.setWhatever(whatever31);


        classes.car.Car.setStaticCar(staticcar7);

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
        bar4.setDescription("z� venancio");
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
        classes.foo.Foo foo15 = new classes.foo.Foo();
        foo15.setTicarica("it's be end?");
        classes.bar.Bar bar16 = new classes.bar.Bar();
        Date date17 = new Date(1555L);
        bar16.setDate(date17);
        Pair<classes.foo.Foo,classes.bar.Bar> Pair14 = new Pair<classes.foo.Foo,classes.bar.Bar>(foo15,bar16);
        car.setPair(Pair14);
        car.setPrice(22.11);
        List<java.lang.Object>whatever18 = new java.util.ArrayList<java.lang.Object>();
        whatever18.add("abacate");
        classes.gear.Gear gear20 = new classes.gear.Gear();
        whatever18.add(gear20);
        whatever18.add(42);
        car.setWhatever(whatever18);


    }

}
