package org.example.observer.event.session.controller;

import org.example.observer.event.session.GameSessionEvent;

public class BoardSentMessage extends GameSessionEvent {
    public BoardSentMessage(String[] changes) {
        this.changes = changes;
    }

    public String[] getChanges() {
        return changes;
    }

    private final String [] changes;

}
