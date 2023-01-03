package designpattern;

public class Crab extends Game {

    private static final String CRAB_URL = "D:\\UM\\yr4\\Design Pattern\\Assignment\\movingCrab.gif";
    Point pt = Point.getInstance();

    public Crab() {
    }

    @Override
    void point() {
        pt.total += 1;
        pt.getTotal();
    }

    @Override
    String URL() {
        return CRAB_URL;
    }

}

