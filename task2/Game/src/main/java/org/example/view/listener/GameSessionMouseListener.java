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

    private Color originalBG;
    private long mousePressedTime;
    private long delay = 150;
    private Timer flashTimer;
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
//
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        mousePressedTime = e.getWhen();
//        if(flashTimer != null)
//            flashTimer.cancel();
//        flashTimer = new Timer("flash timer");
//        flashTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                e.getComponent().setVisible(false);
//            }
//        }, delay);
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        flashTimer.cancel();
//        e.getComponent().setVisible(true);
//        if(e.getWhen() - mousePressedTime > delay)
//            longActionPerformed(e);
//        else
//            shortActionPerformed(e);
//    }
//
//    public void shortActionPerformed(MouseEvent e){
//    }
//    public void longActionPerformed(MouseEvent e){
//        notify(new RequestMovesEvent(getIndex()));
//        try {
//            TimeUnit.MILLISECONDS.sleep(10);
//        } catch (InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }
//        notify(new FigureChosen(getIndex()));
//    }

}
