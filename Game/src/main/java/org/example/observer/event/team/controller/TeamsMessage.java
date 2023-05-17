package org.example.observer.event.team.controller;

import org.example.observer.event.team.TeamEvent;

import java.util.List;

public class TeamsMessage extends TeamEvent {
    private final List<String> teams;

    public TeamsMessage(List<String> teams) {
        this.teams = teams;
    }

    public List<String> getTeams() {
        return teams;
    }
}
