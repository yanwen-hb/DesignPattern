package designpattern;

import static designpattern.IdleState.frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GameStartedState implements State {

    public static BackgroundImageJFrame frame;

    public GameStartedState(BackgroundImageJFrame frame) {
        this.frame = frame;
    }

    public void clickStartButton() {
        System.out.println("You are already in the game!");
    }

    ;
    public void clickPauseButton() {
        try {
            frame.getSecondBackgroundSound().stop();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        frame.getBackgroundSound().setFile("assets\\sea.wav");
        frame.getBackgroundSound().play();
        frame.setState(frame.getGamePausedState());
        frame.getGameStateDisplay().setText("<html>Game Pause!</html>");

        if (Point.getInstance().getTotal() >= 10 && !(frame.getSunUnlock())) {
            frame.buttonStore.displayButton(frame, frame.getButtons(), "sun", 40, 70, frame.remote);
            frame.setSunUnlock(true);
        }
        if (Point.getInstance().getTotal() >= 6 && !(frame.getSeagullsUnlock())) {
            frame.buttonStore.displayButton(frame, frame.getButtons(), "seagulls", 280, 100, frame.remote);
            frame.setSeagullsUnlock(true);
        }
        if (Point.getInstance().getTotal() >= 3 && !(frame.getShellUnlock())) {
            frame.buttonStore.displayButton(frame, frame.getButtons(), "castle", 230, 480, frame.remote);
            frame.setShellUnlock(true);
        }
        for (Component c : frame.getPanel().getComponents()) {
            if (c instanceof JLabel && c.getName() != "background" && c.getName() != "staticObject" && c.getName() != "instruction" && c.getName() != "gameState") {
                frame.getPanel().remove(c);
            } else if (c.getName() == "gameState") {
                frame.getGameStateDisplay().setVisible(true);
            }
        }
        frame.getPauseButton().setVisible(false);
        frame.getResumeButton().setVisible(true);
        JButton[] buttons = frame.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setVisible(true);
        }
        frame.getTimer().stop();
        frame.getPanel().revalidate();
        frame.getPanel().repaint();
        System.out.println("Game paused!");

    }

    ;
    public void clickResumeButton() {
        System.out.println("You are already in the game!");
    }

    ;
    public void clickLobster() {
        frame.setState(frame.getIdleState());
        try {
            frame.getSecondBackgroundSound().stop();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        if (Point.getInstance().getTotal() >= 10 && !(frame.getSunUnlock())) {
            frame.buttonStore.displayButton(frame, frame.getButtons(), "sun", 40, 50, frame.remote);
            frame.setSunUnlock(true);
        }
        if (Point.getInstance().getTotal() >= 6 && !(frame.getSeagullsUnlock())) {
            frame.buttonStore.displayButton(frame, frame.getButtons(), "seagulls", 280, 100, frame.remote);
            frame.setSeagullsUnlock(true);
        }
        if (Point.getInstance().getTotal() >= 3 && !(frame.getShellUnlock())) {
            frame.buttonStore.displayButton(frame, frame.getButtons(), "castle", 230, 480, frame.remote);
            frame.setShellUnlock(true);
        }
        for (Component c : frame.getPanel().getComponents()) {
            if (c instanceof JLabel && c.getName() != "background" && c.getName() != "staticObject" && c.getName() != "instruction" && c.getName() != "gameState") {
                frame.getPanel().remove(c);
            } else if (c.getName() == "gameState") {
                frame.getGameStateDisplay().setVisible(false);
                frame.getInstruction().setVisible(false);
            }
        }
        frame.getPauseButton().setVisible(false);
        frame.getResumeButton().setVisible(false);
        frame.getStartButton().setVisible(true);
        frame.getTimer().stop();
        JButton[] buttons = frame.getButtons();

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setVisible(true);
        }

        frame.getPanel().revalidate();
        frame.getPanel().repaint();

        System.out.println("Game loss");

        frame.getBackgroundSound().setFile("assets\\sea.wav");
        frame.getBackgroundSound().play();
    }
;
}
