package org.example.observer.event.session.view;

import org.example.observer.event.session.GameSessionEvent;

public class MovesRequest extends GameSessionEvent {
    int figure_number;

    public int getFigure_number() {
        return figure_number;
    }

    public MovesRequest(int i) {
        this.figure_number = i;
    }
}
