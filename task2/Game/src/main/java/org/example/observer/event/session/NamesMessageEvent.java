package org.example.observer.event.session;

public class NamesMessageEvent extends GameSessionEvent {
    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    String[] names = new String[64];

    public NamesMessageEvent(String[] names) {
        this.names = names;
    }
}
