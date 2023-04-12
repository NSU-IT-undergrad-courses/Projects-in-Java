package org.example.observer.event.session;

public class RequestMovesEvent extends GameSessionEvent {
    int figure_number;

    public int getFigure_number() {
        return figure_number;
    }

    public void setFigure_number(int figure_number) {
        this.figure_number = figure_number;
    }

    public RequestMovesEvent(int i) {
        this.figure_number = i;
    }
}
