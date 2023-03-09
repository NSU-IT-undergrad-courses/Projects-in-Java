package Main;

import CommandStream.*;
import Commands.Command;
import Context.Context;
import Factory.CommandFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        CommandStream stream;
        if (args.length != 1){
            System.out.println("Incorrect cmd arguments: "+ Arrays.toString(args) +" , running console mode");
            stream = new CmdCommandStream();
        }
        else{
            try {
                stream = new FileCommandStream(args[0]);
            } catch (Exception e) {
                throw new RuntimeException("Cant find a file"+args[0]+" , running in console mode");
            }
        }
        CommandFactory factory = null;
        try {
            factory = new CommandFactory("../config.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Context context = new Context();
        String [] line = null;
        while ((line = stream.getCommandFromStream()) != null) {
            Command command = null;
            command = factory.create(line[0].toUpperCase());
            List<String> list = new ArrayList<>(Arrays.asList(line));
            list.remove(0);
            line = list.toArray(new String[list.size()]);
            command.execute(context, line);
        }
    }
}