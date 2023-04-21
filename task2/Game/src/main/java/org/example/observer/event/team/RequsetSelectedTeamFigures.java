package org.example.observer.event.team;

public class RequsetSelectedTeamFigures extends TeamEvent{
    public RequsetSelectedTeamFigures(String teamFile) {
        this.teamFile = teamFile;
    }

    public String getTeamFile() {
        return teamFile;
    }

    private final String teamFile;

}
