package org.example.observer.event.session.controller;

import org.example.observer.event.session.GameSessionEvent;

public class FigureKilledMessage extends GameSessionEvent {
    private final Integer source;

    public String getDestination_name() {
        return destination_name;
    }

    private final String destination_name;

    public Integer getSource() {
        return source;
    }


    public Integer getDestination() {
        return destination;
    }

    private final Integer destination;

    public FigureKilledMessage(Integer source, String destination_name, Integer destination) {
        this.source = source;
        this.destination_name = destination_name;
        this.destination = destination;
    }
}
