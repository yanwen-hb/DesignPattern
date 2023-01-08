/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.Command;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class StaticObject{
    JFrame jFrame;
    JPanel panel;
    JLabel label;
    String imagePath;
    int xAxis;
    int yAxis;
    
    public StaticObject(JFrame jFrame, JPanel panel, String imagePath, int xAxis, int yAxis){
        this.jFrame=jFrame;
        this.panel=panel;
        this.label=new JLabel("");;
        this.imagePath=imagePath;
        this.xAxis=xAxis;
        this.yAxis=yAxis;
        this.CreateObject(); //call function to create object and button
    }
    
    public void CreateObject(){
        ImageIcon image= new ImageIcon(imagePath);
        ImageIcon image2=new ImageIcon(image.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        label.setIcon(image2);
        label.setVisible(false);
        label.setBounds(xAxis, yAxis, 80, 80);
        label.setName("staticObject");
        panel.add(label);
        panel.setComponentZOrder(label, 0);
    }
    
    public void AddObject(){
        label.setVisible(true);
    }
    
    public void RemoveObject(){
        label.setVisible(false);
    }
}
