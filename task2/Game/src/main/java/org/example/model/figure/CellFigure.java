package org.example.model.figure;

import java.util.ArrayList;
import java.util.List;

public class CellFigure implements Figure {

    private String name;
    private Integer attack;
    private Integer defense;

    private final ArrayList<Integer> trace = new ArrayList<>();

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
        return null;
    }

    @Override
    public void setWhite(Integer isWhite) {

    }

    @Override
    public void setTrace(String[] trace_arguments) {
        trace.add(0, 0);
    }

    @Override
    public List<Integer> getTrace() {
        return this.trace;
    }

    public CellFigure() {
        this.name = "cell";
        this.attack = 0;
        this.defense = 0;
        this.trace.add(0);
        this.trace.add(0);
    }
}
