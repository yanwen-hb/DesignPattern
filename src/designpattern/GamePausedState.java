package designpattern;

import java.io.IOException;
import javax.swing.JButton;

public class GamePausedState implements State {

    public static BackgroundImageJFrame frame;

    public GamePausedState(BackgroundImageJFrame frame) {
        this.frame = frame;
    }
    public void clickStartButton() {
        System.out.println("You are already in the game!");
    };
    public void clickPauseButton() {
        System.out.println("The game has been paused!");

    };
    public void clickResumeButton() {
        //change background music
        frame.getSecondBackgroundSound().setFile("assets\\game.wav");
        frame.getSecondBackgroundSound().play();
        //set state to gameStarted state
        frame.setState(frame.getGameStartedState());
        //start the timer again
        frame.getTimer().start();
        //set pause button to visible
        frame.getPauseButton().setVisible(true);
        //set resume button to invisible
        frame.getResumeButton().setVisible(false);
        frame.getPoint().setVisible(true); // display point
        frame.getPoint().setText("Point: " + Point.getInstance().getTotal());
        JButton[] buttons = frame.getButtons();
        //set all buttons to invisible 
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setVisible(false);
        }
        //display game resume message
        frame.getGameStateDisplay().setText("Game Resume!");
        System.out.println("Game resumed!");
    }

    ;
    public void clickLobster() {
        System.out.println("Unable to play game when under paused mode!");
    };
}
