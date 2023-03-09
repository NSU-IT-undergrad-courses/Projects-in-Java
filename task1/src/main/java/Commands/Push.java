package Commands;

import Context.Context;

import java.util.Arrays;

public class Push extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        if (isNumeric(arguments[0])){
            context.addValue(Double.valueOf(arguments[0]));
        }
        else{
            Double value = context.getVariable(arguments[0]);
            context.addValue(value);
        }
    }
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
