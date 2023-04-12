package org.example.observer.event.session;

import java.util.List;

public class MovesMessageEvent extends GameSessionEvent {
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int index;

    public List<Integer> getMoves() {
        return moves;
    }

    public void setMoves(List<Integer> moves) {
        this.moves = moves;
    }

    public MovesMessageEvent(int index, List<Integer> moves) {
        this.index = index;
        this.moves = moves;
    }

    private List<Integer> moves;
}
