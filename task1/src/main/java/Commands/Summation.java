package Commands;

import Context.Context;

public class Summation extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        Double a = context.getValue();
        Double b = context.getValue();
        context.addValue(a+b);
    }
}
