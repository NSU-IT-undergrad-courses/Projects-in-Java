package Commands;

import Context.Context;
import Exception.Command.WrongElementsAmount;
import Factory.CommandFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefineTest {
    CommandFactory factory = new CommandFactory("../config.properties");
    Context context = new Context();
    Define define = new Define();
    @Test
    void execute() {
        String [] arguments = "a 1 b 2".split(" ");
        define.execute(context, arguments);
        assertEquals(context.getVariable("a"),1);
        assertEquals(context.getVariable("b"),2);
        arguments = "a 1.0 b 2.23".split(" ");
        define.execute(context, arguments);
        assertEquals(context.getVariable("a"),1.0);
        assertEquals(context.getVariable("b"),2.23);
    }
}