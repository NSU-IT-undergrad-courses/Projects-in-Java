package org.example.model.board;

import org.example.model.figure.boardFigure;
import org.example.model.figure.figure;

import java.util.ArrayList;

public class chessboard implements board{
    public chessboard() {
        figure fig = new boardFigure("*#0#0#forward 1");
        this.board = new ArrayList<>(64);
        for (int i = 0; i<64;i++){
            board.add(fig);
        }
    }

    private ArrayList<figure> board;
    private final Integer length = 8;
    private final Integer width = 8;

    @Override
    public ArrayList<figure> getBoard() {
        return board;
    }

    @Override
    public void setBoard(ArrayList<figure> figures) {
        if (figures.size() == 64) {
            board = figures;
        }
        //else Exception
    }
    @Override
    public void setPlace(int length_value, int width_value, figure fig) {
            board.set(length_value+width_value*width, fig);
    }

    @Override
    public figure getPlace(int length_value, int width_value) {
        return board.get(length_value+width_value*width);
    }
}
