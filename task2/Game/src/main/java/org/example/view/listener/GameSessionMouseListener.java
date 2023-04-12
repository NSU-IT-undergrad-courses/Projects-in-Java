package org.example.view.listener;

import org.example.observer.Observable;
import org.example.observer.ObservableImpl;
import org.example.observer.event.session.ReleaseStatsEvent;
import org.example.observer.event.session.RequestMovesEvent;
import org.example.observer.event.session.RequestStatsEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        System.out.println(e.getSource());
        notify(new RequestMovesEvent(getIndex()));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Нажали "+getIndex());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Навели на "+getIndex());
        notify(new RequestStatsEvent(getIndex()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        notify(new ReleaseStatsEvent());
    }

}
