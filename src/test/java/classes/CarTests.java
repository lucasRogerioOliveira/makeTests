package classes;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import gear.Gear;
import java.util.List;
import classes.Car;
import java.util.ArrayList;


public class CarTests {


    private Car car = new Car();

    @Before
    public void beforeTest(){
        car.setName("Carro");
        car.setNumberWeels(4);
        car.setFlax(false);
        car.setPrice(22.11);
        car.setColor(enuns.Color.Blue);

        Car car5 = new Car();
        car5.setName("Trem que pula");
        car5.setNumberWeels(999);
        car5.setFlax(true);
        car5.setPrice(33.44);
        car5.setColor(enuns.Color.Red);
        car.setCar(car5);


        Gear gear = new Gear();
        gear.setNumberOfTeeth(10);
        gear.setSize(1.1);
        gear.setSizeFit(15.261);
        car.setGear(gear);

        List<Gear> gears6 = new ArrayList<Gear>();
        Gear gear7 = new Gear();
        gear7.setNumberOfTeeth(10);
        gear7.setSize(1.1);
        gear7.setSizeFit(15.261);
        gears6.add(gear7);

        Gear gear8 = new Gear();
        gear8.setNumberOfTeeth(null);
        gear8.setSize(null);
        gear8.setSizeFit(null);
        gears6.add(gear8);

    }

    @Test
    public void getNameTest(){
        java.lang.String actual = car.getName();
        //make your asserts here :)
    }

    @Test
    public void getSomethingTest(){
    }

    @Test
    public void getNumberWeelsTest(){
        java.lang.Integer actual = car.getNumberWeels();
        //make your asserts here :)
    }

    @Test
    public void getFlaxTest(){
        java.lang.Boolean actual = car.getFlax();
        //make your asserts here :)
    }

    @Test
    public void getCarTest(){
        classes.Car actual = car.getCar();
        //make your asserts here :)
    }

    @Test
    public void getGearTest(){
        gear.Gear actual = car.getGear();
        //make your asserts here :)
    }

    @Test
    public void getColorTest(){
        enuns.Color actual = car.getColor();
        //make your asserts here :)
    }

    @Test
    public void getPriceTest(){
        java.lang.Double actual = car.getPrice();
        //make your asserts here :)
    }

    @Test
    public void getGearsTest(){
        java.util.List actual = car.getGears();
        //make your asserts here :)
    }

}
