package org.example.observer.event.session.view;

import org.example.observer.event.session.GameSessionEvent;

public class FigureChosenListenerEvent extends GameSessionEvent {
    private final Integer index;

    public Integer getIndex() {
        return index;
    }

    public FigureChosenListenerEvent(Integer index) {
        this.index = index;

    }
}
