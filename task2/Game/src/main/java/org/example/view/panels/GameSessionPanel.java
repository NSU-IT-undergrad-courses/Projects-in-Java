package org.example.view.panels;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
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

public class GameSessionPanel extends JPanel implements Observer{
    ArrayList<GameSessionMouseListener> listeners = new ArrayList<GameSessionMouseListener>(64);
    private JWindow Stats = new JWindow();
    private String [] names = new String [64];
    public GameSessionPanel() {
        this.setSize(Panels.getX(),Panels.getY());
        this.setPreferredSize(new Dimension(Panels.getX(), Panels.getY()));
        setLayout(new GridLayout(8,8,5,5));
        for (int i  = 0; i < 64; i++){
            listeners.add(i,new GameSessionMouseListener());
            listeners.get(i).setIndex(i);
            listeners.get(i).notify( new RequestName(i));
//            JButton btn = createFigure("","/images/figures/"+names[i]+".png");
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(50,50));
            btn.addMouseListener(listeners.get(i));
            this.add(btn);

        }
    }
    public void register(Observer o){
        for (int i = 0; i < 64; i++){
        listeners.get(i).register(o);
        }
    }

    @Override
    public void handle(Event e) {
        if (e instanceof GameSessionEvent){
            if (e instanceof StatsMessageEvent){
                Stats = new JWindow();
                JLabel name = createLabel("Name: "+e.getName(),"/images/stats/name.png");
                JLabel attack = createLabel("Attack: "+((StatsMessageEvent) e).getAttack(),"/images/stats/attack.png");
                JLabel defense = createLabel("Defense: "+((StatsMessageEvent) e).getDefense(),"/images/stats/defense.png");
                Stats.setLayout(new FlowLayout());
                Stats.setPreferredSize(new Dimension(150,100));
                Stats.setBackground(Color.ORANGE);
                Stats.add(name);
                Stats.add(attack);
                Stats.add(defense);
                Stats.pack();
                Stats.setVisible(true);
            }
            if (e instanceof ReleaseStatsEvent){
                Stats.dispose();
            }
            if (e instanceof NamesMessageEvent){
                names = ((NamesMessageEvent) e).getNames();
            }
        }
    }

    private JLabel createLabel(String text, String path){
        BufferedImage name = null;
        try {
            name = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        assert name != null;
        Image dimg = name.getScaledInstance(20,20,
                Image.SCALE_SMOOTH);
        ImageIcon nameimage = new ImageIcon(dimg);
        JLabel line = new JLabel(text, nameimage,SwingConstants.LEFT);
        return line;
    }

    private JButton createFigure(String text, String path){
        System.out.println(path+"bruh");
        BufferedImage name = null;
        try {
            name = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        assert name != null;
        Image dimg = name.getScaledInstance(50,50,
                Image.SCALE_SMOOTH);
        ImageIcon nameimage = new ImageIcon(dimg);
        JButton line = new JButton(text, nameimage);
        return line;
    }
}
