package CommandStream;

import Commands.Command;

import java.io.InputStream;
import java.util.Scanner;

public class FileCommandStream implements  CommandStream{
    private InputStream filestream;
    private Scanner scanner;
    public FileCommandStream(String file) {
        filestream = getClass().getClassLoader().getResourceAsStream(file);
        scanner = new Scanner(filestream);
    }

    @Override
    public String[] getCommandFromStream() {
        try{
            String line = scanner.nextLine();
            if (line.startsWith("#") || line.isEmpty())
                getCommandFromStream();
            String[] command = line.split(" ");
            return command;
        }
        catch(Exception e) {

            return null;
        }
    }
}
