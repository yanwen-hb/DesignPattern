package designpattern;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Lobster extends Game {

    public static BackgroundImageJFrame frame;

    private static final String LOBSTER_URL = "assets\\movingLobster.gif";
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
                try {
                    //sound effect 
                    frame.getBackgroundSound().stop();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                frame.getBackgroundSound().setFile("assets\\lose.wav");
                frame.getBackgroundSound().play();
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
