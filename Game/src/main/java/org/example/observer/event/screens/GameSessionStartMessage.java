package org.example.observer.event.screens;

import org.example.observer.event.session.GameSessionEvent;

public class GameSessionStartMessage extends GameSessionEvent {
    private final String[] names;

    public GameSessionStartMessage(String[] names) {
        this.names = names;
    }

    public String[] getFiguresNames() {
        return names;
    }
}
