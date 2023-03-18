package org.example.exception.Command;

import java.util.Arrays;

public class UnprocessableArgumentsException extends CommandException{
    public UnprocessableArgumentsException(String [] arguments, String command) {
        super("Cant execute "+command+" with these arguments:"+ Arrays.toString(arguments));
    }
}
