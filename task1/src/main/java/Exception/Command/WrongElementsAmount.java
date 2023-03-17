package Exception.Command;

public class WrongElementsAmount extends CommandException{
    public WrongElementsAmount(int amount, String command) {
        super("Cant execute "+command+" with amount of arguments:"+amount);
    }
}
