package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.AvailableTeamsEvent;
import org.example.observer.event.boardcreator.SendTeamsEvent;
import org.example.observer.event.screens.PlacePanelEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardCreatorPanel extends JPanel implements Observable, Observer {
    private String icon = "team_logo.png";
    private String fight_logo = "fight.png";
    private Boolean first = false;
    private Boolean second = false;

    public List<String> getTeams() {
        return teams;
    }

    List<String> teams = new ArrayList<String>();
    private JButton Quit;
    private JButton Start;
    private JButton [] chosen = new JButton[2];
    private final String click = "click.wav";
    private final String start = "start.png";
    private final String startdeactive = "startdeactive.png";

    public BoardCreatorPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        setMaximumSize(new Dimension(1280, 800));
        setMinimumSize(new Dimension(1280, 800));
        setPreferredSize(new Dimension(1280, 800));
        setBackground(new Color(0x003333));
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 10;
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
        this.add(Quit,constraints);
        this.setVisible(true);

        constraints.weighty = 2;
        constraints.weightx = 4;
        constraints.gridx = 7;
        constraints.gridy = 0;
        constraints.anchor= GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.BOTH;
        JLabel fight = new JLabel(CreateImageIcon("/images/stats/"+fight_logo,200,200));
        fight.setPreferredSize(new Dimension(200,200));
        this.add(fight,constraints);

        GridBagConstraints menuconstraints = new GridBagConstraints();
        menuconstraints.anchor = GridBagConstraints.SOUTH;
        menuconstraints.fill = GridBagConstraints.CENTER;
        menuconstraints.gridx = 9;
        menuconstraints.gridy = 2;
        menuconstraints.gridwidth = 1;
        menuconstraints.gridheight = 1;
        chosen[1] = new JButton("Choose team",CreateImageIcon("/images/stats/"+icon,50,50));
        chosen[1].setPreferredSize(new Dimension(200,50));
        chosen[1].setOpaque(true);
        chosen[1].setBorderPainted(false);
        chosen[1].setFocusPainted(false);
        chosen[1].setContentAreaFilled(false);
        chosen[1].setVisible(true);
        chosen[1].setForeground(Color.white);
        BoardCreatorPanel.this.add(chosen[1],menuconstraints);
        BoardCreatorPanel.this.revalidate();

        menuconstraints.gridx = 6;
        chosen[0] = new JButton("Choose team",CreateImageIcon("/images/stats/"+icon,50,50));
        chosen[0].setPreferredSize(new Dimension(200,50));
        chosen[0].setOpaque(true);
        chosen[0].setBorderPainted(false);
        chosen[0].setFocusPainted(false);
        chosen[0].setContentAreaFilled(false);
        chosen[0].setVisible(true);
        chosen[0].setForeground(Color.white);
        BoardCreatorPanel.this.add(chosen[0],menuconstraints);
        BoardCreatorPanel.this.revalidate();

        //---- Quit ----
        Quit.setIcon(CreateImageIcon("/images/mainscreen/quit.png", 87, 87));


        menuconstraints = new GridBagConstraints();
        menuconstraints.anchor = GridBagConstraints.CENTER;
        menuconstraints.fill = GridBagConstraints.HORIZONTAL;
        menuconstraints.gridx = 7;
        menuconstraints.gridy = 4;
        menuconstraints.gridwidth = 2;
        menuconstraints.gridheight = 3;
        Start = new JButton(CreateImageIcon("/images/stats/"+startdeactive,250,190));
        Start.setPreferredSize(new Dimension(250,190));
        Start.setOpaque(true);
        Start.setFocusPainted(false);
        Start.setBorderPainted(false);
        Start.setContentAreaFilled(false);
        Start.setVisible(true);
        BoardCreatorPanel.this.add(Start,menuconstraints);
        BoardCreatorPanel.this.revalidate();
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
        if (e instanceof AvailableTeamsEvent){
            this.teams = ((AvailableTeamsEvent) e).getTeams();
            CreateTeams(teams);
        }
    }


    private void CreateTeams(List<String> teams) {
        for (int i =0; i < teams.size(); i++){
            JButton team = new JButton(teams.get(i).substring(0,teams.get(i).length()-4), CreateImageIcon("/images/stats/"+icon,50,50));
            team.setPreferredSize(new Dimension(200,50));
            GridBagConstraints menuconstraints = new GridBagConstraints();
            menuconstraints.anchor = GridBagConstraints.WEST;
            menuconstraints.fill = GridBagConstraints.CENTER;
            menuconstraints.gridx = 1;
            menuconstraints.gridy = i+2;
            menuconstraints.weightx = 0.1;
            menuconstraints.weighty = 0.2;
            menuconstraints.gridwidth = 5;
            menuconstraints.gridheight = 1;
            team.setForeground(Color.white);
            team.setOpaque(true);
            team.setFocusPainted(false);
            team.setContentAreaFilled(false);
            this.add(team,menuconstraints);
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
                    GridBagConstraints menuconstraints = new GridBagConstraints();
                    menuconstraints.anchor = GridBagConstraints.SOUTH;
                    menuconstraints.fill = GridBagConstraints.CENTER;
                    menuconstraints.gridx = 6;
                    menuconstraints.gridy = 2;
                    menuconstraints.gridwidth = 1;
                    menuconstraints.gridheight = 1;
                    JButton chsnbtn = (JButton) e.getSource();
                    if (chosen[0]!=null){BoardCreatorPanel.this.remove(chosen[0]);}
                    chosen[0] = new JButton((String)chsnbtn.getText(),CreateImageIcon("/images/stats/"+icon,50,50));
                    chosen[0].setPreferredSize(new Dimension(200,50));
                    chosen[0].setOpaque(true);
                    chosen[0].setBorderPainted(false);
                    chosen[0].setFocusPainted(false);
                    chosen[0].setContentAreaFilled(false);
                    chosen[0].setVisible(true);
                    chosen[0].setForeground(Color.white);
                    BoardCreatorPanel.this.add(chosen[0],menuconstraints);
                    BoardCreatorPanel.this.revalidate();
                    BoardCreatorPanel.this.first = true;
                    CheckTeams();
                }

            }.Init(this,teams.get(i),i));
        }
        this.revalidate();
        for (int i =0; i < teams.size(); i++){
            JButton team = new JButton(teams.get(i).substring(0,teams.get(i).length()-4), CreateImageIcon("/images/stats/"+icon,50,50));
            team.setPreferredSize(new Dimension(200,50));
            GridBagConstraints menuconstraints = new GridBagConstraints();
            menuconstraints.anchor = GridBagConstraints.WEST;
            menuconstraints.fill = GridBagConstraints.CENTER;
            menuconstraints.gridx = 10;
            menuconstraints.gridy = i+2;
            menuconstraints.weightx = 0.1;
            menuconstraints.weighty = 0.2;
            menuconstraints.gridwidth = 5;
            menuconstraints.gridheight = 1;
            team.setForeground(Color.white);
            team.setOpaque(true);
            team.setFocusPainted(false);
            team.setContentAreaFilled(false);
            this.add(team,menuconstraints);
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
                    GridBagConstraints menuconstraints = new GridBagConstraints();
                    menuconstraints.anchor = GridBagConstraints.SOUTH;
                    menuconstraints.fill = GridBagConstraints.CENTER;
                    menuconstraints.gridx = 9;
                    menuconstraints.gridy = 2;
                    menuconstraints.gridwidth = 1;
                    menuconstraints.gridheight = 1;
                    JButton chsnbtn = (JButton) e.getSource();
                    if (chosen[1]!=null){BoardCreatorPanel.this.remove(chosen[1]);}
                    chosen[1] = new JButton((String)chsnbtn.getText(),CreateImageIcon("/images/stats/"+icon,50,50));
                    chosen[1].setPreferredSize(new Dimension(200,50));
                    chosen[1].setOpaque(true);
                    chosen[1].setBorderPainted(false);
                    chosen[1].setFocusPainted(false);
                    chosen[1].setContentAreaFilled(false);
                    chosen[1].setVisible(true);
                    chosen[1].setForeground(Color.white);
                    BoardCreatorPanel.this.add(chosen[1],menuconstraints);
                    BoardCreatorPanel.this.revalidate();
                    BoardCreatorPanel.this.second = true;
                    CheckTeams();
                }

            }.Init(this,teams.get(i),i));
        }
        this.revalidate();

    }

    private void CheckTeams() {
        if (first && second){
            Start.setBorder(BorderFactory.createLineBorder(Color.yellow,5));
            Start.setIcon(CreateImageIcon("/images/stats/"+start,250,190));
            Start.setBorderPainted(true);
            Start.addMouseListener(new MouseAdapter() {
                Observable o;
                public MouseAdapter Init(Observable o){
                    this.o = o;
                    return this;
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    o.notify(new SendTeamsEvent(chosen[0].getText(),chosen[0].getText()));
                }

            }.Init(this));
        }
    }


}
