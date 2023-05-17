package org.example.observer.event.team.view;

import org.example.observer.event.team.TeamEvent;

public class DeleteTeamRequest extends TeamEvent {
    public DeleteTeamRequest(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    String team_name;
}
