package designpattern;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GameStartedState implements State {
    public static BackgroundImageJFrame frame;

    public GameStartedState(BackgroundImageJFrame frame){
        this.frame = frame;
    }

    public void clickStartButton(){
        System.out.println("You are already in the game!");
    };
    public void clickPauseButton(){
        frame.setState(frame.getGamePausedState());
                for(Component c : frame.getPanel().getComponents()){
                    if(c instanceof JLabel && c.getName() != "background"){
                        frame.getPanel().remove(c);
                    }
                }
                frame.getPauseButton().setVisible(false);
                frame.getResumeButton().setVisible(true);
                frame.getTimer().stop();
                frame.getPanel().revalidate();
                frame.getPanel().repaint();
        System.out.println("Game paused!");
    };
    public void clickResumeButton(){
        System.out.println("You are already in the game!");
    };
    public void clickLobster(){
        frame.setState(frame.getIdleState());
        for(Component c : frame.getPanel().getComponents()){
            if(c instanceof JLabel && c.getName() != "background"){
                frame.getPanel().remove(c);
            }
        }
        frame.getPauseButton().setVisible(false);
        frame.getResumeButton().setVisible(false);
        frame.getStartButton().setVisible(true);
        frame.getTimer().stop();
        frame.getPanel().revalidate();
        frame.getPanel().repaint();
        System.out.println("Game loss");
    };
}
