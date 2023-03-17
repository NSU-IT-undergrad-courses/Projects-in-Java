package Commands;

import Context.Context;
import Exception.Command.WrongElementsAmount;
import Factory.CommandFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PushTest {
    CommandFactory factory = new CommandFactory("../config.properties");
    Context context = new Context();
    Push push = new Push();
    @Test
    void execute() {
        String [] arguments = "123 123.123".split(" ");
        push.execute(context, arguments);
        assertEquals(context.getValue(),123.123);
        assertEquals(context.getValue(),123.0);
    }
}