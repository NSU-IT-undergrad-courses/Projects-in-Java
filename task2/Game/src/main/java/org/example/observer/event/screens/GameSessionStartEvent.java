package org.example.observer.event.screens;

import org.example.observer.event.session.GameSessionEvent;

public class GameSessionStartEvent extends GameSessionEvent {
    private final String[] names;

    public GameSessionStartEvent(String[] names) {
        this.names = names;
    }

    public String[] getFiguresNames() {
        return names;
    }
}
