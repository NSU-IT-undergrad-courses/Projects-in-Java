package CommandStream;

import java.util.Scanner;

public class CmdCommandStream implements  CommandStream{
    @Override
    public String[] getCommandFromStream() {
        Scanner scanner = new Scanner(System.in);
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
