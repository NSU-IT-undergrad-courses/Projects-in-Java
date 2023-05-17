package org.example.observer.event.session.view;

import org.example.observer.event.session.GameSessionEvent;

public class ChooseFigureListenerRequest extends GameSessionEvent {
    private final Integer index;

    public Integer getIndex() {
        return index;
    }

    public ChooseFigureListenerRequest(Integer index) {
        this.index = index;

    }
}
