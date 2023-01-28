/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern;

import designpattern.Command.AddStaticObjectCommand;
import designpattern.Command.Remote;
import designpattern.Command.RemoveStaticObjectCommand;
import designpattern.Command.StaticObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class ButtonStore {

    SimpleButtonFactory factory;
    JFrame frame;
    JPanel panel;
    Remote remote;
    JButton[] buttons;
    int slot = 0;
    int commandSlot = 0;
    int nextCommandSlot = 0;
    String name;

    public ButtonStore(SimpleButtonFactory factory) {
        this.factory = factory;
    }

    public void displayButton(BackgroundImageJFrame frame, JButton[] buttons, String name, int xAxis, int yAxis, Remote remote) {
        //create static object
        StaticObject staticObject = null;
        staticObject = factory.createButton(frame, frame.getPanel(), remote, name, xAxis, yAxis);
        
        //insert static object into buttons
        AddStaticObjectCommand addStaticObject = null;
        RemoveStaticObjectCommand removeStateObject = null;
        addStaticObject= new AddStaticObjectCommand(staticObject);
        removeStateObject = new RemoveStaticObjectCommand(staticObject);
        if (slot < 6) {
            remote.setCommand(slot, addStaticObject, removeStateObject);
        } else {
            System.out.println("Remote slot is fulled");
        }
        if (commandSlot < 12) {
            nextCommandSlot = commandSlot + 1;
            addCommandToButton(remote, buttons[commandSlot], buttons[nextCommandSlot], frame, slot, name, frame.getPanel());            
        }else {
            System.out.println("Remote slot is fulled");
        }  
        slot++;
        commandSlot = commandSlot +2;
    }

    public void addCommandToButton(Remote remote, JButton addButton, JButton removeButton, JFrame f, int slot, String name, JPanel panel) {
        System.out.println("name button : " + name);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remote.onButtonWasPushed(slot);
            }
        });
        addButton.setText("add " + name);
        panel.add(addButton);
        panel.setComponentZOrder(addButton, 0);

        System.out.println("name :" + name);

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remote.offButtonWasPushed(slot);
            }
        });
        removeButton.setText("remove " + name);
        
        panel.add(removeButton);
        panel.setComponentZOrder(removeButton, 0);
    }
}
