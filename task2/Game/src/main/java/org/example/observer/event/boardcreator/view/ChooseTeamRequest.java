package org.example.observer.event.boardcreator.view;

import org.example.observer.event.boardcreator.BoardCreatorEvent;

public class ChooseTeamRequest extends BoardCreatorEvent {
    public String[] getChosen() {
        return teams;
    }

    String [] teams = new String [2];

    public ChooseTeamRequest(String first, String second) {
        this.teams[0] = first;
        this.teams[1] = second;
    }
}
