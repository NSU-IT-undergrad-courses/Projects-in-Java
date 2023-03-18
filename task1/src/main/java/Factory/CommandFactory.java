package Factory;

import Commands.Command;
import Exception.Factory.CantFindClassException;
import Exception.Factory.FactoryInitException;

import java.io.InputStream;
import java.util.Properties;

public class CommandFactory {
    public CommandFactory(String property_name){
        try {InputStream stream = getClass().getResourceAsStream(property_name);
        classes.load(stream);
        } catch (Exception e){
            throw new FactoryInitException("Error, couldn't init fabric. Cannot continue work.");
        }
    }
    public Command create(String command_sign){
        try{
            String command_class_name = this.classes.getProperty(command_sign);
        Class<?> command_class = Class.forName("Commands."+command_class_name);
            return (Command) command_class.newInstance();
        }catch(Exception e){
            throw new CantFindClassException(command_sign);
        }

    }
    private final Properties classes = new Properties();
}
