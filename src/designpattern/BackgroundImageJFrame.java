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
import javax.swing.border.Border;

public class BackgroundImageJFrame extends JFrame {

    JPanel panel;
    Timer timer;
    JButton point;
    JButton startButton;
    JButton pauseButton;
    JButton resumeButton;
    JButton[] buttons;
    State idleState;
    State gameStartedState;
    State gamePausedState;
    State state;
    Point pt = Point.getInstance();
    Remote remote;
    SimpleButtonFactory factory;
    ButtonStore buttonStore;
    boolean seagullsUnlock = false;
    boolean shellUnlock = false;
    boolean sunUnlock = false;
    String sound_track;
    Music se;
    Music se2;
    JLabel instruction;
    JLabel gameStateDisplay;

    private static final String BACKGROUNDIMAGE_URL = "assets\\pantai.png";

    public BackgroundImageJFrame() {
        //initialize states
        idleState = new IdleState(this);
        gameStartedState = new GameStartedState(this);
        gamePausedState = new GamePausedState(this);
        state = idleState;

        //create sound effect
        se = new Music();
        se2 = new Music();
        setBackgroundSound(se);
        setSecondBackgroundSound(se2);

        //declare screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //import background image
        ImageIcon image = new ImageIcon(BACKGROUNDIMAGE_URL); 

        //declare panel 
        panel = new JPanel();
        //put background image to a jlabel
        JLabel lbl = new JLabel(); 
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

        // create start button
        startButton = new JButton("Start");
        startButton.setBackground(new Color(0, 204, 204));
        startButton.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 15));
        startButton.setForeground(Color.white);
        startButton.setFocusPainted(false);
        startButton.setBounds(getSize().width - 130, 20, 100, 30);
        setStartButton(startButton);

        //create pause button
        pauseButton = new JButton("Pause");
        pauseButton.setBackground(Color.red);
        pauseButton.setForeground(Color.white);
        pauseButton.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 15));
        pauseButton.setFocusPainted(false);
        pauseButton.setBounds(getSize().width - 130, 20, 100, 30);

        //create resume button
        resumeButton = new JButton("Resume");
        resumeButton.setBackground(Color.green);
        resumeButton.setBackground(new Color(0, 204, 0));
        resumeButton.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 15));
        resumeButton.setForeground(Color.white);
        resumeButton.setFocusPainted(false);
        resumeButton.setBounds(getSize().width - 130, 20, 100, 30);

        //create game instruction
        instruction = new JLabel("<html>Game Instruction: <br>Click crab to add point! Dont click lobster!</html>");
        instruction.setForeground(Color.WHITE);
        instruction.setBounds(10, 20, 200, 50);
        instruction.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 15));
        instruction.setName("instruction");
        panel.add(instruction);
        panel.setComponentZOrder(instruction, 0);
        instruction.setVisible(false);

        //create game state display
        gameStateDisplay = new JLabel("<html>Game Start!</html>", SwingConstants.CENTER);
        gameStateDisplay.setForeground(Color.RED);
        // create a line border with the specified color and width
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        gameStateDisplay.setBorder(border);
        gameStateDisplay.setBounds((getSize().width - 300) / 2, (getSize().height - 200) / 2, 300, 50);
        gameStateDisplay.setFont(new Font("ARIAL", Font.CENTER_BASELINE, 36));
        gameStateDisplay.setBackground(Color.WHITE);
        gameStateDisplay.setOpaque(true);
        gameStateDisplay.setName("gameState");
        panel.add(gameStateDisplay);
        panel.setComponentZOrder(gameStateDisplay, 0);
        gameStateDisplay.setVisible(false);

        //create static object buttons
        Remote remote = new Remote();
        setRemote(remote);
        buttons = createRemoteButton(this, remote, image); //image-> to determine the position of button

        //create object and add command 
        SimpleButtonFactory factory = new SimpleButtonFactory();
        setFactory(factory);
        ButtonStore buttonStore = new ButtonStore(factory);
        setButtonStore(buttonStore);
        buttonStore.displayButton(this, buttons, "boat", 50, 400, remote);
        buttonStore.displayButton(this, buttons, "sunbed", 570, 460, remote);

        //sound effect
        sound_track = "assets\\sea.wav";
        setSoundTrack(sound_track);

        //add action listener to pauseButton
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clickPauseButton();
            }
        });

        //add action listener to startButton
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clickStartButton();
            }
        });

        //add action listener to resumeButton
        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clickResumeButton();
            }
        });

        //add all button into panel
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

    //methods to delegate to the current state 
    public void clickStartButton() {
        state.clickStartButton();
    }

    public void clickPauseButton() {
        state.clickPauseButton();
    }

    public void clickResumeButton() {
        state.clickResumeButton();
    }

    public void clickLobster() {
        state.clickLobster();
    }

    //allow object to transite the frame state to different state
    void setState(State state) {
        this.state = state;
    }

    //set methods
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

    //get methods
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

    public JLabel getGameStateDisplay() {
        return gameStateDisplay;
    }

    public JLabel getInstruction() {
        return instruction;
    }

    //create button to add and remove static object
    public JButton[] createRemoteButton(JFrame f, Remote remote, ImageIcon image) {
        JButton[] buttons = new JButton[12];
        int xAxis = 20;;
        int yAxis = image.getIconHeight() - 140;
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JButton("");
            buttons[i].setBackground(new Color(52, 73, 94));
            buttons[i].setForeground(Color.white);
            buttons[i].setFocusPainted(false);
            if (i % 2 == 1) {
                buttons[i - 1].setBounds(xAxis, yAxis, 140, 30);
                buttons[i].setBounds(xAxis, yAxis + 40, 140, 30);
                xAxis += 150;
            }
        }
        setButtons(buttons);
        return buttons;
    }

}
