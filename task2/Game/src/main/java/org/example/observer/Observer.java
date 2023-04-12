package org.example.observer;


import org.example.observer.event.Event;

public interface Observer {
    /**
     * Обработать событие.
     *
     * @param e
     */
    void handle(Event e);
}
