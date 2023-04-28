package org.example.observer.event.session.view;

import org.example.observer.event.session.GameSessionEvent;

public class LoadBoardRequest extends GameSessionEvent {
    public LoadBoardRequest(String[] changes) {
        this.changes = changes;
    }

    public String[] getChanges() {
        return changes;
    }

    private final String [] changes;

}
