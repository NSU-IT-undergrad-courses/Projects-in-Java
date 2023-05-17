package org.example.controllers;

import org.example.observer.Observer;
import org.example.observer.event.Event;

public class SubController implements Observer {
    public final RootController parent;

    public SubController(RootController parent) {
        this.parent = parent;
        parent.register(this);
    }

    void notify(Event e) {
        parent.notify(e);
    }

    @Override
    public void handle(Event e) {

    }
}
