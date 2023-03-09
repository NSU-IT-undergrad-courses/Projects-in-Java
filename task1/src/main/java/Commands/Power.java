package Commands;

import Context.Context;

import static java.lang.Math.pow;

public class Power extends Command{

    @Override
    public void execute(Context context, String[] arguments) {
        Double a = context.getValue();
        Double power = Double.valueOf(arguments[0]);
        context.addValue(pow(a,power));
    }
}
