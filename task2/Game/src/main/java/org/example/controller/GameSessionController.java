package org.example.controller;

import org.example.controller.fabric.FigureFabric;
import org.example.model.board.ChessBoard;
import org.example.model.board.board;
import org.example.model.figure.Figure;
import org.example.model.figure.types.CellFigure;
import org.example.observer.ObservableImpl;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.GameStartEvent;
import org.example.observer.event.session.*;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;
import static java.lang.Math.min;

public class GameSessionController extends ObservableImpl implements Observer {
    private final FigureFabric fabric = new FigureFabric("/fabric/types.properties");
    private board Board = new ChessBoard();

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    private Integer turn = 1;
    private List<Integer> FiguresToMove = new ArrayList<Integer>();

    public GameSessionController() {

    }

    public List<Integer> getFiguresToMove() {
        return FiguresToMove;
    }

    public void setFiguresToMove(List<Integer> figuresToMove) {
        FiguresToMove = figuresToMove;
    }

    public void addFiguresToMove(Integer figure) {
        FiguresToMove.add(figure);
    }

    //
    //Основные функции, типо начало, выход, сменить окно
    //
    public void Start() {
        notify(new GameStartEvent());
    }

    public void Exit() {

    }

    public void StartGame() {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        AudioInputStream inputStream = null;
        try {
            inputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/game.wav")));
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(inputStream);
        } catch (LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
        clip.start();
        Board = loadBoard("/saved/default.txt");
        String[] names = new String[64];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                names[i * 8 + j] = Board.getPlace(j, i).getName();
            }
        }
        notify(new NamesMessageEvent(names));
        notify(new GameSessionStartEvent(names));
    }

    private board loadBoard(String file) {
        board newBoard = new ChessBoard();
        InputStream saved = this.getClass().getResourceAsStream(file);
        Scanner scanner;
        scanner = new Scanner(Objects.requireNonNull(saved));
        scanner.useDelimiter("\n\r");
        ArrayList<Figure> figures = new ArrayList<>(64);
        for (int i = 0; i < 64; i++) {
            figures.add(new CellFigure());
        }
        for (int i = 0; i < 64; i++) {
            String figure_info = scanner.nextLine();
            figures.set(i, fabric.create(figure_info));
            if (i < 16){
                figures.get(i).setWhite(1);
            }
            if (i > 47){
                figures.get(i).setWhite(0);
            }
        }
        newBoard.setBoard(figures);
        return newBoard;
    }


    @Override
    public void handle(Event e) {
        if (e instanceof GameSessionEvent) {
            if (e instanceof FigureChosen) {
                addFiguresToMove(((FigureChosen) e).getIndex());
                if (getFiguresToMove().size() == 2){
                    Integer source = getFiguresToMove().get(0);
                    Integer destination = getFiguresToMove().get(1);
                    if (!Objects.equals(Board.getPlace(source).getName(), "cell") && Board.getPlace(source).isWhite() == getTurn()%2) {
                        int source_length = source % 8;
                        int source_width = source / 8;
                        int destination_length = destination % 8;
                        int destination_width = destination / 8;
                        List<Integer> CurrentMoves = CalculateAvailableMoves(Board.getPlace(source_length, source_width), source_length, source_width);
                        for (int i = 0; i < CurrentMoves.size(); i += 2) {
                            boolean found = Objects.equals(CurrentMoves.get(i), destination_length) && Objects.equals(CurrentMoves.get(i + 1), destination_width);
                            if (found)
                                if (Board.getPlace(destination).isWhite() == null || (Board.getPlace(destination).isWhite() != Board.getPlace(source).isWhite())) {
                                    Integer attack = Board.getPlace(source_length, source_width).getAttack();
                                    Integer defense = Board.getPlace(destination_length, destination_width).getDefense();
                                    if (Board.getPlace(source_length, source_width).getName().contains("horse")) {
                                        turn++;
                                        MakeMove(source, source_length, source_width, destination, destination_length, destination_width, attack, defense);
                                    } else {
                                        boolean clear = CheckWay(source_length, source_width, destination_length, destination_width);
                                        if (clear) {
                                            turn++;
                                            MakeMove(source, source_length, source_width, destination, destination_length, destination_width, attack, defense);
                                        } else {
                                            notify(new CantPerformMoveEvent());
                                        }
                                    }
                                }
                        }

                    }
                    setFiguresToMove(new ArrayList<Integer>());
                    notify(new ClearMovesEvent());
                }
            }
            if (e instanceof RequestStatsEvent) {
                int length = ((RequestStatsEvent) e).getFigureNumber();
                int width = length / 8;
                length %= 8;
                if (Board.getPlace(length, width) instanceof CellFigure) {

                } else {
                    String name = Board.getPlace(length, width).getName();
                    Integer attack = Board.getPlace(length, width).getAttack();
                    Integer defense = Board.getPlace(length, width).getDefense();
                    notify(new StatsMessageEvent(name, attack, defense));
                }
            }
            if (e instanceof RequestMovesEvent) {
                int value = ((RequestMovesEvent) e).getFigure_number();
                int length = value % 8;
                int width = value / 8;
                Figure fig = Board.getPlace(length, width);
                notify(new MovesMessageEvent(value, CalculateAvailableMoves(fig, length, width)));
            }

        }

    }

    private void MakeMove(Integer source, int source_length, int source_width, Integer destination, int destination_length, int destination_width, Integer attack, Integer defense) {
        if (attack >= defense) {
            if (Board.getPlace(destination_length,destination_width).getName().contains("king")){
                notify(new GameEndEvent(Board.getPlace(destination_length,destination_width).isWhite(),getTurn()));
            }
            Board.setPlace(destination_length, destination_width, Board.getPlace(source_length, source_width));
            Board.setPlace(source_length, source_width, new CellFigure());
            notify(new FigureKilledEvent(source, Board.getPlace(source_length, source_width).getName(), Board.getPlace(destination_length, destination_width).getName(), destination));

        } else {
            Board.getPlace(destination_length, destination_width).setDefense(defense - attack);
            notify(new FailedAttackEvent(Board.getPlace(destination_length, destination_width).getName(), -attack));
        }
    }

    private boolean CheckWay(int source_length, int source_width, int destination_length, int destination_width) {boolean clear = true;
       int deltax =  destination_length - source_length;
       int deltay = destination_width - source_width;
       int di = signum(deltax);
       int dj = signum(deltay);
       int startx = source_length;
       int starty = source_width;
       for (;abs(startx-source_length)<abs(deltax) && abs(starty-source_width)<abs(deltay); startx+=di, starty+=dj){
           if (!(startx == source_length && starty == source_width)&& !(startx == destination_length && starty == destination_width)) {
                if (!Objects.equals(Board.getPlace(startx, starty).getName(), "cell")) {
                    clear = false;
                }
            }
       }
        return clear;
    }

    private List<Integer> CalculateAvailableMoves(Figure fig, int length, int width) {
        List<Integer> moves = new ArrayList<Integer>();
        if (Objects.equals(fig.getName(), "cell")) {
            return moves;
        }
        for (int i = 0; i < fig.getTrace().size(); i += 2) {
            int a = length + fig.getTrace().get(i);
            int b = width + fig.getTrace().get(i + 1);
            if (a >= 0 && a < 8 && b > -1 && b < 8){
                if (!Objects.equals(fig.isWhite(), Board.getPlace(a, b).isWhite())){
                    if ((CheckWay(length, width, a, b)) || fig.getName().contains("horse")) {
                        moves.add(a);
                        moves.add(b);
                    }
                }
            }
        }
        return moves;
    }



}
