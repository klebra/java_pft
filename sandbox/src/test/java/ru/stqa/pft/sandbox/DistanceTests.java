package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

    @Test
    public void distanceTest1(){
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 6);

        Assert.assertEquals(p1.distance(p2), 4.0);
    }

    @Test
    public void distanceTest2(){
        Point p1 = new Point(2, 2);
        Point p2 = new Point(6, 2);

        Assert.assertEquals(p1.distance(p2), 4.0);
    }

    @Test
    public void distanceTest3(){
        Point p1 = new Point(32, 2);
        Point p2 = new Point(9, 6);

        Assert.assertEquals(p1.distance(p2), 23.345235059857504);
    }
}
