package org.example.view;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.view.BoardTeamsRequest;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.screens.SetLooknFeelEvent;
import org.example.observer.event.team.view.TeamsRequest;
import org.example.view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.GameConfiguration.*;

public class RootViewComponent extends JFrame implements Observer, Observable {
    private final List<Observer> observers = new ArrayList<>();

    private final List<GamePanel> panels = new ArrayList<>(PANELS_AMOUNT){};

    @Override
    public void handle(Event e) {
        if (e instanceof SetLooknFeelEvent) {
            SetNewLookNFeel((SetLooknFeelEvent) e);
        }
        else if (e instanceof PlacePanelEvent) {
            PlacePanel(((PlacePanelEvent) e).getSource());
        }
        else {
            notify(e);
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
        PlacePanel(panels.get(index));
    }

    public RootViewComponent() {
        boolean lnf = false;
        this.setUndecorated(!lnf);
        SetStartLnF();
        SetPanels();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor cursor = toolkit.createCustomCursor(CreateImageIcon("/images/general/cursor.png", 25, 25).getImage(), new Point(0, 0), "Gaming Cursor");
        this.getContentPane().setCursor(cursor);
        Dimension dimension = toolkit.getScreenSize();
        this.setTitle("ChessRPG");
        this.setSize(new Dimension(DEFAULT_X_RESOLUTION.getSIZE(), DEFAULT_Y_RESOLUTION.getSIZE()));
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setIconImage(CreateImageIcon("/images/general/logo.png", 1000, 1000).getImage());
        this.setVisible(true);
    }

    private void SetStartLnF() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (!OS.contains("win")){
            try {
                UIManager.setLookAndFeel(DEFAULT_LNF);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void SetPanels() {
        for (int i = 0; i < PANELS_AMOUNT; i++){
            panels.add(new StartPanel(this));
        }
        panels.set(START.getPANEL_INDEX(), new StartPanel(this));
        panels.set(OFFLINE.getPANEL_INDEX(), new SessionPanel(this));
        panels.set(SETTINGS.getPANEL_INDEX(), new SettingsPanel(this));
        panels.set(PROFILES.getPANEL_INDEX(), new ProfilePanel(this));
        panels.set(BOARDCREATOR.getPANEL_INDEX(), new BoardCreatorPanel(this));
        panels.set(TEAM.getPANEL_INDEX(), new TeamPanel(this));
        panels.set(SCORES.getPANEL_INDEX(), new ScoresPanel(this));
        panels.set(FAQ.getPANEL_INDEX(), new FaqPanel(this));
    }

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
        if (isMessage(e)){
            for (Observer o : observers) {
                if (isController(o))
                    o.handle(e);
            }
        }
        else{
            for (Observer o : observers) {
                if (!isController(o))
                    o.handle(e);
            }
        }
    }

    private boolean isController(Observer o) {
        return o.getClass().getSimpleName().contains("Controller");
    }

    private boolean isMessage(Event e) {
        return e.getClass().getSimpleName().contains("Message") || e.getClass().getSimpleName().contains("Event");
    }
}
