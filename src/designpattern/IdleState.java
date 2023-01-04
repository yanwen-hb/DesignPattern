package designpattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IdleState implements State {
    public static BackgroundImageJFrame frame;
    JButton startButton;
    String testing;
    int delay = 1000;

    public IdleState(BackgroundImageJFrame frame) {
        this.frame = frame;
    }

    public void clickStartButton() {
        System.out.println("Game start!");
        frame.setState(frame.getGameStartedState());
        frame.getStartButton().setVisible(false);
        frame.getPauseButton().setVisible(true);
        for (var i = 0; i < 10; i++) {
            JLabel crab = new Crab().GameProcess();
            frame.getPanel().add(crab);
            frame.getPanel().setComponentZOrder(crab, 0);

            JLabel lobster = new Lobster(frame).GameProcess();
            frame.getPanel().add(lobster);
            frame.getPanel().setComponentZOrder(lobster, 0);
        }
        frame.getPanel().revalidate();
        frame.getPanel().repaint();
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                for (Component c : frame.getPanel().getComponents()) {
                    // Find the components you want to remove
                    if (c instanceof JLabel && c.getName() != "background") {
                        frame.getPanel().remove(c);
                    }
                }
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
        Timer timer = new Timer(delay, taskPerformer);
        frame.setTimer(timer);
        timer.start();
    };

    public void clickPauseButton() {
        System.out.println("You are not in the game!");

    };

    public void clickResumeButton() {
        System.out.println("You are not in the game!");
    };

    public void clickLobster() {
        System.out.println("You are not in the game!");
    };
}
