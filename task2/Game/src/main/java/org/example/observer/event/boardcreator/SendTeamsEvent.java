package org.example.observer.event.boardcreator;

import org.example.observer.event.Event;

public class SendTeamsEvent extends BoardCreatorEvent {
    public String[] getChosen() {
        return teams;
    }

    String [] teams = new String [2];

    public SendTeamsEvent(String first, String second) {
        this.teams[0] = first;
        this.teams[1] = second;
    }
}
