package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.team.CreatedTeamMessage;
import org.example.observer.event.team.RequsetSelectedTeamFigures;
import org.example.observer.event.team.SendSelectedTeamStats;
import org.example.view.Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.awt.Font.BOLD;

public class TeamPanel extends JPanel implements Observable, Observer{
    private JButton Quit;
    private List<String> teams;
    private Integer overall = Panels.getOverall();
    private String icon = "team_logo.png";
    private final String [] figures = new String[]{"crook","horse","bishop","queen","king"};
    private final String [] stats = new String[]{"attack", "defense","trace", "distance"};
    private List<String[]> changedstats= new ArrayList<String []>();
    private String points = "points";

    public TeamPanel() {
        initComponents();
    }

    private void initComponents() {
        setMaximumSize(new Dimension(1280, 800));
        setMinimumSize(new Dimension(1280, 800));
        setPreferredSize(new Dimension(1280, 800));
        setBackground(new Color(0x003333));
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        DrawFigures(constraints);
        WriteStats(constraints);

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridwidth = 1;
        constraints.gridy = 8;
        constraints.gridheight = 1;
        Quit = new JButton();
        Quit.setOpaque(true);
        Quit.setBorderPainted(false);
        Quit.setFocusPainted(false);
        Quit.setContentAreaFilled(false);
        Quit.setVisible(true);
        Quit.addMouseListener(new MouseAdapter() {
            Observable o;
            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                o.notify(new PlacePanelEvent("mainscreen"));
            }
        }.SetObservable(this));
        this.add(Quit, constraints);
        this.setVisible(true);

        //---- Quit ----
        Quit.setIcon(CreateImageIcon("/images/mainscreen/quit.png", 87, 87));
    }

    private void WriteStats(GridBagConstraints constraints) {
        for (int i = 0; i < stats.length; i++){
            constraints.anchor = GridBagConstraints.SOUTH;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridx = GridBagConstraints.WEST-figures.length-1;
            constraints.gridwidth = 1;
            constraints.gridy = 3+i;
            constraints.gridheight = 1;
            JLabel stats_line = new JLabel(stats[i],CreateImageIcon("/images/stats/"+stats[i]+".png",50,50),JLabel.CENTER);
            stats_line.setForeground(Color.white);
            stats_line.setVisible(true);
            this.add(stats_line, constraints);
        }
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = GridBagConstraints.WEST-figures.length-1;
        constraints.gridwidth = 3;
        constraints.gridy = 3+stats.length;
        constraints.gridheight = 1;
        JLabel overall_points = new JLabel("Overall points: "+overall,CreateImageIcon("/images/stats/"+points+".png",100,100),JLabel.LEFT);
        overall_points.setFont(new Font("Comic Sans", BOLD,30));
        overall_points.setForeground(Color.white);
        overall_points.setVisible(true);
        this.add(overall_points, constraints);
        constraints.gridx++;

    }

    private void DrawFigures(GridBagConstraints constraints) {
        for (int i = 0; i < figures.length; i++){
            constraints.anchor = GridBagConstraints.SOUTH;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridx = GridBagConstraints.WEST-figures.length+i;
            constraints.gridwidth = 1;
            constraints.gridy = 2;
            constraints.gridheight = 1;
            JLabel figure_image = new JLabel(CreateImageIcon("/images/figures/"+"black "+figures[i]+".png",100,100));
            figure_image.setVisible(true);
            this.add(figure_image, constraints);
        }
    }

    private ImageIcon CreateImageIcon(String path, int x, int y) {
        BufferedImage name = null;
        try {
            name = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        assert name != null;
        Image dimg = name.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
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
        if (e instanceof CreatedTeamMessage){
            this.teams = ((CreatedTeamMessage) e).getTeams();
            DrawTeams();
        }
        if (e instanceof SendSelectedTeamStats){
            CreateStats((SendSelectedTeamStats) e);
        }
    }

    private void CreateStats(SendSelectedTeamStats e) {
        changedstats = e.getStats();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = GridBagConstraints.WEST-figures.length;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        for (int i = 0; i < changedstats.size();i++){
            constraints.gridy = 3;
            for (int j = 0; j < changedstats.get(i).length;j++){
                JButton figure_image = new JButton(changedstats.get(i)[j]);
                figure_image.setFont(new Font("Comic Sans",BOLD,16));
                figure_image.setForeground(Color.white);
                figure_image.setOpaque(true);
                figure_image.setBorderPainted(true);
                figure_image.setFocusPainted(false);
                figure_image.setContentAreaFilled(false);
                figure_image.setVisible(true);
                this.add(figure_image,constraints);
                int finalI = i;
                int finalJ = j;
                figure_image.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        setNewValue(finalI,finalJ);
                        ((JButton)e.getSource()).setText(changedstats.get(finalI)[finalJ]);
                    }
                });
                constraints.gridy++;
            }
            constraints.gridx++;
        }

    }

    private void setNewValue(int i, int j) {
        String [] stats = new String[]{"1","2","3","4","5","6","7","8","9"};
        String [] trace = new String []{"crosshair","diagonal","forward","horse","line","queen"};
        String [] distance = new String[]{"1 ","2 ","3 ","4 ","5 ","6 ","7 "};
        if (j < 2){
            Integer value = Integer.valueOf(changedstats.get(i)[j]);
            value = (value +1) % 10;
            changedstats.get(i)[j] = String.valueOf(value);
        }
        if (j == 3){
            List<String> trace_list = Arrays.asList(trace);
            int index = trace_list.indexOf(changedstats.get(i)[j]);
            index = (index + 1) % trace.length;
            changedstats.get(i)[j] = trace[index];
        }
//        if (j == 4){
//            List<String> trace_list = Arrays.asList(trace);
//            int index = trace_list.indexOf(changedstats.get(i)[j]);
//            index = (index + 1) % trace.length;
//            changedstats.get(i)[j] = trace[index];
//        }

    }

    private void DrawTeams() {
        for (int i =0; i < teams.size(); i++){
            JButton team = new JButton(teams.get(i).substring(0,teams.get(i).length()-4), CreateImageIcon("/images/stats/"+icon,50,50));
            team.setPreferredSize(new Dimension(200,50));
            GridBagConstraints menuconstraints = new GridBagConstraints();
            menuconstraints.anchor = GridBagConstraints.WEST;
            menuconstraints.fill = GridBagConstraints.CENTER;
            menuconstraints.gridx = 1;
            menuconstraints.gridy = i+2;
            SetTeamButtonSettings(team, menuconstraints);
            team.addMouseListener(new MouseAdapter() {
                Observable o;

                public MouseAdapter setFile_name(String file_name) {
                    this.file_name = file_name;
                    return this;
                }

                String file_name;
                Integer index;
                public MouseAdapter Init(Observable o, String file_name, Integer i){
                    this.o = o;
                    this.file_name = file_name;
                    index = i;
                    return this;
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    RequestTeamFigures(((JButton)e.getSource()).getText());
                }

            }.Init(this,teams.get(i),i));
        }
        JButton team = new JButton("Create new", CreateImageIcon("/images/stats/"+icon,50,50));
        team.setPreferredSize(new Dimension(200,50));
        GridBagConstraints menuconstraints = new GridBagConstraints();
        menuconstraints.anchor = GridBagConstraints.WEST;
        menuconstraints.fill = GridBagConstraints.CENTER;
        menuconstraints.gridx = 1;
        menuconstraints.gridy = teams.size()+2;
        SetTeamButtonSettings(team, menuconstraints);
        team.addMouseListener(new MouseAdapter() {
            Observable o;

            public MouseAdapter setFile_name(String file_name) {
                this.file_name = file_name;
                return this;
            }

            String file_name;
            Integer index;
            public MouseAdapter Init(Observable o, String file_name, Integer i){
                this.o = o;
                this.file_name = file_name;
                index = i;
                return this;
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                RequestTeamFigures(((JButton)e.getSource()).getText());
            }

        }.Init(this,"Create new", teams.size()+1));
        this.revalidate();
    }

    private void SetTeamButtonSettings(JButton team, GridBagConstraints menuconstraints) {
        menuconstraints.weightx = 0.1;
        menuconstraints.weighty = 0.2;
        menuconstraints.gridwidth = 5;
        menuconstraints.gridheight = 1;
        team.setForeground(Color.white);
        team.setOpaque(true);
        team.setFocusPainted(false);
        team.setContentAreaFilled(false);
        team.setVisible(true);
        this.add(team,menuconstraints);
    }

    private void RequestTeamFigures(String text) {
        notify(new RequsetSelectedTeamFigures(text+".txt"));
    }

}
