package Factory;

import Commands.Command;
import Commands.Unknown;
import Exception.Factory.FactoryInitException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandFactory {
    public CommandFactory(String property_name){
        try {InputStream stream = getClass().getResourceAsStream(property_name);
        classes.load(stream);
        } catch (FactoryInitException e){
            throw new FactoryInitException("Error: Cannot continue work.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Command create(String command_sign) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try{
            String command_class_name = this.classes.getProperty(command_sign);
        Class<?> command_class = Class.forName("Commands."+command_class_name);
        Command command = (Command) command_class.newInstance();
        return command;
        }catch(Exception e){
            return new Unknown(command_sign);
        }

    }
    private Properties classes = new Properties();
}
