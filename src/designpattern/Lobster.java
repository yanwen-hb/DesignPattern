package designpattern;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Lobster extends Game {
    public static BackgroundImageJFrame frame;

    private static final String LOBSTER_URL = "C:\\Users\\xingy\\Pictures\\movingLobster.gif";
    Point pt = Point.getInstance();

    public Lobster(BackgroundImageJFrame frame) {
        this.frame = frame;
    }

    @Override
    void point() {
        // pt.total -= 1;
        pt.getTotal();
    }

    void Clicked() {
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("CLICKED");
                point();
                label.setIcon(null);
                label.setVisible(false);
                frame.clickLobster();
            }
        });
    }


    @Override
    String URL() {
        return LOBSTER_URL;
    }
}
