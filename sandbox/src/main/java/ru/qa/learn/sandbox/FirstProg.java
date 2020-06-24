package ru.qa.learn.sandbox;

public class FirstProg{
	public static void main(String[] args){
		hello("world");
		hello("new world");
		hello("anybody");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("Площать прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

		Point p1 = new Point(6,8);
		Point p2 = new Point(-3,-4);
		System.out.println("Расстояние от точки P1 с координатами " + p1.x + " и " + p1.y + " до точки P2 с координатами " + p2.x + " и " + p2.y + " = " + distance(p1,p2));
	}

	public static void hello(String sombody){
		System.out.println("Hello " + sombody + "!");
	}

	public static double distance(Point p1,Point p2){
		return Math.sqrt(((p1.x-p2.x)*(p1.x-p2.x))+((p1.y-p2.y)*(p1.y-p2.y)));
	}
}