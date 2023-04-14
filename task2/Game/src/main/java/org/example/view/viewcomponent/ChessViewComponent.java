package org.example.view.viewcomponent;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.GameStartEvent;
import org.example.observer.event.session.GameSessionEvent;
import org.example.observer.event.session.GameSessionStartEvent;
import org.example.observer.event.session.ReleaseStatsEvent;
import org.example.observer.event.session.StatsMessageEvent;
import org.example.view.Panels;
import org.example.view.panels.GameSessionPanel;
import org.example.view.panels.MainScreenPanelTEMP;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ChessViewComponent extends JFrame implements Observer, Observable {
    private final List<Observer> observers = new ArrayList<>();
    MainScreenPanelTEMP mainscreen = new MainScreenPanelTEMP();
    GameSessionPanel gamesession = new GameSessionPanel();
    JFrame frame = new JFrame();

    @Override
    public void handle(Event e) {
        if (e instanceof GameStartEvent) {
            PlaceMainScreen();
        }
        if (e instanceof GameSessionEvent) {
            if (e instanceof GameSessionStartEvent) {
                gamesession.handle(e);
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                PlaceGameSession();
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

    public ChessViewComponent() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setTitle("ChessRPG");
        frame.setUndecorated(true);
        frame.setSize(new Dimension(Panels.getX(), Panels.getY()));
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        BufferedImage logo = null;
        try {
            logo = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        assert logo != null;
        Image imagelogo = logo.getScaledInstance(20, 20,
                Image.SCALE_FAST);
        ImageIcon nameimage = new ImageIcon(imagelogo);
        frame.setIconImage(nameimage.getImage());
    }

    public void PlaceMainScreen() {
        frame.add(mainscreen);
        frame.pack();
    }

    public void PlaceGameSession() {
        frame.remove(mainscreen);
        frame.add(gamesession);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
        mainscreen.register(o);
        gamesession.register(o);
        mainscreen.register(this);
        gamesession.register(this);
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
