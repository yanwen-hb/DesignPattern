package designpattern;

public class Lobster extends Game {

    private static final String LOBSTER_URL = "D:\\UM\\yr4\\Design Pattern\\Assignment\\movingLobster.gif";
    Point pt = Point.getInstance();

    public Lobster() {
    }

    @Override
    void point() {
        pt.total -= 1;
        pt.getTotal();
    }

    @Override
    String URL() {
        return LOBSTER_URL;
    }
}
