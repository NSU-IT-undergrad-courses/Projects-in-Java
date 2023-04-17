package org.example.observer.event.screens;

import org.example.observer.event.Event;

public class PlacePanelEvent extends Event {
    private final String source;

    public PlacePanelEvent(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
