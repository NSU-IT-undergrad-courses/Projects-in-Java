package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.GameConfiguration.*;

public class FaqPanel extends JPanel implements Observable, Observer {

    public FaqPanel() {
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this, this);
        JTextArea faq = new JTextArea();
        int textwidth = 800;
        int textheight = 50;
        faq.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        faq.setBackground(Color.white);
        faq.append("Просто играйте в шахматы и все будет");
        faq.setPreferredSize(new Dimension(textwidth, textheight));
        this.add(faq);

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

    @Override
    public void handle(Event e) {

    }
}
