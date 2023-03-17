package Commands;

import Context.Context;
import Exception.Command.WrongElementsAmount;
import Factory.CommandFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {
    CommandFactory factory = new CommandFactory("../config.properties");
    Context context = new Context();
    Division division = new Division();
    @Test
    void execute() {
        String [] arguments = "123 123.123".split(" ");
        division.execute(context, arguments);
        assertEquals(context.getValue(),123.123);
    }
}