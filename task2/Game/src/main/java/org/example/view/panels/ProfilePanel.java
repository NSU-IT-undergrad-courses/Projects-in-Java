package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.GameConfiguration.SetDefaultPanel;

public class ProfilePanel extends JPanel implements Observable {

    public ProfilePanel() {
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this, this);

    }

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void remove(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notify(Event e) {
        for (Observer o : observers) {
            o.handle(e);
        }
    }
}
