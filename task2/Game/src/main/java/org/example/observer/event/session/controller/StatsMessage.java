package org.example.observer.event.session.controller;

import org.example.observer.event.session.GameSessionEvent;

public class StatsMessage extends GameSessionEvent {
    @Override
    public String getName() {
        return name;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getDefense() {
        return defense;
    }

    String name;
    Integer attack;
    Integer defense;

    public StatsMessage(String name, Integer attack, Integer defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }
}
