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
    JButton treeButton;
    JButton [] buttons;
    State idleState;
    State gameStartedState;
    State gamePausedState;
    State state;
    Point pt = Point.getInstance(); 
    String testing;

    private static final String BACKGROUNDIMAGE_URL = "assets\\pantai.png";


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

    public void setPoint(JButton point) {
        this.point = point;
    }
    
    public void setButtons(JButton [] buttons){
        this.buttons = buttons;
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
        
        //create point button
        point = new JButton("Point: "+pt.getTotal());
        point.setBackground(Color.white);
        point.setForeground(Color.black);        
        point.setFocusPainted(false);       
        point.setBounds(80,30,120,40);
        panel.add(point);
        point.setVisible(false);
        
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
        
        //Creating static object buttons
        Remote remote=new Remote();
        buttons=createRemoteButton(this, remote, image); //image-> to determine the position of button
        
        //Create object and add command -> to be modified to factory design pattern
        
        //create object
        StaticObject boat=new StaticObject(this, panel, "assets\\boat.png", 460, 400); 
        //set command
        AddStaticObjectCommand addBoat=new AddStaticObjectCommand(boat);
        RemoveStaticObjectCommand removeBoat=new RemoveStaticObjectCommand(boat);
        remote.setCommand(0, addBoat, removeBoat);
        //add commend to button
        addCommandToButton(remote, buttons[0], buttons[1], this, 0, "boat");
        
        //another object
        //create object
        StaticObject tree=new StaticObject(this , panel, "assets\\tree.png", 20, 460);
        //set command
        AddStaticObjectCommand addTree=new AddStaticObjectCommand(tree);
        RemoveStaticObjectCommand removeTree=new RemoveStaticObjectCommand(tree);
        remote.setCommand(1, addTree, removeTree);
        //add commend to button
        addCommandToButton(remote, buttons[2], buttons[3], this, 1, "tree");


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
        System.out.println("state: "+state.getClass());
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

    public JButton getPoint() {
        return point;
    }
    
    public JButton getTreeButton() {
        return buttons[0];
    }
        
    public JButton [] getButtons(){
        return buttons;
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
    public JButton[] createRemoteButton(JFrame f, Remote remote, ImageIcon image){
        JButton [] buttons=new JButton[12]; ////image.getIconWidth(), image.getIconHeight()
        int xAxis= 20 ;//100;
        System.out.println("width: "+ image.getIconWidth());
        int yAxis= image.getIconHeight()-140;//350;
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JButton("");
            buttons[i].setBackground(new Color(	52, 73, 94));
            buttons[i].setForeground(Color.white);
            buttons[i].setFocusPainted(false);
            if(i%2==1){
                buttons[i-1].setBounds(xAxis, yAxis, 110, 30); 
                buttons[i].setBounds(xAxis, yAxis+40, 110, 30); 
                xAxis+=120;
            }
        }
        setButtons(buttons);
        return buttons;
    }
    
    public void addCommandToButton(Remote remote, JButton addButton, JButton removeButton, JFrame f, int slot, String name){
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remote.onButtonWasPushed(slot);
            }
        });
        addButton.setText("add "+name);
        f.add(addButton);
        
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remote.offButtonWasPushed(slot);
            }
        });
        removeButton.setText("remove "+name);
        f.add(removeButton);
    }
}
