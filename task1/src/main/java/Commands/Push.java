package Commands;

import Context.Context;

import java.util.Arrays;

public class Push extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
       for (int i = 0; i < arguments.length;i++){
           if (isNumeric(arguments[i])){
               context.addValue(Double.valueOf(arguments[i]));
           }
           else{
               Double value = context.getVariable(arguments[i]);
               context.addValue(value);
           }
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
