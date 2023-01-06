package designpattern;

import static designpattern.IdleState.frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Crab extends Game {

    private static final String CRAB_URL = "assets\\movingCrab.gif";
    Point pt = Point.getInstance();

    public Crab() {
    }

    @Override
    void point() {
        pt.total += 1;
      //  pt.getTotal();
        frame.getPoint().setVisible(true); //display point 
        frame.getPoint().setText("Point: "+Point.getInstance().getTotal());
    }

    void Clicked() {
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("CLICKED CRAB");
                point();                
                label.setIcon(null);
                label.setVisible(false);
            }
        });
    }

    @Override
    String URL() {
        return CRAB_URL;
    }

}

