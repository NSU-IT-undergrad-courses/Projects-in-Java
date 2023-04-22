package org.example.controllers;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.team.controller.TeamStatsMessage;
import org.example.observer.event.team.controller.TeamsMessage;
import org.example.observer.event.team.view.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.GameConfiguration.*;

public class TeamController implements Observable, Observer{
    private final String path = TEAM.getDEFAULT_PATH_FILE();
    private List<String> teams = new ArrayList<>();
    private List<String []>stats = new ArrayList<>();
    private String current_team;
    private void getTeams() {
        teams = new ArrayList<>();
        CheckTeamDirectory(path, teams);
        notify(new TeamsMessage(teams));
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
        if (e instanceof ReplaceNameRequest){
            ReplaceTeamName(((ReplaceNameRequest) e).getPrevious(),((ReplaceNameRequest) e).getEdited());
        }
        if (e instanceof DeleteTeamRequest){
            DeleteSelectedTeam(((DeleteTeamRequest) e).getTeam_name());
        }
        if (e instanceof CreateNewTeamRequest){
            CreateDeafultTeam();
        }
        if (e instanceof ChangeStatsRequest){
            if (CheckStats()){
                stats = ((ChangeStatsRequest) e).getChangedstats();
                WriteTeamChanges(stats, current_team);
            }
        }
        if (e instanceof TeamsRequest){
            getTeams();
        }
        if (e instanceof TeamStatsRequest){
            current_team = ((TeamStatsRequest) e).getTeamFile();
            GetSelectedStats();
        }
    }

    private void ReplaceTeamName(String previous, String edited) {
        File previousTeam = new File(TEAM.getDEFAULT_PATH_FILE()+previous);
        File editedTeam = new File(TEAM.getDEFAULT_PATH_FILE()+edited);
        if (previousTeam.renameTo(editedTeam)){
            CheckTeamDirectory(path,teams);
            getTeams();
        }
    }

    private void DeleteSelectedTeam(String teamName) {
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
            System.out.println("File copied");
            Files.copy(Paths.get(TEAM_DEFAULT.getDEFAULT_PATH_RESOURCE()), Paths.get(TEAM.getDEFAULT_PATH_FILE()+"BLANK TEAM.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getTeams();
    }

    private void WriteTeamChanges(List<String[]> stats, String currentTeam) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(TEAM.getDEFAULT_PATH_FILE()+current_team));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String [] previous_figures = new String[24];
        for (int i = 0; i < 8; i++){
            scanner.nextLine();
        }
        for (int i = 0; i < 24; i++){
            previous_figures[i] = scanner.nextLine();
        }
        scanner.close();

        try {
            try (FileOutputStream file = new FileOutputStream(path+currentTeam)) {
                for (String[] stat : stats) {
                    CreateStatsLine(file, stat);
                }
                for (int i = 2; i > -1; i--){
                    String [] stat = stats.get(i);
                    CreateStatsLine(file, stat);
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

    private void CreateStatsLine(FileOutputStream file, String[] stat) {
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

    private boolean CheckStats() {
        return true;
    }

    private void GetSelectedStats() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(TEAM.getDEFAULT_PATH_FILE()+current_team));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        stats = new ArrayList<>();
        for (int i = 0; i < 5;i++){
            String [] stats_line = scanner.nextLine().split("#");
            stats.add(stats_line);
        }
        scanner.close();

        notify(new TeamStatsMessage(stats));
    }
}
