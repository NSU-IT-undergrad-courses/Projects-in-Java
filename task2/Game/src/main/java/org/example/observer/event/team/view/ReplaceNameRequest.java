package org.example.observer.event.team.view;

import org.example.observer.event.team.TeamEvent;

public class ReplaceNameRequest extends TeamEvent {
    String previous;
    String edited;

    public String getPrevious() {
        return previous;
    }

    public String getEdited() {
        return edited;
    }

    public ReplaceNameRequest(String previous, String edited) {
        this.previous = previous;
        this.edited = edited;
    }
}
