package org.example.observer.event.session.controller;

import org.example.observer.event.session.GameSessionEvent;

public class NamesMessage extends GameSessionEvent {

    String[] names;

    public NamesMessage(String[] names) {
        this.names = names;
    }
}
