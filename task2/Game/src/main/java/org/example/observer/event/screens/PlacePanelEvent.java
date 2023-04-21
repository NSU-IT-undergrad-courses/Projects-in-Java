package org.example.observer.event.screens;

import org.example.observer.event.Event;

public class PlacePanelEvent extends Event {
    private final Integer source;

    public PlacePanelEvent(Integer source) {
        this.source = source;
    }

    public Integer getSource() {
        return source;
    }
}
