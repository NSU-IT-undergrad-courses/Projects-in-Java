package org.example.controllers;

import org.example.GameConfiguration;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.view.BoardTeamsRequest;
import org.example.observer.event.screens.GameStopRequest;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.screens.RestartGameEvent;
import org.example.observer.event.team.view.TeamsRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RootController implements Observer, Observable {
    private final List<Observer> observers = new ArrayList<>();
    private final SessionController sessioncontroller = new SessionController(this);
    private final BoardController boardcreator = new BoardController(this);
    private final TeamController teamcontroller = new TeamController(this);

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
        if (Request(e)){
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

    private boolean Request(Event e) {
        return e.getClass().getSimpleName().contains("Request");
    }

    @Override
    public void handle(Event e) {
        if (e instanceof GameStopRequest) {
            System.exit(0);
        }
        else if (e instanceof RestartGameEvent) {
            Start();
        }
        else if (e instanceof PlacePanelEvent) {
            if (Objects.equals(((PlacePanelEvent) e).getSource(), GameConfiguration.OFFLINE.getPANEL_INDEX())) {
                sessioncontroller.StartGame();
            }
        }
        else{
            notify(e);
        }
    }

    public void Start() {
        notify(new TeamsRequest());
        notify(new BoardTeamsRequest());
        notify(new PlacePanelEvent(GameConfiguration.START.getPANEL_INDEX()));
    }
}
