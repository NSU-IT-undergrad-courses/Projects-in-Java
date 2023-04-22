package org.example.view;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.BoardCreatorEvent;
import org.example.observer.event.screens.GameSessionStartEvent;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.screens.SetLooknFeelEvent;
import org.example.observer.event.session.GameSessionEvent;
import org.example.observer.event.session.view.ReleaseStatsListenerEvent;
import org.example.observer.event.session.controller.StatsMessage;
import org.example.observer.event.team.TeamEvent;
import org.example.view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.example.GameConfiguration.*;

public class RootViewComponent extends JFrame implements Observer, Observable {
    private final List<Observer> observers = new ArrayList<>();

    private final JPanel [] panels = new JPanel[7];

    @Override
    public void handle(Event e) {
        if (e instanceof BoardCreatorEvent){
            ((Observer)panels[BOARDCREATOR.getPANEL_INDEX()]).handle(e);
        }
        if (e instanceof TeamEvent){
            ((Observer)panels[TEAM.getPANEL_INDEX()]).handle(e);
        }
        if (e instanceof SetLooknFeelEvent) {
            SetNewLookNFeel((SetLooknFeelEvent) e);
        }
        if (e instanceof PlacePanelEvent) {
            PlacePanel(((PlacePanelEvent) e).getSource());
        }
        if (e instanceof GameSessionEvent) {
            if (e instanceof GameSessionStartEvent) {
                ((Observer)panels[OFFLINE.getPANEL_INDEX()]).handle(e);
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                PlacePanel(OFFLINE.getPANEL_INDEX());
            } else if (e instanceof StatsMessage) {
                ((Observer)panels[OFFLINE.getPANEL_INDEX()]).handle(e);
            } else if (e instanceof ReleaseStatsListenerEvent) {
                ((Observer)panels[OFFLINE.getPANEL_INDEX()]).handle(e);
            } else {
                ((Observer)panels[OFFLINE.getPANEL_INDEX()]).handle(e);
            }
        }
        this.pack();
    }

    private void SetNewLookNFeel(SetLooknFeelEvent e) {
        String LnFName = null;
        if (Objects.equals(e.getLookAndFeel(), "Windows")) {
            LnFName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        }
        if (Objects.equals(e.getLookAndFeel(), "Metal")) {
            LnFName = "javax.swing.plaf.metal.MetalLookAndFeel";
        }
        if (Objects.equals(e.getLookAndFeel(), "Motion")) {
            LnFName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        }
        this.dispose();
        if (LnFName != null) {
            try {
                UIManager.setLookAndFeel(LnFName);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
            setUndecorated(false);
        } else {
            setUndecorated(true);
        }
        SwingUtilities.updateComponentTreeUI(this);
        this.pack();
        this.setVisible(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }


    private void PlacePanel(JPanel panel) {
        panel.setVisible(true);
        this.getContentPane().removeAll();
        this.repaint();
        this.getContentPane().add(panel);
    }

    private void PlacePanel(Integer index) {
        PlacePanel(panels[index]);
    }

    public RootViewComponent() {
        SetPanels();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor cursor = toolkit.createCustomCursor(CreateImageIcon("/images/general/cursor.png", 25, 25).getImage(), new Point(0, 0), "Gaming Cursor");
        this.getContentPane().setCursor(cursor);
        Dimension dimension = toolkit.getScreenSize();
        this.setTitle("ChessRPG");
        boolean lnf = false;
        this.setUndecorated(!lnf);
        this.setSize(new Dimension(DEFAULT_X_RESOLUTION.getSIZE(), DEFAULT_Y_RESOLUTION.getSIZE()));
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setIconImage(CreateImageIcon("/images/general/logo.png", 1000, 1000).getImage());
        this.setVisible(true);
    }

    private void SetPanels() {
        panels[START.getPANEL_INDEX()] = new StartPanel();
        panels[OFFLINE.getPANEL_INDEX()] = new SessionPanel();
        panels[SETTINGS.getPANEL_INDEX()] = new SettingsPanel();
        panels[PROFILES.getPANEL_INDEX()] = new ProfilePanel();
        panels[BOARDCREATOR.getPANEL_INDEX()] = new BoardCreatorPanel();
        panels[TEAM.getPANEL_INDEX()] = new TeamPanel();
        panels[SCORES.getPANEL_INDEX()] = new ScoresPanel();
        panels[FAQ.getPANEL_INDEX()] = new FaqPanel();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
        ((Observable)panels[START.getPANEL_INDEX()]).register(o);
        ((Observable)panels[START.getPANEL_INDEX()]).register(this);

        ((Observable)panels[SETTINGS.getPANEL_INDEX()]).register(o);
        ((Observable)panels[SETTINGS.getPANEL_INDEX()]).register(this);

        ((Observable)panels[TEAM.getPANEL_INDEX()]).register(o);
        ((Observable)panels[TEAM.getPANEL_INDEX()]).register(this);

        ((Observable)panels[BOARDCREATOR.getPANEL_INDEX()]).register(o);
        ((Observable)panels[BOARDCREATOR.getPANEL_INDEX()]).register(this);

        ((Observable)panels[OFFLINE.getPANEL_INDEX()]).register(o);
        ((Observable)panels[OFFLINE.getPANEL_INDEX()]).register(this);

        ((Observable)panels[PROFILES.getPANEL_INDEX()]).register(o);
        ((Observable)panels[PROFILES.getPANEL_INDEX()]).register(this);

        ((Observable)panels[FAQ.getPANEL_INDEX()]).register(o);
        ((Observable)panels[FAQ.getPANEL_INDEX()]).register(this);

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
