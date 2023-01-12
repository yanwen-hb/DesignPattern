/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern.Command;

public class AddStaticObjectCommand implements Command{
    StaticObject obj;

    public AddStaticObjectCommand(StaticObject obj){
        this.obj=obj;
    }
    
    @Override
    public void execute() {
        obj.addObject();
    }
    
}