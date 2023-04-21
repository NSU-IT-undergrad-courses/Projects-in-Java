package org.example.model.figure;

import org.example.model.figure.Figure;

import java.util.ArrayList;
import java.util.List;

public class CrosshairFigure implements Figure {
    public CrosshairFigure(String name, Integer attack, Integer defense, Integer isWhite) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    public CrosshairFigure() {
        this.name = "NONDEFINED";
        this.attack = 0;
        this.defense = 0;
    }

    private String name;
    private Integer attack;
    private Integer defense;

    public Integer getWhite() {
        return isWhite;
    }

    public void setWhite(Integer white) {
        isWhite = white;
    }

    private Integer isWhite;
    private final List<Integer> trace = new ArrayList<Integer>();

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
        for (int i = 0; i < trace_arguments.length; i++) {
            Integer move = Integer.valueOf(trace_arguments[i]);
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
