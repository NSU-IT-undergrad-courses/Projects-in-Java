package Commands;

import Context.Context;
import Exception.Command.StackNotEnoughElements;

public class Print extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        try {
            System.out.println(context.peekValue());
        } catch(Exception e){
            throw new StackNotEnoughElements("Not enough elements on stack for command: "+this.getClass().getSimpleName());
        }
    }
}
