package org.example.view;

import org.example.GameConstants;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.PlacePanelEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoresPanel extends JPanel implements Observable {
    private JButton Quit;

    public ScoresPanel() {
        initComponents();
    }

    private void initComponents() {
        GameConstants.SetDefaultPanel(this,this);
    }

    private ImageIcon CreateImageIcon(String path, int x, int y) {
        BufferedImage name = null;
        try {
            name = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        assert name != null;
        Image dimg = name.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
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

