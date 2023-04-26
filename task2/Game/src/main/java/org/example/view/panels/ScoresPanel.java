package org.example.view.panels;

import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.view.RootViewComponent;

public class ScoresPanel extends GamePanel implements Observer {

    public ScoresPanel(RootViewComponent parent) {
        super(parent);
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this);
    }

    @Override
    public void handle(Event e) {

    }
}

