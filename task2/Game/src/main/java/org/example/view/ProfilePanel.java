package org.example.view;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.GameConstants.*;

public class ProfilePanel extends JPanel implements Observable {
    private JButton Quit;
    private final String click = "click.wav";

    public ProfilePanel() {
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this, this);

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
