package org.example.commands;

import org.example.context.Context;
import org.example.exception.Command.StackNotEnoughElementsException;

public class Print extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        try {
            System.out.println(context.peekValue());
        } catch(Exception e){
            throw new StackNotEnoughElementsException("Not enough elements on stack for command: "+this.getClass().getSimpleName());
        }
    }
}
