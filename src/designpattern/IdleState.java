package designpattern;

import static designpattern.GameStartedState.frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IdleState implements State {

    public static BackgroundImageJFrame frame;
    JButton startButton;
    String testing;
    int delay = 2000;

    public IdleState(BackgroundImageJFrame frame) {
        this.frame = frame;
    }

    public void clickStartButton() {

        //display game state
        frame.getInstruction().setVisible(true);
        frame.getGameStateDisplay().setText("<html>Game Start!</html>");
        frame.getGameStateDisplay().setVisible(true);
        

        System.out.println("Game start!");
        System.out.println("Game Instruction: ");
        System.out.println("Click crab to add point! Dont click lobster! You may try!");

        frame.getSecondBackgroundSound().setFile("assets\\game.wav");
        frame.getSecondBackgroundSound().play();

        frame.setState(frame.getGameStartedState());
        frame.getStartButton().setVisible(false);
        frame.getPauseButton().setVisible(true);
        frame.getPoint().setVisible(true); //display point 
        frame.getPoint().setText("Point: " + Point.getInstance().getTotal());
        //remote
        JButton[] buttons = frame.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setVisible(false);
        }

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                for (Component c : frame.getPanel().getComponents()) {
                    // Find the components you want to remove
                    if (c instanceof JLabel && c.getName() != "background" && c.getName() != "staticObject" && c.getName() != "instruction" && c.getName() != "gameState") {
                        frame.getPanel().remove(c);
                    }else if(c.getName() == "gameState"){
                        frame.getGameStateDisplay().setVisible(false);
                    }
                }
                //display lobster and crab 
                for (var i = 0; i < 10; i++) {
                    JLabel crab = new Crab().GameProcess();
                    frame.getPanel().add(crab);
                    frame.getPanel().setComponentZOrder(crab, 0);

                    JLabel lobster = new Lobster(frame).GameProcess();
                    frame.getPanel().add(lobster);
                    frame.getPanel().setComponentZOrder(lobster, 0);
                }
                frame.getPauseButton().setVisible(true);
                frame.getPanel().revalidate();
                frame.getPanel().repaint();
            }
        };
        
        //start timer
        Timer timer = new Timer(delay, taskPerformer);
        frame.setTimer(timer);
        timer.start();
    }

    ;

    public void clickPauseButton() {
        System.out.println("You are not in the game!");

    }

    ;

    public void clickResumeButton() {
        System.out.println("You are not in the game!");
    }

    ;

    public void clickLobster() {
        System.out.println("You are not in the game!");
    }
;
}
