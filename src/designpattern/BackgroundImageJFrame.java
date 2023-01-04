package designpattern;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Cursor;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class BackgroundImageJFrame extends JFrame {
    JPanel panel;
    Timer timer;
    JLabel crab;
    JLabel lobster;
    Component[] componentList;
    JButton startButton;
    JButton pauseButton;
    JButton resumeButton;
    JButton endButton;
    State idleState;
    State gameStartedState;
    State gamePausedState;
    State state;
    Point pt = Point.getInstance();
    String testing;

    private static final String BACKGROUNDIMAGE_URL = "C:\\Users\\xingy\\Pictures\\pantai.png";


    public void setPanel(JPanel panel){
        this.panel = panel;
    }

    public void setStartButton(JButton startButton){
        this.startButton = startButton;
    }

    public void setPauseButton(JButton pauseButton){
        this.pauseButton = pauseButton;
    }

    public void setTimer(Timer timer){
        this.timer = timer;
    }


    public BackgroundImageJFrame() {
        idleState = new IdleState(this);
		gameStartedState = new GameStartedState(this);
		gamePausedState = new GamePausedState(this);
        state = idleState;
        testing = "Adsadsad";

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // this is your screen size
        ImageIcon image = new ImageIcon(BACKGROUNDIMAGE_URL); // imports the image

        panel = new JPanel();
        JLabel lbl = new JLabel(); // puts the image into a jlabel
        lbl.setIcon(image);
        lbl.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        lbl.setName("background");
        setSize(image.getIconWidth(), image.getIconHeight()); // gets h and w of image and sets jframe to the size
        int x = (screenSize.width - getSize().width) / 2; // These two lines are the dimensions
        int y = (screenSize.height - getSize().height) / 2;// of the center of the screen

        // Creating start button
        startButton = new JButton("Start");
        startButton.setBackground(new Color(59, 89, 182));
        startButton.setForeground(Color.white);
        startButton.setFocusPainted(false);
        startButton.setBounds((getSize().width - 90) / 2, (getSize().height - 50) / 2, 90, 50);
        setStartButton(startButton);
        
        //Creating pause button
        pauseButton = new JButton("Pause");
        pauseButton.setBackground(Color.red);
        pauseButton.setForeground(Color.white);
        pauseButton.setFocusPainted(false);
        pauseButton.setBounds(getSize().width - 110, 10, 90, 50);

        //Creating resume button
        resumeButton = new JButton("Resume");
        resumeButton.setBackground(Color.green);
        resumeButton.setForeground(Color.white);
        resumeButton.setFocusPainted(false);
        resumeButton.setBounds(getSize().width - 110, 10, 90, 50);

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clickPauseButton();
            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clickStartButton();
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clickResumeButton();
            }
        });

        panel.add(startButton);
        panel.add(pauseButton);
        panel.add(resumeButton);
        pauseButton.setVisible(false);
        resumeButton.setVisible(false);

        panel.setLayout(null);
        panel.add(lbl); // puts label inside the jframe
        getContentPane().add(panel);

        setLocation(x, y); // sets the location of the jframe
        setVisible(true); // makes the jframe visible
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    public void clickStartButton(){
        state.clickStartButton();
    };
    public void clickPauseButton(){
        state.clickPauseButton();
    };
    public void clickResumeButton(){
        state.clickResumeButton();
    };
    public void clickLobster(){
        state.clickLobster();
    };

    void setState(State state) {
		this.state = state;
	}

    void setTesting(String testing) {
		this.testing = testing;
	}

    public String getTesting() {
        return testing;
    }

    public State getGameStartedState() {
        return gameStartedState;
    }

    public State getGamePausedState() {
        return gamePausedState;
    }

    public State getIdleState() {
        return idleState;
    }

    public State getCurrentState() {
        return state;
    }

    public JPanel getPanel(){
        return panel;
    }

    public JButton getStartButton(){
        return startButton;
    }

    public JButton getPauseButton(){
        return pauseButton;
    }

    public JButton getResumeButton(){
        return resumeButton;
    }

    public Timer getTimer(){
        return timer;
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
