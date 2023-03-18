package org.example.commands;

import org.example.context.Context;
import org.example.exception.Command.StackNotEnoughElementsException;

public class Subtraction extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        Double a;
        Double b;
        try {
            a = context.getValue();
        }catch (Exception e){
            throw new StackNotEnoughElementsException("Cant execute, not enough elements on stack");
        }
        try {
            b = context.getValue();
        }catch (Exception e){
            Push push  = new Push();
            push.execute(context, new String[]{a.toString()});
            throw new StackNotEnoughElementsException("Cant execute, not enough elements on stack");
        }
        context.addValue(a-b);
    }
}
