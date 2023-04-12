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

public class ChessGameController extends ObservableImpl implements Observer {
    private final FigureFabric fabric = new FigureFabric("/fabric/types.properties");
    private board Board = new ChessBoard();

    public ChessGameController() {

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
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(inputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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
        scanner = new Scanner(saved);
        scanner.useDelimiter("\n\r");
        ArrayList<Figure> figures = new ArrayList<>(64);
        for (int i = 0; i < 64; i++) {
            figures.add(new CellFigure());
        }
        for (int i = 0; i < 64; i++) {
            String figure_info = scanner.nextLine();
            figures.set(i, fabric.create(figure_info));
        }
        newBoard.setBoard(figures);
        return newBoard;
    }


    @Override
    public void handle(Event e) {
        if (e instanceof GameSessionEvent) {
            if (e instanceof RequestStatsEvent) {
                Integer width = ((RequestStatsEvent) e).getFigureNumber();
                Integer height = width / 8;
                width %= 8;
                if (Board.getPlace(width, height) instanceof CellFigure) {

                } else {
                    String name = Board.getPlace(width, height).getName();
                    Integer attack = Board.getPlace(width, height).getAttack();
                    Integer defense = Board.getPlace(width, height).getDefense();
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

    private List<Integer> CalculateAvailableMoves(Figure fig, int length, int width) {
        List<Integer> moves = new ArrayList<Integer>();
        for (int i = 0; i < fig.getTrace().size(); i += 2) {
            Integer a = length + fig.getTrace().get(i);
            Integer b = width + fig.getTrace().get(i + 1);
            if (a >= 0 && a < 8 && b > -1 && b < 8) {
                moves.add(a);
                moves.add(b);
            }
        }
        return moves;
    }


}
