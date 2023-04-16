package org.example.view.panels;

import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.GameSessionEndEvent;
import org.example.observer.event.screens.GameSessionStartEvent;
import org.example.observer.event.session.*;
import org.example.view.Panels;
import org.example.view.listener.GameSessionMouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    private String[] names = new String[64];

    public GameSessionPanel() {
        this.setSize(Panels.getX(), Panels.getY());
        this.setPreferredSize(new Dimension(Panels.getX(), Panels.getY()));
        setLayout(new GridLayout(8, 8, 0, 0));
        for (int i = 0; i < 64; i++) {
            listeners.add(i, new GameSessionMouseListener());
            listeners.get(i).setIndex(i);
        }
    }

    public void register(Observer o) {
        for (int i = 0; i < 64; i++) {
            listeners.get(i).register(o);
        }
    }

    @Override
    public void handle(Event e) {
        if (e instanceof GameSessionEvent) {
            if (e instanceof GameSessionEndEvent){
                int defeated = ((GameSessionEndEvent) e).getDefeated();
                String winner;
                if (defeated == 1){
                    winner = "Команда белых";
                }
                else{
                    winner = "Команда черных";
                }
                    JDialog GameResult = new JDialog();
                    GameResult.setPreferredSize(new Dimension(400,100));
                    GameResult.setLocation(Panels.getX()/2,Panels.getY());
                    GameResult.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    String message1 = winner+" одержала победу в этой игре!\n";
                    String message2 = "Всего было сделано: "+((GameSessionEndEvent) e).getTurns()+" ходов";
                    GameResult.add(new JTextArea(message1+message2));
                    GameResult.setTitle("🏆Конец игры🏆");
                    GameResult.pack();
                    GameResult.setVisible(true);
                    this.setVisible(false);
            }

            if (e instanceof  ClearMovesEvent){
                DefaultAppearance();
            }
            if (e instanceof  FigureChosen){
                int index = ((FigureChosen) e).getIndex();

            }
            if (e instanceof  FailedAttackEvent){
                JDialog fail = new JDialog();
                fail.setPreferredSize(new Dimension(400,100));
                fail.setLocation(Panels.getX()/2,Panels.getY());
                fail.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                String message1 = "Yours figure failed to kill "+e.getName()+" due to low attack💩\n";
                String message2 = "!!!"+e.getName()+": "+((FailedAttackEvent) e).getDamage()+"!!!!";
                fail.add(new JTextArea(message1+message2));
                fail.pack();
                fail.setVisible(true);
            }
            if (e instanceof  CantPerformMoveEvent){
                JDialog fail = new JDialog();
                fail.setPreferredSize(new Dimension(400,100));
                fail.setLocation(Panels.getX()/2,Panels.getY()-200);
                fail.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                fail.add(new JTextArea("Can't peform this move!!!"));
                fail.pack();
                fail.setVisible(true);
            }
            if (e instanceof FigureKilledEvent){
                names[((FigureKilledEvent) e).getDestination()] = ((FigureKilledEvent) e).getDestination_name();
                names[((FigureKilledEvent) e).getSource()] = "cell";
                figures[((FigureKilledEvent) e).getSource()] = CreateFigure(((FigureKilledEvent) e).getSource());
                figures[((FigureKilledEvent) e).getDestination()] = CreateFigure(((FigureKilledEvent) e).getDestination());
                this.remove(((FigureKilledEvent) e).getSource());
                this.addImpl(figures[((FigureKilledEvent) e).getSource()],null,((FigureKilledEvent) e).getSource());
                this.addImpl(figures[((FigureKilledEvent) e).getDestination()],null,((FigureKilledEvent) e).getDestination());
                this.remove(((FigureKilledEvent) e).getDestination()+1);
                this.revalidate();
            }
            if (e instanceof MovesMessageEvent) {
                int index = ((MovesMessageEvent) e).getIndex();
                Integer[] moves = ((MovesMessageEvent) e).getMoves().toArray(new Integer[0]);
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
                    this.addImpl(figures[i],null,i);
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
                figures[i].setBackground(Color.decode("#6C404F"));
            }
            else{
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
        JLabel line = new JLabel(text, nameimage, SwingConstants.CENTER);
        return line;
    }

    private JButton PlaceFigureImage(String text, String path) {
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

    private JButton CreateFigure(Integer index){
        JButton btn;
        if (Objects.equals(names[index], "cell")) {
            if ((index + index / 8) % 2 == 0) {
                btn = new JButton();
            } else {
                    btn = new JButton();
            }
        } else {
            btn = PlaceFigureImage("", "/images/figures/" + names[index] + ".png");
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
