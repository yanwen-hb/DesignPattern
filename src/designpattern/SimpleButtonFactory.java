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
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class SimpleButtonFactory {
//    Point pt = Point.getInstance(); 
    
    public StaticObject createButton(JFrame jFrame, JPanel panel, Remote remote, String name, int xAxis, int yAxis){
        StaticObject staticObject = null; 
        String imagePath = "assets\\\\" + name + ".png";
        staticObject =new StaticObject(jFrame, panel, imagePath, xAxis, yAxis); 
        return staticObject;
    }
    
}
