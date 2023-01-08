/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.Command;

/**
 *
 * @author ASUS
 */
public class Remote {
    Command[] onCommands;
    Command[] offCommands;
    
    public Remote(){
        onCommands=new Command[6];
        offCommands=new Command[6];
        
        Command noCommand=new NoCommand();
        
        for (int i = 0; i < 6; i++) {
            onCommands[i]=noCommand;
            offCommands[i]=noCommand;
        }
    }
    
    public void setCommand(int slot, Command onCommand, Command offCommand){
        onCommands[slot]=onCommand;
        offCommands[slot]=offCommand;
    }
    
    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
    }
    
    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
    }
}
