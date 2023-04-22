package org.example.view.listener;

import org.example.observer.Observable;
import org.example.observer.ObservableImpl;
import org.example.observer.event.session.view.FigureChosenListenerEvent;
import org.example.observer.event.session.view.ReleaseStatsListenerEvent;
import org.example.observer.event.session.view.MovesRequest;
import org.example.observer.event.session.view.StatsRequest;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SessionMouseListener extends ObservableImpl implements MouseListener, Observable {
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
        notify(new MovesRequest(getIndex()));
        notify(new FigureChosenListenerEvent(getIndex()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        notify(new StatsRequest(getIndex()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        notify(new ReleaseStatsListenerEvent());
    }

}
