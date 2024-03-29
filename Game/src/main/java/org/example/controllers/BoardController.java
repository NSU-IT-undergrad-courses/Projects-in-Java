package org.example.controllers;

import org.example.GameConfiguration;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.controller.BoardsTeamsMessage;
import org.example.observer.event.boardcreator.view.BoardTeamsRequest;
import org.example.observer.event.boardcreator.view.ChooseTeamRequest;
import org.example.observer.event.session.view.LoadBoardRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.GameConfiguration.CheckTeamDirectory;

public class BoardController extends SubController implements Observer {
    private final String path = GameConfiguration.TEAM.getDEFAULT_PATH_FILE();

    public BoardController(RootController parent) {
        super(parent);
    }

    private void getTeams() {
        List<String> teams = new ArrayList<>();
        CheckTeamDirectory(path, teams);
        notify(new BoardsTeamsMessage(teams));
    }

    @Override
    public void handle(Event e) {
        if (e instanceof ChooseTeamRequest){
            CreateBoard(((ChooseTeamRequest) e).getChosen());
        }
        if (e instanceof BoardTeamsRequest){
            getTeams();
        }
    }

    private void CreateBoard(String[] chosen) {
        Scanner scanner1 = null;
        try {
            scanner1 = new Scanner(new File(GameConfiguration.TEAM.getDEFAULT_PATH_FILE()+ chosen[0] + ".txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner2 = null;
        try {
            scanner2 = new Scanner(new File(GameConfiguration.TEAM.getDEFAULT_PATH_FILE()+ chosen[1] + ".txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String [] writtenchanges = new String [64];
        for (int i = 0; i < 16; i++){
            String figure = scanner1.nextLine();
            figure = "white "+figure;
            writtenchanges[i] = figure;
        }
        for (int i = 0; i < 16; i++){
            String cell = scanner1.nextLine();
            cell+='\n';
            writtenchanges[i+16] = cell;
        }

        String [] team2figures = new String[32];
        for (int i = 16; i < 32; i++){
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

        for (int i = 0; i < 16; i++){
            team2figures[i] = scanner2.nextLine();
        }
        System.arraycopy(team2figures, 0, writtenchanges, 32, 16);
        for (int i = 0; i < 16; i++){
            writtenchanges[63-i] = team2figures[16+i];
        }

        notify(new LoadBoardRequest(writtenchanges));



    }
}
