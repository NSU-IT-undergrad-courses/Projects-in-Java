package Exception.Command;

public class StackNotEnoughElements extends CommandException{
    public StackNotEnoughElements(String message) {
        super(message);
    }
}
