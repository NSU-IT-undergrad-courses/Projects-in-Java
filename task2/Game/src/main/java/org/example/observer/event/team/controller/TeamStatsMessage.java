package org.example.observer.event.team.controller;

import org.example.observer.event.team.TeamEvent;

import java.util.List;

public class TeamStatsMessage extends TeamEvent {
    public TeamStatsMessage(List<String[]> stats) {
        this.stats = stats;
    }

    private final List<String []> stats;

    public List<String[]> getStats() {
        return stats;
    }
}
