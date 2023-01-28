package designpattern.Command;

public class RemoveStaticObjectCommand implements Command{
    StaticObject obj;

    public RemoveStaticObjectCommand(StaticObject obj){
        this.obj=obj;
    }
    
    @Override
    public void execute() {
        obj.removeObject();
    }
}
