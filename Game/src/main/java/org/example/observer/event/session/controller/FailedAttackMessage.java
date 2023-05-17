package org.example.observer.event.session.controller;

import org.example.observer.event.session.GameSessionEvent;

public class FailedAttackMessage extends GameSessionEvent {
    @Override
    public String getName() {
        return name;
    }


    public FailedAttackMessage(String name, Integer damage) {
        this.name = name;
        this.damage = damage;
    }

    private final String name;

    public Integer getDamage() {
        return damage;
    }

    private final Integer damage;

}
