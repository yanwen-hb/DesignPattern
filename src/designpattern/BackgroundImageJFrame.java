package designpattern;


import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Cursor;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class BackgroundImageJFrame extends JFrame {

    private static final String BACKGROUNDIMAGE_URL = "D:\\UM\\yr4\\Design Pattern\\Assignment\\pantai.png";

    public BackgroundImageJFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //this is your screen size
        ImageIcon image = new ImageIcon(BACKGROUNDIMAGE_URL); //imports the image
        for (int i = 0; i < 20; i++) {
            Crab crab = new Crab();
            getContentPane().add(crab.GameProcess());            
        }

        for (int i = 0; i < 20; i++) {
            Lobster lobster = new Lobster();
            getContentPane().add(lobster.GameProcess());
        }

        JLabel lbl = new JLabel(); //puts the image into a jlabel
        lbl.setIcon(image);
        lbl.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        getContentPane().add(lbl); //puts label inside the jframe
        setSize(image.getIconWidth(), image.getIconHeight()); //gets h and w of image and sets jframe to the size
        int x = (screenSize.width - getSize().width) / 2; //These two lines are the dimensions
        int y = (screenSize.height - getSize().height) / 2;//of the center of the screen
        setLocation(x, y); //sets the location of the jframe
        setVisible(true); //makes the jframe visible
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public ImageIcon scaleImage(ImageIcon icon, int w, int h) {
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

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
