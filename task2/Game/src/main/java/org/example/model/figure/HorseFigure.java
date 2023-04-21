package org.example.model.figure;

import org.example.model.figure.Figure;

import java.util.ArrayList;
import java.util.List;

public class HorseFigure implements Figure {
    public Integer getWhite() {
        return isWhite;
    }

    public void setWhite(Integer white) {
        isWhite = white;
    }

    private Integer isWhite;

    public HorseFigure(String name, Integer attack, Integer defense, Integer isWhite) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.isWhite = isWhite;
    }

    private String name;
    private Integer attack;
    private Integer defense;
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

    public HorseFigure() {
        this.name = "NONDEFINED";
        this.attack = 0;
        this.defense = 0;
    }

    @Override
    public void setTrace(String[] trace_arguments) {
        this.trace.add(1);
        this.trace.add(2);

        this.trace.add(-1);
        this.trace.add(2);

        this.trace.add(1);
        this.trace.add(-2);

        this.trace.add(-1);
        this.trace.add(-2);


        this.trace.add(2);
        this.trace.add(1);

        this.trace.add(-2);
        this.trace.add(1);

        this.trace.add(2);
        this.trace.add(-1);

        this.trace.add(-2);
        this.trace.add(-1);

    }


    @Override
    public List<Integer> getTrace() {
        return trace;
    }

}
