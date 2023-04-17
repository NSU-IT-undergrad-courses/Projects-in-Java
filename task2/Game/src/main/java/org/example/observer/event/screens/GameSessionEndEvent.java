package org.example.observer.event.screens;

import org.example.observer.event.session.GameSessionEvent;

public class GameSessionEndEvent extends GameSessionEvent {
    public int getTurns() {
        return turns;
    }

    private final int turns;
    private final int defeated;

    public GameSessionEndEvent(int defeated, int turns) {
        this.turns = turns;
        this.defeated = defeated;
    }

    public int getDefeated() {
        return defeated;
    }
}
