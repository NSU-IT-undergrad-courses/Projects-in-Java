package org.example.observer.event.session;

public class GameEndEvent extends  GameSessionEvent{
    public int getTurns() {
        return turns;
    }

    private int turns;
    private int defeated;

    public GameEndEvent(int defeated, int turns) {
        this.turns = turns;
        this.defeated = defeated;
    }

    public int getDefeated() {
        return defeated;
    }
}
