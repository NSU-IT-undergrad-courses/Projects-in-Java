package org.example.view.panels;

import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.controller.BoardsTeamsMessage;
import org.example.observer.event.boardcreator.view.ChooseTeamRequest;
import org.example.view.RootViewComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static org.example.GameConfiguration.CreateImageIcon;
import static org.example.GameConfiguration.IMG_BOARDCREATOR;

public class BoardCreatorPanel extends GamePanel implements Observer {
    private final String ICON = "team_logo.png";
    private Boolean first = false;
    private Boolean second = false;

    List<String> teams = new ArrayList<>();
    private JButton Start;
    private JButton [] chosen = new JButton[2];

    public BoardCreatorPanel(RootViewComponent parent) {
        super(parent);
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this);
        setBackground(new Color(0x003333));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weighty = 2;
        constraints.weightx = 4;
        constraints.gridx = 7;
        constraints.gridy = 0;
        constraints.anchor= GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.BOTH;
        String FIGHT_LOGO = "fight.png";
        JLabel fight = new JLabel(CreateImageIcon(IMG_BOARDCREATOR.getDEFAULT_PATH_RESOURCE()+ FIGHT_LOGO,200,200));
        fight.setPreferredSize(new Dimension(200,200));
        this.add(fight,constraints);

        GridBagConstraints menuconstraints = new GridBagConstraints();
        menuconstraints.anchor = GridBagConstraints.SOUTH;
        menuconstraints.fill = GridBagConstraints.CENTER;
        menuconstraints.gridx = 9;
        menuconstraints.gridy = 2;
        menuconstraints.gridwidth = 1;
        menuconstraints.gridheight = 1;
        chosen[1] = new JButton("Choose team",CreateImageIcon(IMG_BOARDCREATOR.getDEFAULT_PATH_RESOURCE()+ ICON,50,50));
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
        chosen[0] = new JButton("Choose team",CreateImageIcon(IMG_BOARDCREATOR.getDEFAULT_PATH_RESOURCE()+ ICON,50,50));
        chosen[0].setPreferredSize(new Dimension(200,50));
        chosen[0].setOpaque(true);
        chosen[0].setBorderPainted(false);
        chosen[0].setFocusPainted(false);
        chosen[0].setContentAreaFilled(false);
        chosen[0].setVisible(true);
        chosen[0].setForeground(Color.white);
        BoardCreatorPanel.this.add(chosen[0],menuconstraints);
        BoardCreatorPanel.this.revalidate();

        menuconstraints = new GridBagConstraints();
        menuconstraints.anchor = GridBagConstraints.CENTER;
        menuconstraints.fill = GridBagConstraints.HORIZONTAL;
        menuconstraints.gridx = 7;
        menuconstraints.gridy = 4;
        menuconstraints.gridwidth = 2;
        menuconstraints.gridheight = 3;
        String startdeactive = "startdeactive.png";
        Start = new JButton(CreateImageIcon(IMG_BOARDCREATOR.getDEFAULT_PATH_RESOURCE()+ startdeactive,250,190));
        Start.setPreferredSize(new Dimension(250,190));
        Start.setOpaque(true);
        Start.setFocusPainted(false);
        Start.setBorderPainted(false);
        Start.setContentAreaFilled(false);
        Start.setVisible(true);
        BoardCreatorPanel.this.add(Start,menuconstraints);
        BoardCreatorPanel.this.revalidate();
    }

    @Override
    public void handle(Event e) {
        if (e instanceof BoardsTeamsMessage){
            this.removeAll();
            initComponents();
            this.teams = ((BoardsTeamsMessage) e).getTeams();
            CreateTeams(teams);
        }
    }


    private void CreateTeams(List<String> teams) {
        for (int i =0; i < teams.size(); i++){
            JButton team = new JButton(teams.get(i).substring(0,teams.get(i).length()-4), CreateImageIcon(IMG_BOARDCREATOR.getDEFAULT_PATH_RESOURCE()+ ICON,50,50));
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

                String file_name;
                Integer index;
                public MouseAdapter Init(String file_name, Integer i){
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
                    if (chosen[0]!=null){
                        BoardCreatorPanel.this.remove(chosen[0]);}
                    chosen[0] = new JButton(chsnbtn.getText(),CreateImageIcon(IMG_BOARDCREATOR.getDEFAULT_PATH_RESOURCE()+ ICON,50,50));
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

            }.Init(teams.get(i),i));
        }
        this.revalidate();
        for (int i =0; i < teams.size(); i++){
            JButton team = new JButton(teams.get(i).substring(0,teams.get(i).length()-4), CreateImageIcon(IMG_BOARDCREATOR.getDEFAULT_PATH_RESOURCE()+ ICON,50,50));
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
                String file_name;
                Integer index;
                public MouseAdapter Init(String file_name, Integer i){
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
                    if (chosen[1]!=null){
                        BoardCreatorPanel.this.remove(chosen[1]);}
                    chosen[1] = new JButton(chsnbtn.getText(),CreateImageIcon(IMG_BOARDCREATOR.getDEFAULT_PATH_RESOURCE()+ ICON,50,50));
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

            }.Init(teams.get(i),i));
        }
        this.revalidate();

    }

    private void CheckTeams() {
        if (first && second){
            Start.setBorder(BorderFactory.createLineBorder(Color.yellow,5));
            String start = "start.png";
            Start.setIcon(CreateImageIcon(IMG_BOARDCREATOR.getDEFAULT_PATH_RESOURCE()+ start,250,190));
            Start.setBorderPainted(true);
            Start.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    BoardCreatorPanel.this.notify(new ChooseTeamRequest(chosen[0].getText(),chosen[1].getText()));
                    chosen   = new JButton[2];
                    first = second = false;
                    BoardCreatorPanel.this.removeAll();
                    initComponents();
                    CreateTeams(teams);
                }

            });
        }
    }


}
