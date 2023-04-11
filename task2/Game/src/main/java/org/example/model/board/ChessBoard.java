package org.example.model.board;

import org.example.model.figure.Figure;
import org.example.model.figure.types.CellFigure;

import java.util.ArrayList;

public class ChessBoard implements board{
    public ChessBoard() {
        Figure fig = new CellFigure();
        this.board = new ArrayList<>(64);
        for (int i = 0; i<64;i++){
            board.add(fig);
        }
    }

    private ArrayList<Figure> board;
    private final Integer length = 8;
    private final Integer width = 8;

    @Override
    public ArrayList<Figure> getBoard() {
        return board;
    }

    @Override
    public void setBoard(ArrayList<Figure> Figures) {
        if (Figures.size() == 64) {
            board = Figures;
        }
        //else Exception
    }
    @Override
    public void setPlace(int length_value, int width_value, Figure fig) {
            board.set(length_value+width_value*width, fig);
    }

    @Override
    public Figure getPlace(int length_value, int width_value) {
        return board.get(length_value+width_value*width);
    }
}
