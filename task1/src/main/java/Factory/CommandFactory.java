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
            //need log
        }
    }
    public Command create(String command_sign) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try{
            String command_class_name = this.classes.getProperty(command_sign);
        Class<?> command_class = Class.forName("Commands."+command_class_name);
        Command command = (Command) command_class.newInstance();
        return command;
        }catch(Exception e){
            throw new CantFindClassException(command_sign);
        }

    }
    private Properties classes = new Properties();
}
