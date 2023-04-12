package org.example.observer;

import org.example.observer.event.Event;

public interface Observable {

    /**
     * Регистрация обсервера
     *
     * @param o
     */
    void register(Observer o);

    /**
     * Деактивация обсервера
     *
     * @param o
     */
    void remove(Observer o);

    /**
     * Оповощение о событии
     *
     * @param e
     */
    void notify(Event e);

}
