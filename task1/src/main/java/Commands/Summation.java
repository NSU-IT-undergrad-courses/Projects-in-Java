package Commands;

import Context.Context;
import Exception.Command.StackNotEnoughElements;

public class Summation extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        Double a;
        Double b;
        try {
            a = context.getValue();
        }catch (Exception e){
            throw new StackNotEnoughElements("Cant execute, not enough elements on stack");
        }
        try {
            b = context.getValue();
        }catch (Exception e){
            Push push  = new Push();
            push.execute(context, new String[]{a.toString()});
            throw new StackNotEnoughElements("Cant execute, not enough elements on stack");
        }
        context.addValue(a+b);
    }
}
