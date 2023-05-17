package org.example.view.panels;

import org.example.GameConfiguration;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.view.RootViewComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static org.example.GameConfiguration.*;

public class GamePanel extends JPanel implements  Observer{
    public final RootViewComponent parent;

    public GamePanel(RootViewComponent parent) {
        this.parent = parent;
        parent.register(this);
    }
    public void SetDefaultPanel(GamePanel panel) {
        SetPanelSizeColor(panel);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.RELATIVE;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 8;
        constraints.gridheight = 1;
        JButton Quit = new JButton();
        Quit.setOpaque(true);
        Quit.setBorderPainted(false);
        Quit.setFocusPainted(false);
        Quit.setContentAreaFilled(false);
        Quit.setVisible(true);
        Quit.addMouseListener(new MouseAdapter()
                              {
                                  @Override
                                  public void mouseClicked(MouseEvent e) {
                                      GamePanel.this.notify(new PlacePanelEvent(GameConfiguration.START.getPANEL_INDEX()));
                                  }

                              });
        panel.add(Quit, constraints);
        panel.setVisible(true);

        //---- Quit ----
        Quit.setIcon(CreateImageIcon("/images/general/quit.png", DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE(), DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE()));
    }

    public void notify(Event e) {
        parent.handle(e);
    }

    @Override
    public void handle(Event e) {

    }
}
