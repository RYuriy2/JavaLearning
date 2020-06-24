package ru.qa.learn.sandbox;

public class Distance {
    public static void main(String[] args){
        double k1,k2,d,p12,p21,s;
        Point p1 = new Point(6,8);
        Point p2 = new Point(-3,-4);
        System.out.println("Даны точки с координатами P1(" + p1.x +","+ p1.y + ") и P2(" + p2.x + "," + p2.y + ").");
        System.out.println("Необходимо рассчитать длину отрезка, ограниченного данными точками.");
        System.out.println();
        System.out.println("Длиной отрезка будет равна длине гипотенузы прямоугольного треугольника");
        System.out.println("с вершиной в точке пересечения прямых, опущенных из заданных точек к осям координат.");
        System.out.println("Длину гипотенузы будем вычислять по теореме Пифагора.");
        System.out.println("Расчёт произведём пошагово:");
        System.out.println();
        k1=p1.x-p2.x;
        k2=p1.y-p2.y;
        System.out.println("Рассчитаем разницу между координатами Х точек P1 и P2. Это будет длина первого катета. Обозначим K1.");
        System.out.println("K1 = " + k1);
        System.out.println("Рассчитаем разницу между координатами Y точек P1 и P2. Это будет длина второго катета. Обозначим K2.");
        System.out.println("K2 = " + k2);
        p12=k1*k1;
        p21=k2*k2;
        System.out.println("Возведём длины катетов в кадрат.");
        System.out.println("Квадрат длины катета K1 обозначим P12.");
        System.out.println("P12 = " + p12);
        System.out.println("Квадрат длины катета K2 обозначим P21.");
        System.out.println("P21 = " + p21);
        s=p12+p21;
        System.out.println("Получим сумму квадратов катетов и обозначим S.");
        System.out.println("S = " + s);
        d=Math.sqrt(s);
        System.out.println("Вычислим квадратный корень из суммы квадратов катетов. Обозначим D.");
        System.out.println("D = " + d + " - искомая длина отрезка между точками P1 и P2.");
        System.out.println("Сравним результат вычисления с результатом вычисления при помощи функции distance:");
        System.out.println("distance = " + distance(p1,p2));
        System.out.println(d + " = " + distance(p1,p2));

        System.out.println("Вызов метода класса Point. Результат вычисления = " + p1.dist(p2));
        System.out.println("Вызов метода класса Point. Результат вычисления = " + p2.dist(p1));
    }

    public static double distance(Point p1,Point p2){
        return Math.sqrt(((p1.x-p2.x)*(p1.x-p2.x))+((p1.y-p2.y)*(p1.y-p2.y)));
    }
    }
