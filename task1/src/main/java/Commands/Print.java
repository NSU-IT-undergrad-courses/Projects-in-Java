package Commands;

import Context.Context;

public class Print extends Command{
    @Override
    public void execute(Context context, String[] arguments) {
        System.out.println(context.peekValue());
    }
}
