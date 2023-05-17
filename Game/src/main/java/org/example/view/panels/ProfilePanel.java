package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.view.RootViewComponent;

public class ProfilePanel extends GamePanel {

    public ProfilePanel(RootViewComponent parent) {
        super(parent);
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this);

    }

}
