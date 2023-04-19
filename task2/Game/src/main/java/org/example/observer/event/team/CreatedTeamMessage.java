package org.example.observer.event.team;

import java.util.List;

public class CreatedTeamMessage extends TeamEvent{
    private List<String> teams;

    public CreatedTeamMessage(List<String> teams) {
        this.teams = teams;
    }

    public List<String> getTeams() {
        return teams;
    }
}
