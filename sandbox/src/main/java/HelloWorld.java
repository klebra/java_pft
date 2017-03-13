public class HelloWorld {

    public static void main(String[] args){
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 6);
        System.out.println("Расстояние между точками с координатами A("
                + p1.x + ", " + p1.y + ") и B(" + p2.x + ", " + p2.y + ") = " + distance(p1, p2));
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
}
