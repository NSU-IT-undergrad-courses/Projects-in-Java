package org.example.exception.Command;

public class StackNotEnoughElementsException extends CommandException{
    public StackNotEnoughElementsException(String message) {
        super(message);
    }
}
