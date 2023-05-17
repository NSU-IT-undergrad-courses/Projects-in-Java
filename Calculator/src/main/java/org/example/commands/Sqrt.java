package org.example.commands;

import org.example.context.Context;
import org.example.exception.Command.StackNotEnoughElementsException;
import static java.lang.Math.sqrt;

public class Sqrt extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        Double a;
        try {
            a = context.getValue();
        }catch (Exception e){
            throw new StackNotEnoughElementsException("Cant execute, not enough elements on stack");
        }
        context.addValue(sqrt(a));
    }
}
