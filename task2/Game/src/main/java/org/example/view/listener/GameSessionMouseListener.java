package org.example.view.listener;

import org.example.observer.Observable;
import org.example.observer.ObservableImpl;
import org.example.observer.event.session.*;

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
        if (e.getClickCount() == 2){
            notify(new FigureChosen(getIndex()));
            notify(new RequestMovesEvent(getIndex()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        notify(new RequestMovesEvent(getIndex()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        notify(new ClearMovesEvent());
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
