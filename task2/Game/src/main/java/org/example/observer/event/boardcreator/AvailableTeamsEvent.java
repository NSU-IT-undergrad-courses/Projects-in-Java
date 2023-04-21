package org.example.observer.event.boardcreator;

import org.example.observer.event.Event;

import java.util.List;

public class AvailableTeamsEvent extends BoardCreatorEvent {
    private final List<String> teams;

    public AvailableTeamsEvent(List<String> teams) {
        this.teams = teams;
    }

    public List<String> getTeams() {
        return teams;
    }
}
