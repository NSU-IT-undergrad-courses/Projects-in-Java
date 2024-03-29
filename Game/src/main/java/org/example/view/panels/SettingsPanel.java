package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.SetLooknFeelEvent;
import org.example.view.RootViewComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import static org.example.GameConfiguration.*;
import static org.example.GameConfiguration.CreateImageIcon;

public class SettingsPanel extends GamePanel implements Observer {
    private final String[] LookAndFeelOptions = {"Default", "Windows", "Metal", "Motion"};

    public SettingsPanel(RootViewComponent parent) {
        super(parent);
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this);
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

                @Override
                public void mouseClicked(MouseEvent e) {
                    SettingsPanel.this.notify(new SetLooknFeelEvent((JRadioButton) e.getSource()));
                }
            });
            radio.setOpaque(true);
            radio.setBorderPainted(false);
            radio.setFocusPainted(false);
            radio.setContentAreaFilled(false);
            radio.setVisible(true);
            radio.setPreferredSize(new Dimension(400, 50));
            radio.setForeground(Color.white);
            String radioLnF = "switch.png";
            radio.setIcon(CreateImageIcon(IMG_SETTINGS.getDEFAULT_PATH_RESOURCE()+ radioLnF, DEFAULT_RADIO_BUTTON_SIZE.getSIZE(), DEFAULT_RADIO_BUTTON_SIZE.getSIZE()));
            String radioLnFSelected = "switchon.png";
            radio.setSelectedIcon(CreateImageIcon(IMG_SETTINGS.getDEFAULT_PATH_RESOURCE() + radioLnFSelected, DEFAULT_RADIO_BUTTON_SIZE.getSIZE(), DEFAULT_RADIO_BUTTON_SIZE.getSIZE()));
            LnF.add(radio);
            this.add(radio, constraints);
            constraints.gridy++;
        }
    }

    @Override
    public void handle(Event e) {

    }
}
