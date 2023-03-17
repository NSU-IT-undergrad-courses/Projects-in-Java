package Exception.Command;

public class UnknownVariable extends CommandException{
    public UnknownVariable(String variable) {
        super("Unknown variable: "+variable);
    }
}
