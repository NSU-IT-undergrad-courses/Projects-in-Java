package org.example.observer.event.session;

public class RequestStatsEvent extends  GameSessionEvent{
    int figure_number;
    public RequestStatsEvent(int i) {
        this.figure_number = i;
    }

    public int getFigureNumber() {
        return figure_number;
    }
}
