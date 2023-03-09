package Commands;

import Context.Context;

import static java.lang.Math.sqrt;

public class Sqrt extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        Double a = context.getValue();
        context.addValue(sqrt(a));
    }
}
