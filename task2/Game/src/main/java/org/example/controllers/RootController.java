package org.example.controllers;

import org.example.GameConstants;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.AvailableTeamsRequest;
import org.example.observer.event.boardcreator.BoardCreatorEvent;
import org.example.observer.event.screens.GameStopEvent;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.screens.RestartGameEvent;
import org.example.observer.event.session.GameSessionEvent;
import org.example.observer.event.session.RequestStatsEvent;
import org.example.observer.event.team.TeamEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RootController implements Observer, Observable {
    private final List<Observer> observers = new ArrayList<>();
    private final SessionController sessioncontroller = new SessionController();
    private final BoardController boardcreator = new BoardController();
    private final TeamController teamcontroller = new TeamController();

    @Override
    public void register(Observer o) {
        observers.add(o);
        boardcreator.register(o);
        sessioncontroller.register(o);
        teamcontroller.register(o);
        boardcreator.register(this);
        sessioncontroller.register(this);
        teamcontroller.register(this);

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

    @Override
    public void handle(Event e) {
        if (e instanceof GameStopEvent) {
            System.exit(0);
        }
        if (e instanceof BoardCreatorEvent){
            boardcreator.handle(e);
        }
        if (e instanceof GameSessionEvent){

            sessioncontroller.handle(e);
        }
        if (e instanceof TeamEvent){
            teamcontroller.handle(e);
        }
        if (e instanceof RestartGameEvent) {
            Start();
        }
        if (e instanceof PlacePanelEvent) {
            if (Objects.equals(((PlacePanelEvent) e).getSource(), GameConstants.OFFLINE.getPANEL_INDEX())) {
                sessioncontroller.StartGame();
            }
        }
    }

    public void Start() {
        notify(new PlacePanelEvent(GameConstants.START.getPANEL_INDEX()));
    }
}
