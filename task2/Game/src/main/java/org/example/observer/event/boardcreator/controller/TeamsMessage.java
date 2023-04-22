package org.example.observer.event.boardcreator.controller;

import org.example.observer.event.boardcreator.BoardCreatorEvent;

import java.util.List;

public class TeamsMessage extends BoardCreatorEvent {
    private final List<String> teams;

    public TeamsMessage(List<String> teams) {
        this.teams = teams;
    }

    public List<String> getTeams() {
        return teams;
    }
}
