package org.example.model.figure;

import java.util.ArrayList;
import java.util.List;

public class QueenFigure implements Figure {
    public Integer getWhite() {
        return isWhite;
    }

    public void setWhite(Integer white) {
        isWhite = white;
    }

    private Integer isWhite;

    public QueenFigure() {
        this.name = "NONDEFINED";
        this.attack = 0;
        this.defense = 0;
    }

    public QueenFigure(String name, Integer attack, Integer defense, Integer isWhite) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.isWhite = isWhite;
    }

    private String name;
    private Integer attack;
    private Integer defense;

    private final List<Integer> trace = new ArrayList<>();

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
    public Integer isWhite() {
        return isWhite;
    }

    @Override
    public void setTrace(String[] trace_arguments) {
        for (String traceArgument : trace_arguments) {
            Integer move = Integer.valueOf(traceArgument);
            this.trace.add(move);
            this.trace.add(move);
            this.trace.add(-move);
            this.trace.add(move);
            this.trace.add(move);
            this.trace.add(-move);
            this.trace.add(-move);
            this.trace.add(-move);
            this.trace.add(move);
            this.trace.add(0);
            this.trace.add(-move);
            this.trace.add(0);
            this.trace.add(0);
            this.trace.add(move);
            this.trace.add(0);
            this.trace.add(-move);
        }
    }

    @Override
    public List<Integer> getTrace() {
        return this.trace;
    }

}