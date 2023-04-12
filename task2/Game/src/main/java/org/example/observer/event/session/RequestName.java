package org.example.observer.event.session;

public class RequestName extends GameSessionEvent{
    private Integer index;

    public RequestName(Integer index) {
        this.index = index;
    }

}
