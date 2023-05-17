package org.example.view.panels;

import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.GameSessionEndEvent;
import org.example.observer.event.screens.GameSessionStartMessage;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.observer.event.session.GameSessionEvent;
import org.example.observer.event.session.controller.*;
import org.example.observer.event.session.view.ReleaseStatsListenerEvent;
import org.example.view.RootViewComponent;
import org.example.view.listener.SessionMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.example.GameConfiguration.*;

public class SessionPanel extends GamePanel implements Observer {
    private Integer gridSize = 100;
    ArrayList<SessionMouseListener> listeners = new ArrayList<>(64);
    JButton[] figures = new JButton[64];
    private String[] names = new String[64];
    private JLabel name;
    private JLabel attack;
    private JLabel defense;

    public SessionPanel(RootViewComponent parent) {
        super(parent);
        setLayout(new GridBagLayout());
        for (int i = 0; i < 64; i++) {
            listeners.add(i, new SessionMouseListener(this));
            listeners.get(i).setIndex(i);
        }
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
            if (e instanceof GameSessionStartMessage) {
                this.names = ((GameSessionStartMessage) e).getFiguresNames();
                PaintField();
            }
            if (e instanceof StatsMessage) {
                name.setText(e.getName());
                attack.setText(((StatsMessage) e).getAttack().toString());
                defense.setText(((StatsMessage) e).getDefense().toString());
                this.repaint();

            }
            if (e instanceof ReleaseStatsListenerEvent) {
                name.setText("");
                attack.setText("");
                defense.setText("");
                this.repaint();
            }
        }
    }

    private void PaintField() {
        setVisible(false);
        removeAll();
        setMinimumSize(new Dimension(gridSize*11,gridSize*8));
        setPreferredSize(new Dimension(gridSize*11,gridSize*8));
        for (int i = 0; i < 64; i++) {
            figures[i] = CreateFigure(i);
            GridBagConstraints constraints = getGridBagConstraints(i);
            this.addImpl(figures[i], constraints, i);
        }
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 8;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        this.add( createLabel("", "/images/stats/name.png"),constraints);
        constraints.gridx++;
        name = new JLabel("");
        name.setMinimumSize(new Dimension(gridSize*2,gridSize));
        name.setPreferredSize(new Dimension(gridSize*2,gridSize));
        constraints.gridwidth = 2;
        this.add(name,constraints);
        constraints.gridwidth--;

        constraints.gridx--;
        constraints.gridy +=1;
        this.add(createLabel("", "/images/stats/attack.png"),constraints);
        constraints.gridx++;
        attack = new JLabel("");
        attack.setMinimumSize(new Dimension(gridSize*2,gridSize));
        attack.setPreferredSize(new Dimension(gridSize*2,gridSize));
        constraints.gridwidth = 2;
        this.add(attack,constraints);
        constraints.gridwidth--;

        constraints.gridx--;
        constraints.gridy +=1;
        this.add(createLabel("", "/images/stats/defense.png"),constraints);
        constraints.gridx++;
        defense = new JLabel("");
        defense.setMinimumSize(new Dimension(gridSize*2,gridSize));
        defense.setPreferredSize(new Dimension(gridSize*2,gridSize));

        constraints.gridwidth = 2;
        this.add(defense,constraints);
        constraints.gridx--;
        constraints.gridy +=1;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        int current_grdy = constraints.gridy;
        for (int i = current_grdy; i < 7; i++){
            CreateSizeButton(i-current_grdy+1, constraints);
            constraints.gridy++;
        }
        CreateSessionQuit(constraints);
        repaint();
        setVisible(true);
    }

    private void CreateSessionQuit(GridBagConstraints constraints) {
        JButton Quit = new JButton();
        Quit.setIcon(CreateImageIcon("/images/general/quit.png", DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE()*gridSize/100, DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE()*gridSize/100));
        Quit.setOpaque(true);
        Quit.setFocusPainted(false);
        Quit.setBorderPainted(true);
        Quit.setContentAreaFilled(false);
        Quit.setMinimumSize(new Dimension(gridSize*2,gridSize));
        Quit.setPreferredSize(new Dimension(gridSize*2,gridSize));
        Quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if( e.getClickCount() == 2){
                    SessionPanel.this.notify(new PlacePanelEvent(BOARDCREATOR.getPANEL_INDEX()));
                }
            }
        });
        this.add(Quit, constraints);
    }

    private void CreateSizeButton(int i, GridBagConstraints constraints) {
        Integer GridSize = i*25;
        JButton SizeButton = new JButton(String.valueOf(GridSize));
        SizeButton.setFocusPainted(true);
        SizeButton.setBorderPainted(true);
        SizeButton.setContentAreaFilled(false);
        if (GridSize.equals(gridSize)){
            SizeButton.setBackground(Color.decode("#5C404F"));
            SizeButton.setContentAreaFilled(true);
            SizeButton.setBorder(BorderFactory.createBevelBorder(5,Color.black,Color.black));
        }
        SizeButton.setMinimumSize(new Dimension(gridSize*2,gridSize));
        SizeButton.setPreferredSize(new Dimension(gridSize*2,gridSize));
        SizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SessionPanel.this.gridSize = GridSize;
                SessionPanel.this.PaintField();
            }
        });
        this.add(SizeButton, constraints);

    }

    private static GridBagConstraints getGridBagConstraints(int i) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = i %8;
        constraints.gridy = i /8;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill= GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
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
        JLabel jLabel = new JLabel(text, nameimage, SwingConstants.RIGHT);
        jLabel.setFont(new Font("Comic Sans",Font.BOLD,gridSize/4));
        return jLabel;
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
        btn.setSize(new Dimension(gridSize, gridSize));
        btn.setPreferredSize(new Dimension(gridSize, gridSize));
        btn.setMinimumSize(new Dimension(gridSize, gridSize));
        btn.addMouseListener(listeners.get(index));
        if ((index + index / 8) % 2 == 0) {
            btn.setBackground(Color.decode("#6C404F"));
        } else {
            btn.setBackground(Color.white);
        }
        return btn;
    }
}
