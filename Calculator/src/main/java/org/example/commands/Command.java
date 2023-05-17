package org.example.commands;

import org.example.context.Context;

public abstract class Command {
    public abstract void execute(Context context, String [] arguments);
}
