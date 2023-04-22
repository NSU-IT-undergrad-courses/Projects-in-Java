package org.example.observer.event.team.view;

import org.example.observer.event.team.TeamEvent;

import java.util.List;

public class ChangeStatsRequest extends TeamEvent {
    public List<String[]> getChangedstats() {
        return changedstats;
    }

    public ChangeStatsRequest(List<String[]> changedstats) {
        this.changedstats = changedstats;
    }

    private final List<String[]> changedstats;

}
