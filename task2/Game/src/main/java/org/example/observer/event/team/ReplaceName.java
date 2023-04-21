package org.example.observer.event.team;

public class ReplaceName extends TeamEvent{
    String previous;
    String edited;

    public String getPrevious() {
        return previous;
    }

    public String getEdited() {
        return edited;
    }

    public ReplaceName(String previous, String edited) {
        this.previous = previous;
        this.edited = edited;
    }
}
