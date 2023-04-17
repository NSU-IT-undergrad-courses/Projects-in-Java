package org.example.controller.boardcreator;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.AvailableTeamsEvent;
import org.example.observer.event.boardcreator.AvailableTeamsRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BoardCreator implements Observable, Observer {
    private final List<Observer> observers = new ArrayList<>();
    private String path = "src/main/resources/teams/";
    private List<String> teams = new ArrayList<String>();
    private void getTeams() {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                teams.add(listOfFiles[i].getName());
            }
        }
        notify(new AvailableTeamsEvent(teams));
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
        for (Observer o : observers) {
            o.handle(e);
        }
    }

    @Override
    public void handle(Event e) {
        if (e instanceof AvailableTeamsRequest){
            getTeams();
        }
    }
}
