import Commands.*;
import Context.Context;
import Factory.CommandFactory;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {
    CommandFactory factory = new CommandFactory("../config.properties");
    Context context = new Context();
    @org.junit.jupiter.api.Test
    void Define() {
        Define define = new Define();
        String [] arguments = "a 1 b 2".split(" ");
        define.execute(context, arguments);
        assertEquals(context.getVariable("a"),1);
        assertEquals(context.getVariable("b"),2);
        arguments = "a 1.0 b 2.23".split(" ");
        define.execute(context, arguments);
        assertEquals(context.getVariable("a"),1.0);
        assertEquals(context.getVariable("b"),2.23);
    }
    @org.junit.jupiter.api.Test
    void Push() {
        Push push = new Push();
        String [] arguments = "123 123.123".split(" ");
        push.execute(context, arguments);
        assertEquals(context.getValue(),123.123);
        assertEquals(context.getValue(),123.0);
    }
    @org.junit.jupiter.api.Test
    void Division() {
        Division division = new Division();
        Push push = new Push();
        String [] arguments = "123 123.123".split(" ");
        push.execute(context, arguments);
        division.execute(context, arguments);
        assertEquals(context.getValue(),1,1E-2);
    }
    @org.junit.jupiter.api.Test
    void Multiplication() {
        Multiplication mul = new Multiplication();
        Push push = new Push();
        String [] arguments = "2 3.5".split(" ");
        push.execute(context, arguments);
        mul.execute(context, arguments);
        assertEquals(context.getValue(),7);
    }

    @org.junit.jupiter.api.Test
    void Power() {
        Power power = new Power();
        Push push = new Push();
        String [] arguments = "2 3 1.5".split(" ");
        push.execute(context, arguments);
        arguments = "2".split(" ");
        power.execute(context, arguments);
        assertEquals(context.getValue(),2.25);
        power.execute(context, arguments);
        assertEquals(context.getValue(),9);
    }

    @org.junit.jupiter.api.Test
    void Sqrt() {
        Sqrt Sqrt = new Sqrt();
        Push push = new Push();
        String [] arguments = "2 3 2.25".split(" ");
        push.execute(context, arguments);
        arguments = "2".split(" ");
        Sqrt.execute(context, arguments);
        assertEquals(context.getValue(),1.5);
    }

    @org.junit.jupiter.api.Test
    void Sub() {
        Substraction substraction = new Substraction();
        Push push = new Push();
        String [] arguments = "2 3 2.25".split(" ");
        push.execute(context, arguments);
        arguments = "2".split(" ");
        substraction.execute(context, arguments);
        assertEquals(context.getValue(),-0.75);
    }

    @org.junit.jupiter.api.Test
    void Sum() {
        Summation Summation = new Summation();
        Push push = new Push();
        String [] arguments = "2 3 2.25".split(" ");
        push.execute(context, arguments);
        arguments = "2".split(" ");
        Summation.execute(context, arguments);
        assertEquals(context.peekValue(),5.25);
        Summation.execute(context, arguments);
        assertEquals(context.getValue(),7.25);
    }

    
}

