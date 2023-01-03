package designpattern;

public class Point {
    int point=0;
    int total=0;
    private static Point uniqueInstance;

    private Point() {
    }
    
    public static Point getInstance(){
        if (uniqueInstance == null) {
            uniqueInstance = new Point();
        }
        return uniqueInstance;
    }
    
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getTotal() {
        System.out.println(total);
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}