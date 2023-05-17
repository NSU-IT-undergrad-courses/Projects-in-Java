package org.example.observer.event.session.view;

import org.example.observer.event.session.GameSessionEvent;

public class StatsRequest extends GameSessionEvent {
    int figure_number;

    public StatsRequest(int i) {
        this.figure_number = i;
    }

    public int getFigureNumber() {
        return figure_number;
    }
}
