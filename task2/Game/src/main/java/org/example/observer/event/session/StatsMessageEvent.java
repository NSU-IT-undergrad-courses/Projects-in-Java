package org.example.observer.event.session;

public class StatsMessageEvent extends GameSessionEvent {
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

    public StatsMessageEvent(String name, Integer attack, Integer defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }
}
