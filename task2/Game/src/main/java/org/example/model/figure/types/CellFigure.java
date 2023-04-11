package org.example.model.figure.types;

import org.example.model.figure.Figure;

public class CellFigure implements Figure {
    public CellFigure(String name, Integer attack, Integer defense){
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    private String name;
    private Integer attack;
    private Integer defense;

    private String move;

    private String trace;

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
    public String getMove() {
        return move;
    }

    @Override
    public String getTrace() {
        return this.trace;
    }

    public CellFigure() {
        this.name = "Cell";
        this.attack = 0;
        this.defense = 0;
        this.trace = "Stand";
    }
}
