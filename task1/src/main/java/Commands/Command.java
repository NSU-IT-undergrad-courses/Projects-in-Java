package Commands;

import Context.Context;

public abstract class Command {
    public abstract void execute(Context context, String [] arguments);
}
