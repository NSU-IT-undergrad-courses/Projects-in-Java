package org.example.observer.event.team;

import java.util.ArrayList;
import java.util.List;

public class NewStatsMessage extends TeamEvent{
    public List<String[]> getChangedstats() {
        return changedstats;
    }

    public NewStatsMessage(List<String[]> changedstats) {
        this.changedstats = changedstats;
    }

    private final List<String[]> changedstats;

}
