package Commands;

import Context.Context;

public class Unknown extends Command{

    public Unknown(String name) {
        command = name;
    }

    @Override
    public void execute(Context context, String[] arguments) {
        System.out.println("You tried to do some illegal shit, unknown command: "+command);
    }
    private String command;
}
