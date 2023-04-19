package org.example.controller.boardcreator;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.AvailableTeamsEvent;
import org.example.observer.event.boardcreator.AvailableTeamsRequest;
import org.example.observer.event.boardcreator.BoardCreatorEvent;
import org.example.observer.event.boardcreator.SendTeamsEvent;
import org.example.observer.event.screens.PlacePanelEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BoardCreator implements Observable, Observer {
    private final List<Observer> observers = new ArrayList<>();
    private String path = "src/main/resources/teams/";
    private List<String> teams = new ArrayList<String>();
    private void getTeams() {
        teams =  new ArrayList<String>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                teams.add(listOfFiles[i].getName());
            }
        }
        notify(new AvailableTeamsEvent(teams));
    }
    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void remove(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notify(Event e) {
        for (Observer o : observers) {
            o.handle(e);
        }
    }

    @Override
    public void handle(Event e) {
            if (e instanceof SendTeamsEvent){
                CreateBoard(((SendTeamsEvent) e).getChosen());
            }
            if (e instanceof AvailableTeamsRequest){
                getTeams();
            }
        }

    private void CreateBoard(String[] chosen) {
        InputStream team1 = getClass().getResourceAsStream("/teams/" + chosen[0] + ".txt");
        InputStream team2 = getClass().getResourceAsStream("/teams/" + chosen[1] + ".txt");
        assert team1 != null;
        Scanner scanner1 = new Scanner(team1);
        assert team2 != null;
        Scanner scanner2 = new Scanner(team2);
        try {
            try (FileOutputStream board = new FileOutputStream(new File("src/main/resources/session/" + "createdboard" + ".txt"))) {
                for (int i = 0; i < 16; i++){
                    String figure = scanner1.nextLine();
                    figure = "white "+figure;
                    figure+='\n';
                    board.write(figure.getBytes());
                }
                for (int i = 0; i < 16; i++){
                    String cell = scanner1.nextLine();
                    cell+='\n';
                    board.write(cell.getBytes());
                }


                String [] team2figures = new String[32];
                for (int i = 0; i < 16; i++){
                    team2figures[i] = scanner2.nextLine();
                    if (team2figures[i].contains("forward")){
                        String [] figure = team2figures[i].split("#");
                        figure[figure.length-1] = "-1";
                        StringBuilder builder = new StringBuilder();
                        for(String s : figure) {
                            builder.append(s);
                            builder.append("#");
                        }
                        builder.deleteCharAt(builder.length()-1);
                        String str = builder.toString();
                        team2figures[i] = str;
                    }
                    team2figures[i] = "black " + team2figures[i];
                }

                for (int i = 16; i < 32; i++){
                    team2figures[i] = scanner2.nextLine();
                }
                for (int i = 31; i > -1; i--){
                    team2figures[i] +='\n';
                    board.write(team2figures[i].getBytes());
                }
                notify(new PlacePanelEvent("gamesession"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
