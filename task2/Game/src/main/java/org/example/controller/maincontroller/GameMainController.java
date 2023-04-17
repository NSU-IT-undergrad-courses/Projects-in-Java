package org.example.controller.maincontroller;

import org.example.controller.boardcreator.BoardCreator;
import org.example.controller.gamesession.GameSessionController;
import org.example.controller.mainscreencontroller.MainScreenController;
import org.example.controller.profile.ProfileController;
import org.example.controller.scorescontroller.ScoresController;
import org.example.controller.teamcontroller.TeamController;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.AvailableTeamsRequest;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.screens.RestartGameEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameMainController implements Observer, Observable {
    private final List<Observer> observers = new ArrayList<>();
    private GameSessionController sessioncontroller;
    private BoardCreator boardcreator = new BoardCreator();
    private MainScreenController mainscreencontroller;
    private ProfileController profilecontroller;
    private TeamController teamcontroller;
    private ScoresController scorescontroller;

    @Override
    public void register(Observer o) {
        observers.add(o);
        boardcreator.register(o);
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
        if (e instanceof AvailableTeamsRequest){
            boardcreator.handle(e);
        }
        if (e instanceof RestartGameEvent) {
            Start();
        }
        if (e instanceof PlacePanelEvent) {
            if (Objects.equals(((PlacePanelEvent) e).getSource(), "profiles")) {
                profilecontroller = new ProfileController();
            }
        }
    }

    public void Start() {
        mainscreencontroller = new MainScreenController();
        notify(new PlacePanelEvent("mainscreen"));
    }
}
