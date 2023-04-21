package org.example.observer.event.team;

public class DeleteTeam extends TeamEvent{
    public DeleteTeam(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    String team_name;
}
