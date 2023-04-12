package org.example.model.board;

import org.example.model.figure.Figure;

import java.util.ArrayList;

public interface board {
    ArrayList<Figure> getBoard();

    void setBoard(ArrayList<Figure> Figures);

    void setPlace(int length, int width, Figure fig);

    Figure getPlace(int length, int width);
}
