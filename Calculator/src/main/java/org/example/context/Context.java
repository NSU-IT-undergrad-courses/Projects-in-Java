package org.example.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    public Double getVariable(String name) {
        return variables.get(name);
    }

    public void addVariables(String name, Double value) {
        this.variables.put(name, value);
    }

    private final Map<String, Double> variables = new HashMap<>();
    public Double getValue() {
        return stack.pop();
    }

    public Double peekValue() {
        return stack.peek();
    }

    public void addValue(Double value) {
        this.stack.push(value);
    }

    private final Stack<Double> stack = new Stack<>();
}
