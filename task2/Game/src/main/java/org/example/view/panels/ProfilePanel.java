package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.screens.SetLooknFeelEvent;

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

public class ProfilePanel extends JPanel implements Observable {
    private JButton Quit;
    private final String click = "click.wav";

    public ProfilePanel() {
        initComponents();
    }

    private void initComponents() {
        setMaximumSize(new Dimension(1280, 800));
        setMinimumSize(new Dimension(1280, 800));
        setPreferredSize(new Dimension(1280, 800));
        setBackground(new Color(0x003333));
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 8;
        constraints.gridheight = 1;
        Quit = new JButton();
        Quit.setOpaque(true);
        Quit.setBorderPainted(false);
        Quit.setFocusPainted(false);
        Quit.setContentAreaFilled(false);
        Quit.setVisible(true);
        Quit.addMouseListener(new MouseAdapter() {
            Observable o;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                o.notify(new PlacePanelEvent("mainscreen"));
            }

        }.SetObservable(this));
        this.add(Quit, constraints);
        this.setVisible(true);

        //---- Quit ----
        Quit.setIcon(CreateImageIcon("/images/mainscreen/quit.png", 87, 87));
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
