package org.example.observer.event.session.controller;

import org.example.observer.event.session.GameSessionEvent;

import java.util.List;

public class MovesMessage extends GameSessionEvent {

    public List<Integer> getMoves() {
        return moves;
    }

    public MovesMessage(List<Integer> moves) {
        this.moves = moves;
    }

    private final List<Integer> moves;
}
