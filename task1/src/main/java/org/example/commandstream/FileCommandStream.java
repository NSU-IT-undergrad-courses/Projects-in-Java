package org.example.commandstream;

import org.example.exception.CommandStream.FileCommandStream.NotFoundException;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class FileCommandStream implements  CommandStream{
    private final Scanner scanner;
    public FileCommandStream(String file) {
        try {
            InputStream filestream = getClass().getClassLoader().getResourceAsStream(file);
            scanner = new Scanner(Objects.requireNonNull(filestream));
        }catch(Exception e){
            throw new NotFoundException(file);
        }
    }

    @Override
    public String[] getCommandFromStream() {
        try{
            String line = scanner.nextLine();
            if (line.startsWith("#") || line.isEmpty())
                getCommandFromStream();
            return line.split(" ");
        }
        catch(Exception e) {

            return null;
        }
    }
}
