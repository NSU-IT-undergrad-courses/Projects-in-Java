package Commands;

import Context.Context;
import Exception.Command.StackNotEnoughElements;

import static java.lang.Math.pow;

public class Power extends Command{

    @Override
    public void execute(Context context, String[] arguments) {
        Double a;
        Double power = Double.valueOf(arguments[0]);
        try {
            a = context.getValue();
        }catch (Exception e){
            throw new StackNotEnoughElements("Cant execute, not enough elements on stack");
        }
        context.addValue(pow(a,power));
    }
}
