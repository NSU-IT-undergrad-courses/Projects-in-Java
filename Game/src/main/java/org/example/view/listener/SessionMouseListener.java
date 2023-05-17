package org.example.view.listener;

import org.example.observer.event.session.view.ChooseFigureListenerRequest;
import org.example.observer.event.session.view.MovesRequest;
import org.example.observer.event.session.view.ReleaseStatsListenerEvent;
import org.example.observer.event.session.view.StatsRequest;
import org.example.view.panels.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SessionMouseListener implements MouseListener {
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    private Integer index;

    public SessionMouseListener(GamePanel parent) {
        this.parent = parent;
    }

    private final GamePanel parent;


    @Override
    public void mouseClicked(MouseEvent e) {
        parent.notify(new MovesRequest(getIndex()));
        parent.notify(new ChooseFigureListenerRequest(getIndex()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        parent.notify(new StatsRequest(getIndex()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        parent.handle(new ReleaseStatsListenerEvent());
    }

}
