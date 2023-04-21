package org.example.controllers;

import org.example.GameConstants;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.team.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.example.GameConstants.*;

public class TeamController implements Observable, Observer{
    private final String path = TEAM.getDEFAULT_PATH_FILE();
    private List<String> teams = new ArrayList<String>();
    private List<String []>stats = new ArrayList<>();
    private String current_team;
    private void getTeams() {
        teams =  new ArrayList<String>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile()) {
                teams.add(listOfFiles[i].getName());
            }
        }
        notify(new CreatedTeamMessage(teams));
    }

    private final List<Observer> observers = new ArrayList<>();

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
        if (e instanceof  ReplaceName){
            ReplaceTeamName(((ReplaceName) e).getPrevious(),((ReplaceName) e).getEdited());
        }
        if (e instanceof DeleteTeam){
            DeleteSelectedTeam(((DeleteTeam) e).getTeam_name());
        }
        if (e instanceof CreateNewTeam){
            CreateDeafultTeam();
        }
        if (e instanceof NewStatsMessage){
            System.out.println(CheckStats(((NewStatsMessage) e).getChangedstats()));
            if (CheckStats(((NewStatsMessage) e).getChangedstats())){
                stats = ((NewStatsMessage) e).getChangedstats();
                WriteTeamChanges(stats, current_team);
            }
        }
        if (e instanceof CreatedTeamsRequest){
            getTeams();
        }
        if (e instanceof RequsetSelectedTeamFigures){
            current_team = ((RequsetSelectedTeamFigures) e).getTeamFile();
            GetSelectedStats();
        }
    }

    private void ReplaceTeamName(String previous, String edited) {
        File previousTeam = new File(TEAM.getDEFAULT_PATH_FILE()+previous);
        File editedTeam = new File(TEAM.getDEFAULT_PATH_FILE()+edited);
        previousTeam.renameTo(editedTeam);
        getTeams();
    }

    private void DeleteSelectedTeam(String teamName) {
        System.out.println(TEAM.getDEFAULT_PATH_FILE()+teamName);
        File team = new File(TEAM.getDEFAULT_PATH_FILE()+teamName);
        if (team.delete()){
            if (teams.size() == 0){
                CreateDeafultTeam();
            }
            getTeams();
        }
    }

    private void CreateDeafultTeam() {
        try {
            Files.copy(Paths.get(TEAM_DEFAULT.getDEFAULT_PATH_RESOURCE()), Paths.get(TEAM.getDEFAULT_PATH_FILE()+"BLANK TEAM.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getTeams();
    }

    private void WriteTeamChanges(List<String[]> stats, String currentTeam) {
        InputStream previous = getClass().getResourceAsStream(TEAM.getDEFAULT_PATH_RESOURCE() +currentTeam);
        Scanner scanner = new Scanner(previous);
        String [] previous_figures = new String[24];
        for (int i = 0; i < 8; i++){
            scanner.nextLine();
        }
        for (int i = 0; i < 24; i++){
            previous_figures[i] = scanner.nextLine();
        }
        try {
            scanner.close();
            previous.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            try (FileOutputStream file = new FileOutputStream(new File(path+currentTeam))) {
                for (String[] stat : stats) {
                    StringBuilder builder = new StringBuilder();
                    for (String s : stat) {
                        builder.append(s);
                        builder.append("#");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    try {
                        file.write(String.valueOf(builder).getBytes());
                        file.write('\n');
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (int i = 2; i > -1; i--){
                    String [] stat = stats.get(i);
                    StringBuilder builder = new StringBuilder();
                    for (String s : stat) {
                        builder.append(s);
                        builder.append("#");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    try {
                        file.write(String.valueOf(builder).getBytes());
                        file.write('\n');
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (String previousFigure : previous_figures) {
                    file.write(previousFigure.getBytes());
                    file.write('\n');
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean CheckStats(List<String[]> changedstats) {
        return true;
    }

    private void GetSelectedStats() {
        InputStream selectedteam = getClass().getResourceAsStream(TEAM.getDEFAULT_PATH_RESOURCE() +current_team);
        assert selectedteam != null;
        Scanner scanner = new Scanner(selectedteam);
        stats = new ArrayList<String []>();
        for (int i = 0; i < 5;i++){
            String [] stats_line = scanner.nextLine().split("#");
            stats.add(stats_line);
        }
        try {
            selectedteam.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            selectedteam.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        notify(new SendSelectedTeamStats(stats));
    }
}
