package org.example.controllers;

import org.example.GameConfiguration;
import org.example.model.board.ChessBoard;
import org.example.model.board.board;
import org.example.model.fabric.FigureFabric;
import org.example.model.figure.CellFigure;
import org.example.model.figure.Figure;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.GameSessionEndEvent;
import org.example.observer.event.screens.GameSessionStartEvent;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.session.GameSessionEvent;
import org.example.observer.event.session.controller.*;
import org.example.observer.event.session.view.FigureChosenListenerEvent;
import org.example.observer.event.session.view.MovesRequest;
import org.example.observer.event.session.view.StatsRequest;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;

public class SessionController extends SubController implements Observer {
    private final List<Observer> observers = new ArrayList<>();
    private final FigureFabric fabric = new FigureFabric("/fabric/types.properties");
    private board Board = new ChessBoard();
    private Integer turn = 1;
    private List<Integer> FiguresToMove = new ArrayList<>();

    public SessionController(RootController parent) {
        super(parent);
    }

    public Integer getTurn() {
        return turn;
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

    public void StartGame() {
        Clip clip;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        AudioInputStream inputStream;
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
        String[] names = new String[64];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                names[i * 8 + j] = Board.getPlace(j, i).getName();
            }
        }
        notify(new NamesMessage(names));
        notify(new GameSessionStartEvent(names));
    }

    private board loadBoard(String [] changes) {
        board newBoard = new ChessBoard();
        ArrayList<Figure> figures = new ArrayList<>(64);
        for (int i = 0; i < 64; i++) {
            figures.add(new CellFigure());
        }
        for (int i = 0; i < 64; i++) {
            String figure_info = changes[i];
            figures.set(i, fabric.create(figure_info));
            if (i < 16) {
                figures.get(i).setWhite(1);
            }
            if (i > 47) {
                figures.get(i).setWhite(0);
            }
        }
        newBoard.setBoard(figures);

        return newBoard;
    }


    @Override
    public void handle(Event e) {
        if (e instanceof GameSessionEvent) {
            if (e instanceof BoardSentMessage){
                Board = loadBoard(((BoardSentMessage) e).getChanges());
                notify(new PlacePanelEvent(GameConfiguration.OFFLINE.getPANEL_INDEX()));
            }
            if (e instanceof FigureChosenListenerEvent) {
                addFiguresToMove(((FigureChosenListenerEvent) e).getIndex());
                if (getFiguresToMove().size() == 2) {
                    Integer source = getFiguresToMove().get(0);
                    Integer destination = getFiguresToMove().get(1);
                    if (!Objects.equals(Board.getPlace(source).getName(), "cell") && Board.getPlace(source).isWhite() == getTurn() % 2) {
                        int source_length = source % 8;
                        int source_width = source / 8;
                        int destination_length = destination % 8;
                        int destination_width = destination / 8;
                        List<Integer> CurrentMoves = CalculateAvailableMoves(Board.getPlace(source_length, source_width), source_length, source_width);
                        for (int i = 0; i < CurrentMoves.size(); i += 2) {
                            boolean found = Objects.equals(CurrentMoves.get(i), destination_length) && Objects.equals(CurrentMoves.get(i + 1), destination_width);
                            if (found)
                                if (Board.getPlace(destination).isWhite() == null || (!Objects.equals(Board.getPlace(destination).isWhite(), Board.getPlace(source).isWhite()))) {
                                    Integer attack = Board.getPlace(source_length, source_width).getAttack();
                                    Integer defense = Board.getPlace(destination_length, destination_width).getDefense();
                                    if (Board.getPlace(source_length, source_width).getClass().getSimpleName().contains("Horse")) {
                                        turn++;
                                        MakeMove(source, source_length, source_width, destination, destination_length, destination_width, attack, defense);
                                    } else {
                                        boolean clear = CheckWay(source_length, source_width, destination_length, destination_width);
                                        if (clear) {
                                            turn++;
                                            MakeMove(source, source_length, source_width, destination, destination_length, destination_width, attack, defense);
                                        } else {
                                            notify(new InvalidMoveMessage());
                                        }
                                    }
                                }
                        }

                    }
                    setFiguresToMove(new ArrayList<>());
                    notify(new ClearMovesMessage());
                }
            }
            if (e instanceof StatsRequest) {
                int length = ((StatsRequest) e).getFigureNumber();
                int width = length / 8;
                length %= 8;
                if (!(Board.getPlace(length, width) instanceof CellFigure)) {
                    String name = Board.getPlace(length, width).getName();
                    Integer attack = Board.getPlace(length, width).getAttack();
                    Integer defense = Board.getPlace(length, width).getDefense();
                    notify(new StatsMessage(name, attack, defense));
                }
            }
            if (e instanceof MovesRequest) {
                int value = ((MovesRequest) e).getFigure_number();
                int length = value % 8;
                int width = value / 8;
                Figure fig = Board.getPlace(length, width);
                notify(new MovesMessage(CalculateAvailableMoves(fig, length, width)));
            }

        }

    }

    private void MakeMove(Integer source, int source_length, int source_width, Integer destination, int destination_length, int destination_width, Integer attack, Integer defense) {
        if (attack >= defense) {
            if (Board.getPlace(destination_length, destination_width).getName().contains("king")) {
                notify(new GameSessionEndEvent(Board.getPlace(destination_length, destination_width).isWhite(), getTurn()));
            }
            Board.setPlace(destination_length, destination_width, Board.getPlace(source_length, source_width));
            Board.setPlace(source_length, source_width, new CellFigure());
            notify(new FigureKilledMessage(source, Board.getPlace(destination_length, destination_width).getName(), destination));

        } else {
            Board.getPlace(destination_length, destination_width).setDefense(defense - attack);
            notify(new FailedAttackMessage(Board.getPlace(destination_length, destination_width).getName(), -attack));
        }
    }

    private boolean CheckWay(int source_length, int source_width, int destination_length, int destination_width) {
        boolean clear = true;
        int deltax = destination_length - source_length;
        int deltay = destination_width - source_width;
        int di = signum(deltax);
        int dj = signum(deltay);
        int startx = source_length;
        int starty = source_width;
        for (; abs(startx - source_length) <= abs(deltax) && abs(starty - source_width) <= abs(deltay); startx += di, starty += dj) {
            if (!(startx == source_length && starty == source_width) && !(startx == destination_length && starty == destination_width)) {
                if (!Objects.equals(Board.getPlace(startx, starty).getName(), "cell")) {
                    clear = false;
                }
            }
        }
        return clear;
    }

    private List<Integer> CalculateAvailableMoves(Figure fig, int length, int width) {
        List<Integer> moves = new ArrayList<>();
        if (Objects.equals(fig.getName(), "cell")) {
            return moves;
        }
        for (int i = 0; i < fig.getTrace().size(); i += 2) {
            int a = length + fig.getTrace().get(i);
            int b = width + fig.getTrace().get(i + 1);
            if (a >= 0 && a < 8 && b > -1 && b < 8) {
                if (!Objects.equals(fig.isWhite(), Board.getPlace(a, b).isWhite())) {
                    if ((CheckWay(length, width, a, b)) || fig.getClass().getSimpleName().contains("Horse")) {
                        moves.add(a);
                        moves.add(b);
                    }
                }
            }
        }
        return moves;
    }


}
