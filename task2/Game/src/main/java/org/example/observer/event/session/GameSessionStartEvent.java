package org.example.observer.event.session;

import org.example.observer.event.Event;

public class GameSessionStartEvent extends GameSessionEvent {
    private String[] names = new String [64];

    public GameSessionStartEvent(String[] names) {
        this.names = names;
    }

    public String [] getFiguresNames(){
        return names;
    }
}