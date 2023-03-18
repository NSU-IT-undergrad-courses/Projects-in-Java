package org.example.commands;

import org.example.context.Context;
import org.example.exception.Command.UnprocessableArgumentsException;
import org.example.exception.Command.WrongElementsAmountException;

public class Define extends Command {

    @Override
    public void execute(Context context, String[] arguments) {
        if (arguments.length %2 !=0){
            throw new WrongElementsAmountException(arguments.length,this.getClass().getSimpleName());
        }
        for(int i = 0; i < arguments.length;i +=2){
            try {
                context.addVariables(arguments[i], Double.valueOf(arguments[i + 1]));
            } catch (Exception e) {
                throw new UnprocessableArgumentsException(new String[]{arguments[i], arguments[i + 1]},this.getClass().getSimpleName());
            }
        }
    }
}
