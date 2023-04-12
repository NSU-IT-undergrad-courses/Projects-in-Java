package org.example.model.figure.types;

import org.example.model.figure.Figure;

import java.util.ArrayList;
import java.util.List;

public class DiagonalFigure implements Figure {
    public DiagonalFigure() {
        this.name = "NONDEFINED";
        this.attack = 0;
        this.defense = 0;
    }

    public DiagonalFigure(String name, Integer attack, Integer defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    private String name;
    private Integer attack;
    private Integer defense;

    private List<Integer> trace = new ArrayList<Integer>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAttack(Integer attack_value) {
        attack = attack_value;
    }

    @Override
    public Integer getAttack() {
        return attack;
    }

    @Override
    public void setDefense(Integer defense_value) {
        defense = defense_value;
    }

    @Override
    public Integer getDefense() {
        return defense;
    }

    @Override
    public void setTrace(String[] trace_arguments) {
        for (int i = 0; i < trace_arguments.length; i++) {
            Integer move = Integer.valueOf(trace_arguments[i]);
            this.trace.add(move);
            this.trace.add(move);
            this.trace.add(-move);
            this.trace.add(move);
            this.trace.add(move);
            this.trace.add(-move);
            this.trace.add(-move);
            this.trace.add(-move);
        }
    }

    @Override
    public List<Integer> getTrace() {
        return this.trace;
    }

}
