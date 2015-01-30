package classes.car;
import org.junit.Test;
import org.junit.Before;
import classes.car.Car;
import java.util.List;
import [Ljava.util.HashMap$Node;;
import classes.gear.Gear;
import classes.foo.Foo;
import java.util.HashMap;
import java.util.ArrayList;


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

        classes.car.Car car12 = new classes.car.Car();
        car12.setSomething("something nice ;)");
        car12.setName("Trem que pula");
        car12.setNumberWeels(999);
        car12.setFlax(true);
        car12.setPrice(33.44);
        car12.setColor(enuns.Color.Red);
        car.setCar(car12);


        classes.gear.Gear gear = new classes.gear.Gear();
        gear.setNumberOfTeeth(10);
        gear.setSize(1.1);
        gear.setSizeFit(15.261);
        car.setGear(gear);

        List<classes.gear.Gear>gears13 = new ArrayList<classes.gear.Gear>();
        classes.gear.Gear gear14 = new classes.gear.Gear();
        gear14.setNumberOfTeeth(10);
        gear14.setSize(1.1);
        gear14.setSizeFit(15.261);
        gears13.add(gear14);
        classes.gear.Gear gear15 = new classes.gear.Gear();
        gear15.setNumberOfTeeth(null);
        gear15.setSize(null);
        gear15.setSizeFit(null);
        gears13.add(gear15);
        car.setGears(gears13);


        List<java.lang.Object>whatever16 = new ArrayList<java.lang.Object>();
        classes.gear.Gear gear17 = new classes.gear.Gear();
        gear17.setNumberOfTeeth(null);
        gear17.setSize(null);
        gear17.setSizeFit(null);
        whatever16.add(gear17);
        whatever16.add("abacate");
        whatever16.add(42);
        car.setWhatever(whatever16);



        java.util.HashMap hashmap = new java.util.HashMap();
        hashmap.setSerialVersionUID(362498820763181265);
        hashmap.setDEFAULT_INITIAL_CAPACITY(16);
        hashmap.setMAXIMUM_CAPACITY(1073741824);
        hashmap.setDEFAULT_LOAD_FACTOR(0.75);
        hashmap.setTREEIFY_THRESHOLD(8);
        hashmap.setUNTREEIFY_THRESHOLD(6);
        hashmap.setMIN_TREEIFY_CAPACITY(64);

        [Ljava.util.HashMap$Node; hashmap$node; = new [Ljava.util.HashMap$Node;();
        hashmap.setTable(hashmap$node;);

        hashmap.setSize(2);
        hashmap.setModCount(2);
        hashmap.setThreshold(12);
        hashmap.setLoadFactor(0.75);
        car.setFooBar(hashmap);

        List<classes.foo.Foo>fooset20 = new ArrayList<classes.foo.Foo>();
        classes.foo.Foo foo21 = new classes.foo.Foo();
        foo21.setTicarica("null");
        fooset20.add(foo21);
        classes.foo.Foo foo22 = new classes.foo.Foo();
        foo22.setTicarica("curupaaaaco! kk");
        fooset20.add(foo22);
        car.setFooSet(fooset20);


    }

    @Test
    public void getNameTest(){
        java.lang.String actual = car.getName();
        //make your asserts here :)
    }

    @Test
    public void getFooBarTest(){
        java.util.Map actual = car.getFooBar();
        //make your asserts here :)
    }

    @Test
    public void getFooSetTest(){
        java.util.Set actual = car.getFooSet();
        //make your asserts here :)
    }

    @Test
    public void getNumberWeelsTest(){
        java.lang.Integer actual = car.getNumberWeels();
        //make your asserts here :)
    }

    @Test
    public void getColorTest(){
        enuns.Color actual = car.getColor();
        //make your asserts here :)
    }

    @Test
    public void getGearsTest(){
        java.util.List actual = car.getGears();
        //make your asserts here :)
    }

    @Test
    public void getSomethingTest(){
    }

    @Test
    public void getFlaxTest(){
        java.lang.Boolean actual = car.getFlax();
        //make your asserts here :)
    }

    @Test
    public void getPriceTest(){
        java.lang.Double actual = car.getPrice();
        //make your asserts here :)
    }

    @Test
    public void getCarTest(){
        classes.car.Car actual = car.getCar();
        //make your asserts here :)
    }

    @Test
    public void getGearTest(){
        classes.gear.Gear actual = car.getGear();
        //make your asserts here :)
    }

    @Test
    public void getWhateverTest(){
        java.util.List actual = car.getWhatever();
        //make your asserts here :)
    }

}
