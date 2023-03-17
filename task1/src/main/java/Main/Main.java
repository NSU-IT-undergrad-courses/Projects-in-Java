package Main;

import CommandStream.*;
import Commands.Command;
import Context.Context;
import Exception.Command.CommandException;
import Exception.CommandStream.FileCommandStream.FleCommandStreamException;
import Exception.Factory.FactoryException;
import Factory.CommandFactory;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Logger log = Logger.getLogger(String.valueOf(Main.class));
        log.info("Started the calculator");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the source file or press [ENTER] for console mode: ");
        String [] arguments = scanner.nextLine().split(" ");
        CommandStream stream;
        if ((arguments.length != 1) || Objects.equals(arguments[0], "")){
            if (Objects.equals(arguments[0], "")){
                System.out.println("Switching to console mode...");
                log.info("Switching to console mode...");
            }
            else {
                System.out.println("Wrong arguments, "+"running console mode...");
                log.warning("Wrong arguments, only file name needed: "+Arrays.toString(arguments));
                log.info("Switching to console mode...");
            }
            System.out.println("========================================================");
            stream = new CmdCommandStream();

        }
        else{
            try {
                stream = new FileCommandStream(arguments[0]);
                log.info("Opened file: "+arguments[0]);
                log.info("Running file mode");
                System.out.println("Opened file: "+ arguments[0] +" , running file mode");
                System.out.println("========================================================");
            } catch (FleCommandStreamException e) {
                log.warning(e.getMessage());
                System.out.println(e.getMessage()+" , running in console mode");
                log.info("Switching to console mode...");
                System.out.println("========================================================");
                stream = new CmdCommandStream();
            }
        }
        CommandFactory factory = null;
        try {
            factory = new CommandFactory("../config.properties");
        }catch (FactoryException e){
            log.log(Level.SEVERE,e.getMessage());
            System.exit(1);
        }
        Context context = new Context();
        String [] line;
        while ((line = stream.getCommandFromStream()) != null) {
            Command command;
            try {
                command = factory.create(line[0].toUpperCase());
            }catch (FactoryException e){
                log.warning(e.getMessage());
                continue;
            }
            List<String> list = new ArrayList<>(Arrays.asList(line));
            list.remove(0);
            line = list.toArray(new String[0]);
            try{
            log.info("Executing "+command.getClass().getSimpleName()+" with arguments"+ Arrays.toString(line));
            command.execute(context, line);
            }catch (CommandException e){
                log.warning(e.getMessage());
            }catch (Exception e){
                log.warning(e.getMessage());
            }
        }
        log.info("Stopping the calculator");
    }
}