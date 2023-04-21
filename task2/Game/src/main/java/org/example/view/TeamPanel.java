package org.example.view;

import org.example.GameConstants;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.team.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.awt.Font.BOLD;
import static org.example.GameConstants.*;

public class TeamPanel extends JPanel implements Observable, Observer {
    private List<String> teams = new ArrayList<>();
    private final Integer overall = OVERALL_TEAM_POINTS.getSIZE();
    private final String TEAM_ICON = "team_logo.png";
    private final String[] figures = new String[]{"crook", "horse", "bishop", "queen", "king"};
    private final String[] stats = new String[]{"attack", "defense", "trace", "distance"};
    private List<String[]> changedstats = new ArrayList<String[]>();
    private final String POINTS = "points.png";
    private String DELETE = "delete.png";
    private JButton SHEESH;

    public TeamPanel() {
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this,this);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        DrawFigures(constraints);
        WriteStats(constraints);
    }

    private void WriteStats(GridBagConstraints constraints) {
        for (int i = 0; i < stats.length; i++) {
            constraints.anchor = GridBagConstraints.SOUTH;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridx = GridBagConstraints.WEST - figures.length - 1;
            constraints.gridwidth = 1;
            constraints.gridy = 3 + i;
            constraints.gridheight = 1;
            JLabel stats_line = new JLabel(stats[i], CreateImageIcon(IMG_TEAM.getDEFAULT_PATH_RESOURCE() + stats[i] + ".png", 50, 50), JLabel.CENTER);
            stats_line.setForeground(Color.white);
            stats_line.setVisible(true);
            this.add(stats_line, constraints);
        }
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = GridBagConstraints.WEST - figures.length - 1;
        constraints.gridwidth = 3;
        constraints.gridy = 3 + stats.length;
        constraints.gridheight = 1;
        JLabel overall_points = new JLabel("Overall points: " + overall, CreateImageIcon(IMG_TEAM.getDEFAULT_PATH_RESOURCE()+ POINTS, 100, 100), JLabel.LEFT);
        overall_points.setFont(new Font("Comic Sans", BOLD, 30));
        overall_points.setForeground(Color.white);
        overall_points.setVisible(true);
        this.add(overall_points, constraints);
        constraints.gridx++;

    }

    private void DrawFigures(GridBagConstraints constraints) {
        for (int i = 0; i < figures.length; i++) {
            constraints.anchor = GridBagConstraints.SOUTH;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridx = GridBagConstraints.WEST - figures.length + i;
            constraints.gridwidth = 1;
            constraints.gridy = 2;
            constraints.gridheight = 1;
            JLabel figure_image = new JLabel(CreateImageIcon(IMG_FIGURES.getDEFAULT_PATH_RESOURCE()+ "black " + figures[i] + ".png", 100, 100));
            figure_image.setVisible(true);
            this.add(figure_image, constraints);
        }
    }


    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer o) {
        observers.add(o);
        notify(new CreatedTeamsRequest());
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
        if (e instanceof CreatedTeamMessage) {
            this.teams = ((CreatedTeamMessage) e).getTeams();
            RefreshPanel();
        }
        if (e instanceof SendSelectedTeamStats) {
            changedstats = ((SendSelectedTeamStats) e).getStats();
            RefreshPanel();
        }
    }

    private void RefreshPanel() {
        this.removeAll();
        SetDefaultPanel(this,this);
        DrawTeams();
        DrawFigures(new GridBagConstraints());
        CreateStats();
        this.repaint();
    }

    private void CreateStats() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = GridBagConstraints.WEST - figures.length;
        constraints.gridwidth = 1;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        for (int i = 0; i < changedstats.size(); i++) {
            constraints.fill = GridBagConstraints.NONE;
            constraints.gridy = 3;
            for (int j = 1; j < changedstats.get(i).length; j++) {
                JButton figure_image = new JButton(changedstats.get(i)[j]);
                figure_image.setFont(new Font("Comic Sans", BOLD, 11));
                figure_image.setForeground(Color.white);
                figure_image.setOpaque(true);
                figure_image.setBorderPainted(false);
                figure_image.setFocusPainted(false);
                figure_image.setContentAreaFilled(false);
                figure_image.setVisible(true);
                this.add(figure_image, constraints);
                int finalI = i;
                int finalJ = j;
                figure_image.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        setNewValue(finalI, finalJ);
                        ((JButton) e.getSource()).setText(changedstats.get(finalI)[finalJ]);
                        RefreshPanel();
                        TeamPanel.this.notify(new NewStatsMessage(changedstats));
                    }
                });
                constraints.gridy++;
            }
            constraints.gridx++;
        }

    }

    private void setNewValue(int i, int j) {
        String[] stats = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] trace = new String[]{"crosshair", "diagonal", "forward", "horse", "line", "queen"};
        String[] distance = new String[]{"1", "2", "3", "4", "5", "6", "7"};
        if (j < 3) {
            Integer value = Integer.valueOf(changedstats.get(i)[j]);
            value = (value + 1) % 10;
            changedstats.get(i)[j] = String.valueOf(value);
        }
        if (j == 3) {
            List<String> trace_list = Arrays.asList(trace);
            int index = trace_list.indexOf(changedstats.get(i)[j]);
            index = (index + 1) % trace.length;
            changedstats.get(i)[j] = trace[index];
        }
        if (j == 4){
            List<String> distance_list = Arrays.asList(trace);
            Character last = changedstats.get(i)[j].charAt(changedstats.get(i)[j].length()-1);
            StringBuilder new_distance = new StringBuilder();
            Integer new_max_distance = Integer.valueOf(last);
            new_max_distance = (new_max_distance +1) % (distance.length+1);
            if (new_max_distance == 0) {
                new_max_distance++;
            }
            for (int distance_iterator = 0; distance_iterator < new_max_distance; distance_iterator++){
                new_distance.append(distance_iterator + 1);
                new_distance.append(" ");
            }
            new_distance.deleteCharAt(new_distance.length()-1);
            changedstats.get(i)[j] = String.valueOf(new_distance);



        }

    }

    private void DrawTeams() {
        for (int i = 0; i < teams.size(); i++) {
            CreateTeamLine(i);
        }
        Add_CreateNew();
        this.revalidate();
    }

    private void Add_CreateNew() {
        JButton team = new JButton("Create new", CreateImageIcon(IMG_TEAM.getDEFAULT_PATH_RESOURCE() + TEAM_ICON, 50, 50));
        team.setPreferredSize(new Dimension(200, 50));
        team.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED,Color.yellow,Color.green));
        GridBagConstraints menuconstraints = new GridBagConstraints();
        menuconstraints.anchor = GridBagConstraints.WEST;
        menuconstraints.fill = GridBagConstraints.CENTER;
        menuconstraints.gridx = 1;
        menuconstraints.gridy = teams.size() + 2;
        SetTeamButtonSettings(team, menuconstraints);
        this.add(team, menuconstraints);
        team.addMouseListener(new MouseAdapter() {
            Observable o;

            public MouseAdapter setFile_name(String file_name) {
                this.file_name = file_name;
                return this;
            }

            String file_name;
            Integer index;

            public MouseAdapter Init(Observable o, String file_name, Integer i) {
                this.o = o;
                this.file_name = file_name;
                index = i;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                TeamPanel.this.notify(new CreateNewTeam());
            }

        }.Init(this, "Create new", teams.size() + 1));
    }

    private void CreateTeamLine(int i) {
        JButton team = new JButton(teams.get(i).substring(0, teams.get(i).length() - 4), CreateImageIcon(IMG_TEAM.getDEFAULT_PATH_RESOURCE()+ TEAM_ICON, 50, 50));
        team.setPreferredSize(new Dimension(200, 50));
        GridBagConstraints menuconstraints = new GridBagConstraints();
        menuconstraints.anchor = GridBagConstraints.WEST;
        menuconstraints.fill = GridBagConstraints.CENTER;
        menuconstraints.gridx = 1;
        menuconstraints.gridy = i + 2;
        SetTeamButtonSettings(team, menuconstraints);
        team.addMouseListener(new MouseAdapter() {
            Observable o;

            public MouseAdapter setFile_name(String file_name) {
                this.file_name = file_name;
                return this;
            }

            String file_name;
            Integer index;

            public MouseAdapter Init(Observable o, String file_name, Integer i) {
                this.o = o;
                this.file_name = file_name;
                index = i;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JDialog team_dialog = new JDialog();
                    team_dialog.setLayout(new GridBagLayout());
                    team_dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    team_dialog.setPreferredSize(new Dimension(200, 100));
                    team_dialog.setMinimumSize(new Dimension(200, 100));
                    team_dialog.setMaximumSize(new Dimension(200, 100));
                    team_dialog.setBackground(DEFAULT_COLOR.getCOLOR());
                    team_dialog.setForeground(DEFAULT_COLOR.getCOLOR());
                    team_dialog.setTitle(teams.get(i).substring(0, teams.get(i).length() - 4) + " team settings");
                    team_dialog.setUndecorated(true);
                    team_dialog.setLocation(DEFAULT_X_RESOLUTION.getSIZE() / 2, DEFAULT_Y_RESOLUTION.getSIZE() / 2);
                    JButton delete = new JButton("Delete");
                    JButton rename = new JButton("Rename");
                    SetTeamButtonSettings(delete, new GridBagConstraints());
                    SetTeamButtonSettings(rename, new GridBagConstraints());
                    delete.setForeground(Color.BLACK);
                    rename.setForeground(Color.BLACK);
                    delete.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            TeamPanel.this.notify(new DeleteTeam(teams.get(i)));
                            team_dialog.dispose();
                        }
                    });
                    rename.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            team_dialog.dispose();
                            JDialog rename_dialog = new JDialog();
                            rename_dialog.setLayout(new GridBagLayout());
                            rename_dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            rename_dialog.setPreferredSize(new Dimension(400, 150));
                            rename_dialog.setMinimumSize(new Dimension(400, 150));
                            rename_dialog.setMaximumSize(new Dimension(400, 150));
                            rename_dialog.setBackground(DEFAULT_COLOR.getCOLOR());
                            rename_dialog.setForeground(DEFAULT_COLOR.getCOLOR());
                            rename_dialog.setTitle(teams.get(i).substring(0, teams.get(i).length() - 4) + " team's new name");
                            rename_dialog.setUndecorated(true);
                            rename_dialog.setLocation(DEFAULT_X_RESOLUTION.getSIZE() / 2, DEFAULT_Y_RESOLUTION.getSIZE() / 2);
                            JTextArea  enter_name = new JTextArea("Enter new name in the field below, then press [ENTER]");
                            enter_name.setEditable(false);
                            enter_name.setPreferredSize(new Dimension(400,70));
                            enter_name.setMaximumSize(new Dimension(400,50));
                            enter_name.setMinimumSize(new Dimension(400,50));
                            menuconstraints.weightx = 0.1;
                            menuconstraints.weighty = 0.2;
                            menuconstraints.gridwidth = 1;
                            menuconstraints.gridheight = 1;
                            menuconstraints.gridx = 0;
                            menuconstraints.gridy = 0;
                            menuconstraints.anchor = GridBagConstraints.SOUTH;
                            enter_name.setBackground(Color.gray);
                            enter_name.setForeground(Color.white);
                            enter_name.setOpaque(true);
                            enter_name.setVisible(true);
                            enter_name.setVisible(true);
                            rename_dialog.add(enter_name,menuconstraints);

                            JTextField  new_name = new JTextField("TEAM NAME", 20);
                            new_name.setPreferredSize(new Dimension(400,50));
                            new_name.setMaximumSize(new Dimension(400,50));
                            new_name.setMinimumSize(new Dimension(400,50));
                            menuconstraints.weightx = 0.1;
                            menuconstraints.weighty = 0.2;
                            menuconstraints.gridwidth = 1;
                            menuconstraints.gridheight = 1;
                            menuconstraints.gridx = 0;
                            menuconstraints.gridy = 1;
                            new_name.setBackground(Color.gray);
                            new_name.setForeground(Color.white);
                            new_name.setOpaque(true);
                            new_name.setVisible(true);
                            new_name.setVisible(true);
                            new_name.addActionListener(e1 -> {
                                String name_written =  new_name.getText();
                                TeamPanel.this.notify(new ReplaceName(teams.get(i),name_written+".txt"));
                                rename_dialog.dispose();
                            });
                            rename_dialog.setModal(true);
                            rename_dialog.add(new_name,menuconstraints);
                            rename_dialog.setVisible(true);
                            rename_dialog.revalidate();

                        }
                    });
                    team_dialog.add(delete);
                    team_dialog.add(rename);
                    team_dialog.setVisible(true);
                }
                RequestTeamFigures(((JButton) e.getSource()).getText());
            }

        }.Init(this, teams.get(i), i));
        this.add(team, menuconstraints);
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
    }

    private void RequestTeamFigures(String text) {
        notify(new RequsetSelectedTeamFigures(text + ".txt"));
    }

}
