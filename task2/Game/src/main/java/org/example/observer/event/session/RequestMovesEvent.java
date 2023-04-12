package org.example.observer.event.session;

public class RequestMovesEvent extends GameSessionEvent{
    int figure_number;
    public RequestMovesEvent(int i) {
        this.figure_number = i;
    }
}
