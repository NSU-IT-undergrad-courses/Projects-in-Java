package org.example.commands;

import org.example.context.Context;
import org.example.exception.Command.UnknownVariableException;
import org.example.exception.Command.UnprocessableArgumentsException;

public class Push extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
       for (int i = 0; i < arguments.length;i++){
           if (isNumeric(arguments[i])){
               try {
                   context.addValue(Double.valueOf(arguments[i]));
               } catch (Exception e) {
                   throw new UnprocessableArgumentsException(new String[]{arguments[i], arguments[i + 1]},this.getClass().getSimpleName());
               }
           }
           else{
               Double value;
               try{
                   value = context.getVariable(arguments[i]);
                   if (value == null){
                       throw new UnknownVariableException(arguments[i]);
                   }
                   context.addValue(value);
               }catch(Exception e){
                   throw new UnknownVariableException(arguments[i]);
               }
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
