package ru.qa.learn.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testArea(){
        Square s = new Square(5);
        int check = 20;
        Assert.assertEquals(s.area(),check);
//        assert s.area() == 24;
    }
    @Test
    public void testDistance(){
        Point p1 = new Point(6,8);
        Point p2 = new Point(-3,-4);
        Assert.assertEquals(p1.dist(p2),15);
        Assert.assertEquals(p2.dist(p1),15.0);
        Assert.assertEquals(p1.distance(p1,p2),15);
    }
}
