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

public class SettingsPanel extends JPanel implements Observable {
    private JButton Quit;
    private final String[] LookAndFeelOptions = {"Default", "Windows", "Metal", "Motion"};
    private final String radioLnF = "switch.png";
    private final String radioLnFSelected = "switchon.png";

    public SettingsPanel() {
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

        constraints.weightx = 1.0f;
        constraints.weighty = 0.8f;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridwidth = 5;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        JLabel LookAndFeel = new JLabel("Look and Feel:");
        LookAndFeel.setPreferredSize(new Dimension(400, 60));
        LookAndFeel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        LookAndFeel.setForeground(Color.white);
        this.add(LookAndFeel, constraints);
        ButtonGroup LnF = new ButtonGroup();
        constraints.gridx = 0;
        constraints.gridwidth = 5;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        for (int i = 0; i < LookAndFeelOptions.length; i++) {
            JRadioButton radio = new JRadioButton(LookAndFeelOptions[i]);
            if (i == 0) {
                radio.setSelected(true);
            }
            radio.addMouseListener(new MouseAdapter() {
                Observable o;

                public MouseListener SetObservable(Observable o) {
                    this.o = o;
                    return this;
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    SettingsPanel.this.notify(new SetLooknFeelEvent((JRadioButton) e.getSource()));
                }
            }.SetObservable(this));
            radio.setOpaque(true);
            radio.setBorderPainted(false);
            radio.setFocusPainted(false);
            radio.setContentAreaFilled(false);
            radio.setVisible(true);
            radio.setPreferredSize(new Dimension(400, 50));
            radio.setForeground(Color.white);
            radio.setIcon(CreateImageIcon("/images/" + radioLnF, 50, 50));
            radio.setSelectedIcon(CreateImageIcon("/images/" + radioLnFSelected, 50, 50));
            LnF.add(radio);
            this.add(radio, constraints);
            constraints.gridy++;
        }
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
                SettingsPanel.this.setVisible(false);
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
