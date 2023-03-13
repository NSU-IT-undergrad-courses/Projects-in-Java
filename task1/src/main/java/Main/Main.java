package Main;

import CommandStream.*;
import Commands.Command;
import Context.Context;
import Factory.CommandFactory;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the source file or press [ENTER] for console mode: ");
        String [] arguments = scanner.nextLine().split(" ");
        CommandStream stream;
        if ((arguments.length != 1) || Objects.equals(arguments[0], "")){
            if (Objects.equals(arguments[0], "")){
                System.out.println("Switching to console mode...");
            }
            else {
                System.out.println("Cannot open file: " + Arrays.toString(arguments) + " , running console mode...");
            }
            System.out.println("========================================================");
            stream = new CmdCommandStream();
        }
        else{
            try {
                stream = new FileCommandStream(arguments[0]);
                System.out.println("Opened file: "+ arguments[0] +" , running file mode");
                System.out.println("========================================================");
            } catch (Exception e) {
                System.out.println("Cant find a file "+arguments[0]+" , running in console mode");
                System.out.println("========================================================");
                stream = new CmdCommandStream();
            }
        }
        CommandFactory factory;
        factory = new CommandFactory("../config.properties");
        Context context = new Context();
        String [] line;
        while ((line = stream.getCommandFromStream()) != null) {
            Command command;
            command = factory.create(line[0].toUpperCase());
            List<String> list = new ArrayList<>(Arrays.asList(line));
            list.remove(0);
            line = list.toArray(new String[list.size()]);
            command.execute(context, line);
        }
    }
}