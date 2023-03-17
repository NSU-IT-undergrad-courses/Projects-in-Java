package Commands;

import Context.Context;
import Exception.Command.StackNotEnoughElements;
import static java.lang.Math.sqrt;

public class Sqrt extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        Double a;
        try {
            a = context.getValue();
        }catch (Exception e){
            throw new StackNotEnoughElements("Cant execute, not enough elements on stack");
        }
        context.addValue(sqrt(a));
    }
}
