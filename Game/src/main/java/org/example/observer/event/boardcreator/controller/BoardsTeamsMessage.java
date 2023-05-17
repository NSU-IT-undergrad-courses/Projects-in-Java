package org.example.observer.event.boardcreator.controller;

import org.example.observer.event.boardcreator.BoardCreatorEvent;

import java.util.List;

public class BoardsTeamsMessage extends BoardCreatorEvent {
    private final List<String> teams;

    public BoardsTeamsMessage(List<String> teams) {
        this.teams = teams;
    }

    public List<String> getTeams() {
        return teams;
    }
}
