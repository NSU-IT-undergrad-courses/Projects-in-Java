package org.example.view.panels;

import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.session.*;
import org.example.view.Panels;
import org.example.view.listener.GameSessionMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameSessionPanel extends JPanel implements Observer {
    ArrayList<GameSessionMouseListener> listeners = new ArrayList<GameSessionMouseListener>(64);
    JButton[] figures = new JButton[64];
    private JWindow Stats = new JWindow();
    private JFrame Log = new JFrame();
    private JTextArea display  = new JTextArea();
    private String[] names = new String[64];

    public GameSessionPanel() {
        this.setSize(Panels.getX(), Panels.getY());
        this.setPreferredSize(new Dimension(Panels.getX(), Panels.getY()));
        setLayout(new GridLayout(8, 8, 5, 5));
        for (int i = 0; i < 64; i++) {
            listeners.add(i, new GameSessionMouseListener());
            listeners.get(i).setIndex(i);
        }
        PlaceLogWindows();
    }

    private void PlaceLogWindows() {
        // create the middle panel components
        this.display = new JTextArea(16, 58);
        display.setEditable(false); // set textArea non-editable
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Log.setVisible(true);
        //Add Textarea in to middle panel
        Log.add(scroll);
        Log.setBounds(0,400,200,500);
        Log.setVisible(true);
    }

    public void register(Observer o) {
        for (int i = 0; i < 64; i++) {
            listeners.get(i).register(o);
        }
    }

    @Override
    public void handle(Event e) {
        if (e instanceof GameSessionEvent) {
            if (e instanceof  ClearMovesEvent){
                DefaultAppearance();
            }
            if (e instanceof  FigureChosen){
                int index = ((FigureChosen) e).getIndex();
                figures[index].setBackground(Color.CYAN);

            }

            if (e instanceof MovesMessageEvent) {
                int index = ((MovesMessageEvent) e).getIndex();
                figures[index].setBackground(Color.CYAN);
                Integer[] moves = ((MovesMessageEvent) e).getMoves().toArray(new Integer[0]);
                for (int i = 0; i < moves.length; i += 2) {
                    Integer x = moves[i];
                    Integer y = moves[i + 1];
                    figures[y * 8 + x].setBackground(Color.green);
                }
            }
            if (e instanceof GameSessionStartEvent) {
                this.names = ((GameSessionStartEvent) e).getFiguresNames();
                for (int i = 0; i < 64; i++) {
                    JButton btn;
                    if (Objects.equals(names[i], "cell")) {
                        if ((i + i / 8) % 2 == 0) {
                            btn = createFigure("", "/images/figures/" + "white" + names[i] + ".png");
                        } else {
                            btn = createFigure("", "/images/figures/" + "black" + names[i] + ".png");
                        }
                    } else {
                        if (Objects.equals(names[i], "queen")) {
                            btn = createFigure("", "/images/figures/" + names[i] + "dance.gif");
                        } else
                            btn = createFigure("", "/images/figures/" + names[i] + ".png");
                    }
                    btn.setSize(new Dimension(50, 50));
                    btn.setPreferredSize(new Dimension(50, 50));
                    btn.addMouseListener(listeners.get(i));
                    if ((i + i / 8) % 2 == 0) {
                        btn.setBackground(Color.gray);
                    } else {
                        btn.setBackground(Color.white);
                    }
                    figures[i] = btn;
                    this.add(figures[i]);
                }
            }
            if (e instanceof StatsMessageEvent) {
                Stats = new JWindow();
                JLabel name = createLabel("Name: " + e.getName(), "/images/stats/name.png");
                JLabel attack = createLabel("Attack: " + ((StatsMessageEvent) e).getAttack(), "/images/stats/attack.png");
                JLabel defense = createLabel("Defense: " + ((StatsMessageEvent) e).getDefense(), "/images/stats/defense.png");
                Stats.setLayout(new FlowLayout());
                Stats.setPreferredSize(new Dimension(150, 100));
                Stats.setBackground(Color.ORANGE);
                Stats.add(name);
                Stats.add(attack);
                Stats.add(defense);
                Stats.pack();
                Stats.setVisible(true);
            }
            if (e instanceof ReleaseStatsEvent) {
                Stats.dispose();
            }
        }
    }

    private void DefaultAppearance() {
        for (int i = 0; i < 64; i++){
            if ((i + i/8)%2  == 0) {
                figures[i].setBackground(Color.gray);
            }
            else{
                figures[i].setBackground(Color.white);
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
        JLabel line = new JLabel(text, nameimage, SwingConstants.CENTER);
        return line;
    }

    private JButton createFigure(String text, String path) {
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
        JButton line = new JButton(text, nameimage);
        return line;
    }
}
