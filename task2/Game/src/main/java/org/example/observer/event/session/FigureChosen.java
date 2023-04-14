package org.example.observer.event.session;

public class FigureChosen extends GameSessionEvent{
    private Integer index;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public FigureChosen(Integer index) {
        this.index = index;

    }
}
