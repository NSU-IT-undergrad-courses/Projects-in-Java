package org.example.model.board;

import org.example.model.figure.figure;

import java.util.ArrayList;

public interface board {
    public ArrayList<figure> getBoard();
    public void setBoard(ArrayList<figure> figures);
    public void setPlace(int length, int width, figure fig);
    public figure getPlace(int length, int width);
}
