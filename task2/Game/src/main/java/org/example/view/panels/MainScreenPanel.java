package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.view.Panels;
import org.example.view.listener.MainScreenListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainScreenPanel extends JPanel {
    private Dimension QuitDim = new Dimension(150,62);
    private MainScreenListener listener = new MainScreenListener();
    private Point QuitPlace = new Point(Panels.getX()/3,Panels.getY()*3/4);
    public MainScreenPanel() {
        this.setBackground(Color.black);
        JButton quit = new JButton("");
        quit.setBorderPainted(false);
        quit.setFocusPainted(false);
        quit.setContentAreaFilled(false);
        quit.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/buttonicons/quitlogo150_62.png"))));
        quit.setLocation(QuitPlace);
        quit.setSize(QuitDim);
        this.add(quit);
    }

    public void register(Observer o) {
        listener.register(o);
    }

}
