package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.GameSessionEndEvent;
import org.example.observer.event.screens.GameSessionStartEvent;
import org.example.observer.event.session.*;
import org.example.observer.event.session.controller.*;
import org.example.observer.event.session.view.ReleaseStatsListenerEvent;
import org.example.view.listener.SessionMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.example.GameConfiguration.*;

public class SessionPanel extends JPanel implements Observer, Observable {
    ArrayList<SessionMouseListener> listeners = new ArrayList<>(64);
    JButton[] figures = new JButton[64];
    private JWindow Stats = new JWindow();
    private String[] names = new String[64];

    public SessionPanel() {
        SetDefaultPanel(this, null);
        setLayout(new GridBagLayout());
        for (int i = 0; i < 64; i++) {
            listeners.add(i, new SessionMouseListener());
            listeners.get(i).setIndex(i);
        }
    }

    public void register(Observer o) {
        for (int i = 0; i < 64; i++) {
            listeners.get(i).register(o);
        }
    }

    @Override
    public void remove(Observer o) {
        for (int i = 0; i < 64; i++) {
            listeners.get(i).remove(o);
        }
    }

    @Override
    public void notify(Event e) {

    }

    @Override
    public void handle(Event e) {
        if (e instanceof GameSessionEvent) {
            if (e instanceof GameSessionEndEvent) {
                int defeated = ((GameSessionEndEvent) e).getDefeated();
                String winner;
                if (defeated == 0) {
                    winner = "White team";
                } else {
                    winner = "Black team";
                }
                JDialog GameResult = new JDialog();
                GameResult.setPreferredSize(new Dimension(400, 100));
                GameResult.setLocation(DEFAULT_X_RESOLUTION.getSIZE()/2, DEFAULT_Y_RESOLUTION.getSIZE()/2);
                GameResult.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                String message1 = winner + " has won the game!";
                String message2 = "Total moves: " + ((GameSessionEndEvent) e).getTurns();
                GameResult.add(new JTextArea(message1 + message2));
                GameResult.setTitle("🏆THE END🏆");
                GameResult.pack();
                GameResult.setVisible(true);
                this.setVisible(false);
            }

            if (e instanceof ClearMovesMessage) {
                DefaultAppearance();
            }
            if (e instanceof FailedAttackMessage) {
                JDialog fail = new JDialog();
                fail.setPreferredSize(new Dimension(400, 100));
                fail.setLocation(DEFAULT_X_RESOLUTION.getSIZE() / 2, DEFAULT_Y_RESOLUTION.getSIZE());
                fail.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                String message1 = "Yours figure failed to kill " + e.getName() + " due to low attack💩\n";
                String message2 = "!!!" + e.getName() + ": " + ((FailedAttackMessage) e).getDamage() + "!!!!";
                fail.add(new JTextArea(message1 + message2));
                fail.pack();
                fail.setVisible(true);
            }
            if (e instanceof InvalidMoveMessage) {
                JDialog fail = new JDialog();
                fail.setPreferredSize(new Dimension(400, 100));
                fail.setLocation(DEFAULT_X_RESOLUTION.getSIZE(), DEFAULT_Y_RESOLUTION.getSIZE() - 200);
                fail.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                fail.add(new JTextArea("Can't peform this move!!!"));
                fail.pack();
                fail.setVisible(true);
            }
            if (e instanceof FigureKilledMessage) {
                names[((FigureKilledMessage) e).getDestination()] = ((FigureKilledMessage) e).getDestination_name();
                names[((FigureKilledMessage) e).getSource()] = "cell";
                figures[((FigureKilledMessage) e).getSource()] = CreateFigure(((FigureKilledMessage) e).getSource());
                figures[((FigureKilledMessage) e).getDestination()] = CreateFigure(((FigureKilledMessage) e).getDestination());
                this.remove(((FigureKilledMessage) e).getSource());
                this.addImpl(figures[((FigureKilledMessage) e).getSource()],getGridBagConstraints(((FigureKilledMessage) e).getSource()) , ((FigureKilledMessage) e).getSource());
                this.addImpl(figures[((FigureKilledMessage) e).getDestination()], getGridBagConstraints(((FigureKilledMessage) e).getDestination()), ((FigureKilledMessage) e).getDestination());
                this.remove(((FigureKilledMessage) e).getDestination() + 1);
                this.revalidate();
            }
            if (e instanceof MovesMessage) {
                Integer[] moves = ((MovesMessage) e).getMoves().toArray(new Integer[0]);
                for (int i = 0; i < moves.length; i += 2) {
                    Integer x = moves[i];
                    Integer y = moves[i + 1];
                    figures[y * 8 + x].setBackground(Color.decode("#47DC5D"));
                }
            }
            if (e instanceof GameSessionStartEvent) {
                this.names = ((GameSessionStartEvent) e).getFiguresNames();
                for (int i = 0; i < 64; i++) {
                    figures[i] = CreateFigure(i);
                    GridBagConstraints constraints = getGridBagConstraints(i);
                    this.addImpl(figures[i], constraints, i);
                }
            }
            if (e instanceof StatsMessage) {
                Stats = new JWindow();
                JLabel name = createLabel("Name: " + e.getName(), "/images/stats/name.png");
                JLabel attack = createLabel("Attack: " + ((StatsMessage) e).getAttack(), "/images/stats/attack.png");
                JLabel defense = createLabel("Defense: " + ((StatsMessage) e).getDefense(), "/images/stats/defense.png");
                Stats.setLayout(new FlowLayout());
                Stats.setPreferredSize(new Dimension(150, 100));
                Stats.setBackground(Color.ORANGE);
                Stats.add(name);
                Stats.add(attack);
                Stats.add(defense);
                Stats.pack();
                Stats.setVisible(true);
            }
            if (e instanceof ReleaseStatsListenerEvent) {
                Stats.dispose();
            }
        }
    }

    private static GridBagConstraints getGridBagConstraints(int i) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = i %8;
        constraints.gridy = i /8;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill= GridBagConstraints.BOTH;
        constraints.weightx = 0.8;
        constraints.weighty = 0.8;
        return constraints;
    }

    private void DefaultAppearance() {
        for (int i = 0; i < 64; i++) {
            if ((i + i / 8) % 2 == 0) {
                figures[i].setBackground(Color.decode("#6C404F"));
            } else {
                figures[i].setBackground(Color.decode("#F5DEDD"));
            }
        }
    }

    private JLabel createLabel(String text, String path) {
        BufferedImage name = null;
        try {
            name = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        assert name != null;
        Image dimg = name.getScaledInstance(20, 20,
                Image.SCALE_FAST);
        ImageIcon nameimage = new ImageIcon(dimg);
        return new JLabel(text, nameimage, SwingConstants.CENTER);
    }

    private JButton PlaceFigureImage(String path) {
        BufferedImage name = null;
        try {
            name = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        assert name != null;
        Image dimg = name.getScaledInstance(50, 50,
                Image.SCALE_SMOOTH);
        ImageIcon nameimage = new ImageIcon(dimg);
        return new JButton("", nameimage);
    }

    private JButton CreateFigure(Integer index) {
        JButton btn;
        if (Objects.equals(names[index], "cell")) {
            btn = new JButton();
        } else {
            btn = PlaceFigureImage("/images/figures/" + names[index] + ".png");
        }
        btn.setSize(new Dimension(50, 50));
        btn.setPreferredSize(new Dimension(50, 50));
        btn.addMouseListener(listeners.get(index));
        if ((index + index / 8) % 2 == 0) {
            btn.setBackground(Color.decode("#6C404F"));
        } else {
            btn.setBackground(Color.white);
        }
        return btn;
    }
}
