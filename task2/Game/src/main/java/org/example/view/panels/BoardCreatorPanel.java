package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.boardcreator.AvailableTeamsEvent;
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

    public List<String> getTeams() {
        return teams;
    }

    List<String> teams = new ArrayList<String>();
    private JButton Quit;
    private final String click = "click.wav";

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
        constraints.weightx = 2;
        constraints.gridx = 6;
        constraints.gridy = 2;
        JLabel fight = new JLabel(CreateImageIcon("/images/stats/"+fight_logo,150,150));
        this.add(fight,constraints);

        //---- Quit ----
        Quit.setIcon(CreateImageIcon("/images/mainscreen/quit.png", 87, 87));
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
            menuconstraints.gridy = i;
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
                    GridBagConstraints cons = new GridBagConstraints();
                    cons.gridx = 6;
                    cons.gridy = 6;
                    cons.weighty = 1;
                    cons.gridx = 1;
                    cons.gridwidth = 1;
                    BoardCreatorPanel.this.add((Component) e.getSource(),cons);
                    BoardCreatorPanel.this.repaint();
//                    o.notify();
                }

            }.Init(this,teams.get(i),i));
        }
        this.revalidate();
        for (int i =0; i < teams.size(); i++){
            JButton team = new JButton(teams.get(i).substring(0,teams.get(i).length()-4), CreateImageIcon("/images/stats/"+icon,50,50));
            team.setPreferredSize(new Dimension(200,50));
            GridBagConstraints menuconstraints = new GridBagConstraints();
            menuconstraints.anchor = GridBagConstraints.EAST;
            menuconstraints.fill = GridBagConstraints.CENTER;
            menuconstraints.gridx = 8;
            menuconstraints.gridy = i;
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
                    GridBagConstraints cons = new GridBagConstraints();
                    cons.gridx = 7;
                    cons.gridy = 6;
                    cons.weighty = 1;
                    cons.gridx = 1;
                    cons.gridwidth = 1;
                    BoardCreatorPanel.this.add((Component) e.getSource(),cons);
                    BoardCreatorPanel.this.revalidate();
//                    o.notify();
                }

            }.Init(this,teams.get(i),i));
        }
        this.revalidate();

    }


}
