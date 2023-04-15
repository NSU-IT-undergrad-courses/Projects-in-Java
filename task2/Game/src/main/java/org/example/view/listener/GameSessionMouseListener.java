package org.example.view.listener;

import org.example.observer.Observable;
import org.example.observer.ObservableImpl;
import org.example.observer.event.session.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class GameSessionMouseListener extends ObservableImpl implements MouseListener, Observable {
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    private Integer index;


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        notify(new RequestMovesEvent(getIndex()));
        notify(new FigureChosen(getIndex()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        notify(new RequestStatsEvent(getIndex()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        notify(new ReleaseStatsEvent());
    }

}
