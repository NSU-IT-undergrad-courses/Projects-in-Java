package org.example.observer.event.session;

public class GameSessionStartEvent extends GameSessionEvent {
    private String[] names = new String[64];

    public GameSessionStartEvent(String[] names) {
        this.names = names;
    }

    public String[] getFiguresNames() {
        return names;
    }
}
