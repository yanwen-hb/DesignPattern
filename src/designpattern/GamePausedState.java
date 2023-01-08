package designpattern;

import javax.swing.JButton;

public class GamePausedState implements State {
    public static BackgroundImageJFrame frame;

    public GamePausedState(BackgroundImageJFrame frame){
        this.frame = frame;
    }

    public void clickStartButton(){
        System.out.println("You are already in the game!");
    };
    public void clickPauseButton(){
        System.out.println("The game has been paused!");

    };
    public void clickResumeButton(){
        frame.setState(frame.getGameStartedState());
        frame.getTimer().start();
        frame.getPauseButton().setVisible(true);
        frame.getResumeButton().setVisible(false);
        frame.getPoint().setVisible(true); // display point
        frame.getPoint().setText("Point: "+Point.getInstance().getTotal());
        JButton[] buttons=frame.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            System.out.println("button true");
            buttons[i].setVisible(false);
        }
        System.out.println("Game resumed!");
    };
    public void clickLobster(){
        System.out.println("Unable to play game when under paused mode!");
    };
}
