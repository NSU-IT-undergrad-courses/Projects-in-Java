package org.example.observer.event.team;

import java.util.ArrayList;
import java.util.List;

public class SendSelectedTeamStats extends  TeamEvent{
    public SendSelectedTeamStats(List<String[]> stats) {
        this.stats = stats;
    }

    private List<String []> stats = new ArrayList<>();

    public List<String[]> getStats() {
        return stats;
    }
}
