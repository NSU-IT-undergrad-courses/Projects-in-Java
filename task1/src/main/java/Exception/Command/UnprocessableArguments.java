package Exception.Command;

import java.util.Arrays;

public class UnprocessableArguments extends CommandException{
    public UnprocessableArguments(String [] arguments, String command) {
        super("Cant execute "+command+" with these arguments:"+ Arrays.toString(arguments));
    }
}
