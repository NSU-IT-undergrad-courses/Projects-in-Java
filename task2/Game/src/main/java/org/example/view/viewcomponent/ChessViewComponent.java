package org.example.view.viewcomponent;

import org.example.controller.boardcreator.BoardCreator;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.AvailableTeamsEvent;
import org.example.observer.event.screens.GameSessionStartEvent;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.screens.SetLooknFeelEvent;
import org.example.observer.event.session.GameSessionEvent;
import org.example.observer.event.session.ReleaseStatsEvent;
import org.example.observer.event.session.StatsMessageEvent;
import org.example.view.Panels;
import org.example.view.panels.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ChessViewComponent extends JFrame implements Observer, Observable {
    private final List<Observer> observers = new ArrayList<>();
    private final MainScreenPanel mainscreen = new MainScreenPanel();
    private final GameSessionPanel gamesession = new GameSessionPanel();
    private final SettingsPanel settings = new SettingsPanel();
    private final ProfilePanel profiles = new ProfilePanel();
    private final BoardCreatorPanel boardcreator = new BoardCreatorPanel();
    private final TeamPanel team = new TeamPanel();
    private final ScoresPanel scores = new ScoresPanel();

    public void setLnf(Boolean lnf) {
        this.lnf = lnf;
    }

    private Boolean lnf = false;

    @Override
    public void handle(Event e) {
        if (e instanceof AvailableTeamsEvent){
            boardcreator.handle(e);
        }
        if (e instanceof SetLooknFeelEvent) {
            String LnFName = null;
            if (Objects.equals(((SetLooknFeelEvent) e).getLookAndFeel(), "Windows")) {
                LnFName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            }
            if (Objects.equals(((SetLooknFeelEvent) e).getLookAndFeel(), "Metal")) {
                LnFName = "javax.swing.plaf.metal.MetalLookAndFeel";
            }
            if (Objects.equals(((SetLooknFeelEvent) e).getLookAndFeel(), "Motion")) {
                LnFName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            }
            if (LnFName != null) {
                this.dispose();
                try {
                    UIManager.setLookAndFeel(LnFName);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                }
                setUndecorated(false);
            } else {
                this.dispose();
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
        if (e instanceof PlacePanelEvent) {
            PlacePanel(((PlacePanelEvent) e).getSource());
        }
        if (e instanceof GameSessionEvent) {
            if (e instanceof GameSessionStartEvent) {
                gamesession.handle(e);
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                PlacePanel(gamesession);
            } else if (e instanceof StatsMessageEvent) {
                gamesession.handle(e);
            } else if (e instanceof ReleaseStatsEvent) {
                gamesession.handle(e);
            } else {
                gamesession.handle(e);
            }
        }
        this.pack();
    }


    private void PlacePanel(JPanel panel) {
        panel.setVisible(true);
        this.getContentPane().removeAll();
        this.repaint();
        this.getContentPane().add(panel);
    }

    private void PlacePanel(String panel) {
        Field jpanel = null;
        try {
            jpanel = this.getClass().getDeclaredField(panel);
            JPanel toPlace = (JPanel) jpanel.get(this);
            PlacePanel(toPlace);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void PlaceScores() {
        this.remove(mainscreen);
        this.add(scores);
        scores.setVisible(true);
    }

    public ChessViewComponent() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor cursor = toolkit.createCustomCursor(CreateImageIcon("/images/cursor.png", 25, 25).getImage(), new Point(0, 0), "Gaming Cursor");
        this.getContentPane().setCursor(cursor);
        Dimension dimension = toolkit.getScreenSize();
        this.setTitle("ChessRPG");
        this.setUndecorated(!lnf);
        this.setSize(new Dimension(Panels.getX(), Panels.getY()));
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setIconImage(CreateImageIcon("/images/logo.png", 1000, 1000).getImage());
        this.setVisible(true);
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
        mainscreen.register(o);
        gamesession.register(o);
        settings.register(o);
        team.register(o);
        boardcreator.register(o);
        mainscreen.register(this);
        gamesession.register(this);
        profiles.register(this);
        settings.register(this);
        team.register(this);
        boardcreator.register(this);

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
}
