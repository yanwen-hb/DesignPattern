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
//import classes from other package
import designpattern.Command.Remote;
import designpattern.Command.StaticObject;
import designpattern.Command.AddStaticObjectCommand;
import designpattern.Command.RemoveStaticObjectCommand;

public class BackgroundImageJFrame extends JFrame {

    JPanel panel;
    Timer timer;
    JLabel crab;
    JLabel lobster;
    JButton point;
    Component[] componentList;
    JButton startButton;
    JButton pauseButton;
    JButton resumeButton;
    JButton endButton;
    JButton[] buttons;
    State idleState;
    State gameStartedState;
    State gamePausedState;
    State state;
    Point pt = Point.getInstance();
    String testing;
    Remote remote;
    SimpleButtonFactory factory;
    ButtonStore buttonStore;
    boolean seagullsUnlock = false;
    boolean shellUnlock = false;
    boolean sunUnlock = false;
    String sound_track;
    Music se;
    Music se2;

    private static final String BACKGROUNDIMAGE_URL = "assets\\pantai.png";

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public void setPauseButton(JButton pauseButton) {
        this.pauseButton = pauseButton;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setPoint(JButton point) {
        this.point = point;
    }

    public void setButtons(JButton[] buttons) {
        this.buttons = buttons;
    }

    public void setRemote(Remote remote) {
        this.remote = remote;
    }

    public void setFactory(SimpleButtonFactory factory) {
        this.factory = factory;
    }

    public void setButtonStore(ButtonStore buttonStore) {
        this.buttonStore = buttonStore;
    }

    public void setSeagullsUnlock(boolean accomplished) {
        this.seagullsUnlock = accomplished;
    }

    public void setShellUnlock(boolean accomplished) {
        this.shellUnlock = accomplished;
    }

    public void setSunUnlock(boolean accomplished) {
        this.sunUnlock = accomplished;
    }

    public void setSoundTrack(String sound_track) {
        this.sound_track = sound_track;
    }

    public void setBackgroundSound(Music se) {
        this.se = se;
    }

    public void setSecondBackgroundSound(Music se2) {
        this.se2 = se2;
    }

    public BackgroundImageJFrame() {
        idleState = new IdleState(this);
        gameStartedState = new GameStartedState(this);
        gamePausedState = new GamePausedState(this);
        state = idleState;
        testing = "Adsadsad";

        //create sound effect
        se = new Music();
        se2 = new Music();
        setBackgroundSound(se);
        setSecondBackgroundSound(se2);

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

        //create point button
        point = new JButton("Point: " + pt.getTotal());
        point.setBackground(Color.white);
        point.setForeground(Color.black);
        point.setFont(new Font("DEFAULT", Font.PLAIN, 15));
        point.setFocusPainted(false);
        point.setBounds((getSize().width - 90) / 2, 20, 120, 30);
        panel.add(point);
        point.setVisible(false);

        // Creating start button
        startButton = new JButton("Start");
        startButton.setBackground(new Color(0, 204, 204));
        startButton.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 15));
        startButton.setForeground(Color.white);
        startButton.setFocusPainted(false);
        startButton.setBounds(getSize().width - 130, 20, 100, 30);
        setStartButton(startButton);

        //Creating pause button
        pauseButton = new JButton("Pause");
        pauseButton.setBackground(Color.red);
        pauseButton.setForeground(Color.white);
        pauseButton.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 15));
        pauseButton.setFocusPainted(false);
        pauseButton.setBounds(getSize().width - 130, 20, 100, 30);

        //Creating resume button
        resumeButton = new JButton("Resume");
        resumeButton.setBackground(Color.green);
        resumeButton.setBackground(new Color(0, 204, 0));
        resumeButton.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 15));
        resumeButton.setForeground(Color.white);
        resumeButton.setFocusPainted(false);
        resumeButton.setBounds(getSize().width - 130, 20, 100, 30);

        //Creating static object buttons
        Remote remote = new Remote();
        setRemote(remote);
        buttons = createRemoteButton(this, remote, image); //image-> to determine the position of button

        //Create object and add command 
        SimpleButtonFactory factory = new SimpleButtonFactory();
        setFactory(factory);
        ButtonStore buttonStore = new ButtonStore(factory);
        setButtonStore(buttonStore);
        buttonStore.displayButton(this, buttons, "boat", 50, 400, remote);
        buttonStore.displayButton(this, buttons, "sunbed", 570, 460, remote);

        //sound effect
        sound_track = "assets\\sea.wav";
        setSoundTrack(sound_track);

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
        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent event) {
                se.setFile(sound_track);
                se.play();
            }
        });
    }

    public void clickStartButton() {
        state.clickStartButton();
    }

    ;
    public void clickPauseButton() {
        state.clickPauseButton();
    }

    ;
    public void clickResumeButton() {
        state.clickResumeButton();
    }

    ;
    public void clickLobster() {
        state.clickLobster();
    }

    ;

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

    public JPanel getPanel() {
        return panel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public Timer getTimer() {
        return timer;
    }

    public JButton getPoint() {
        return point;
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public Remote getRemote() {
        return remote;
    }

    public SimpleButtonFactory getFactory() {
        return factory;
    }

    public ButtonStore getButtonStore() {
        return buttonStore;
    }

    public boolean getSeagullsUnlock() {
        return seagullsUnlock;
    }

    public boolean getShellUnlock() {
        return shellUnlock;
    }

    public boolean getSunUnlock() {
        return sunUnlock;
    }

    public String getSoundtrack() {
        return sound_track;
    }

    public Music getBackgroundSound() {
        return se;
    }

    public Music getSecondBackgroundSound() {
        return se2;
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

    //command design pattern
    public JButton[] createRemoteButton(JFrame f, Remote remote, ImageIcon image) {
        JButton[] buttons = new JButton[12]; ////image.getIconWidth(), image.getIconHeight()
        int xAxis = 20;//100;
        int yAxis = image.getIconHeight() - 140;//350;
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JButton("");
            buttons[i].setBackground(new Color(52, 73, 94));
            buttons[i].setForeground(Color.white);
            buttons[i].setFocusPainted(false);
            if (i % 2 == 1) {
                buttons[i - 1].setBounds(xAxis, yAxis, 110, 30);
                buttons[i].setBounds(xAxis, yAxis + 40, 110, 30);
                xAxis += 120;
            }
        }
        setButtons(buttons);
        return buttons;
    }

}
