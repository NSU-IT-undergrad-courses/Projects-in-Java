package org.example.observer.event.team.view;

import org.example.observer.event.team.TeamEvent;

public class TeamStatsRequest extends TeamEvent {
    public TeamStatsRequest(String teamFile) {
        this.teamFile = teamFile;
    }

    public String getTeamFile() {
        return teamFile;
    }

    private final String teamFile;

}
