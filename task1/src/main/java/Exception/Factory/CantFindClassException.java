package Exception.Factory;

public class CantFindClassException extends FactoryException{
    public CantFindClassException(String class_name) {
        super("Cant find command class:"+class_name);
    }
}
