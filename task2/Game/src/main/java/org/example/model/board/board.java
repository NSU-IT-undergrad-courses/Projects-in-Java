package org.example.model.board;

import org.example.model.figure.Figure;

import java.util.ArrayList;

public interface board {
    public ArrayList<Figure> getBoard();
    public void setBoard(ArrayList<Figure> Figures);
    public void setPlace(int length, int width, Figure fig);
    public Figure getPlace(int length, int width);
}
