package org.example.observer;

import org.example.observer.event.Event;

public interface Observable {

    /**
     * Регистрация обсервера
     *
     */
    void register(Observer o);

    /**
     * Деактивация обсервера
     *
     */
    void remove(Observer o);

    /**
     * Оповощение о событии
     *
     */
    void notify(Event e);

}
