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
	}

	public static void hello(String sombody){
		System.out.println("Hello " + sombody + "!");
	}
}