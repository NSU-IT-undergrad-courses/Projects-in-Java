package org.example.exception.Command;

public class UnknownVariableException extends CommandException{
    public UnknownVariableException(String variable) {
        super("Unknown variable: "+variable);
    }
}
