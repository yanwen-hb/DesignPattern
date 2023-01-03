package designpattern;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Game {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JLabel label = new JLabel();

    final JLabel GameProcess() {
        String path = URL();
        JLabel label = generate(path);
        Clicked();
        return label;
    }

    abstract String URL();

    abstract void point();

    JLabel generate(String path) {
        ImageIcon object = new ImageIcon(path);
        ImageIcon newObject = scaleImage(object, (int) Math.round(object.getIconWidth() * 0.1), (int) Math.round(object.getIconHeight() * 0.1));
        label.setIcon(newObject);
        label.setBounds(getRandomNumber(0, 750), getRandomNumber(400, 620), newObject.getIconWidth(), (int) Math.round(newObject.getIconHeight()));
        label.setCursor(new Cursor(12));
        return label;
    }

    void Clicked() {
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("CLICKED");
                point();
                label.setIcon(null);
                label.setVisible(false);
            }
        });
    }

    ImageIcon scaleImage(ImageIcon icon, int w, int h) {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();
        if (icon.getIconWidth() > w) {
            nw = w;
            nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }
        if (nh > h) {
            nh = h;
            nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }
        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }

    int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

//    void calculatePoints(String algorithm) {
//        
////        if (algorithm.equals("Add")) {
////            pt.total += 1;
////        } else if (algorithm.equals("Minus")) {
////            pt.point -= 1;
////            pt.total += pt.point;
////        }
//    }

}