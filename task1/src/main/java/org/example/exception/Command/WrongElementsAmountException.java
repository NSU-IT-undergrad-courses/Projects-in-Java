package org.example.exception.Command;

public class WrongElementsAmountException extends CommandException{
    public WrongElementsAmountException(int amount, String command) {
        super("Cant execute "+command+" with amount of arguments:"+amount);
    }
}
