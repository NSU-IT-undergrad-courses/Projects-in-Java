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
import org.example.view.panels.MainScreenPanel;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChessViewComponent extends JFrame implements Observer, Observable {
    private final List<Observer> observers = new ArrayList<>();
    MainScreenPanel mainscreen = new MainScreenPanel();
    GameSessionPanel gamesession = new GameSessionPanel();
    JFrame frame = new JFrame();

    @Override
    public void handle(Event e) {
        if (e instanceof GameStartEvent) {
            PlaceMainScreen();
        }
        if (e instanceof GameSessionEvent) {
            if (e instanceof  GameSessionStartEvent){
                PlaceGameSession();
            }
            if (e instanceof StatsMessageEvent){
                gamesession.handle(e);
            }
            if (e instanceof ReleaseStatsEvent){
                gamesession.handle(e);
            }
        }
    }

    public ChessViewComponent() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setUndecorated(true);
        frame.setSize(new Dimension(Panels.getX(), Panels.getY()));
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        URL icon_link = null;
        try {
            icon_link = new URL("https://w7.pngwing.com/pngs/629/63/png-transparent-the-red-lion-logo-hotel-roar-lion-fitness-animals-carnivoran.png");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Image icon = new ImageIcon(icon_link).getImage();
        frame.setIconImage(icon);
        frame.setVisible(true);
    }

    public void PlaceMainScreen() {
        frame.add(mainscreen);
        frame.pack();
    }

    public void PlaceGameSession() {
        frame.remove(mainscreen);
        frame.add(gamesession);
        frame.pack();
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
