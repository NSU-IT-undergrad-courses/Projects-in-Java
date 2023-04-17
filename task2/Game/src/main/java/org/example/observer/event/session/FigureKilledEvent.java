package org.example.observer.event.session;

public class FigureKilledEvent extends GameSessionEvent {
    private final Integer source;

    private final String source_name;

    public String getSource_name() {
        return source_name;
    }

    public String getDestination_name() {
        return destination_name;
    }

    private final String destination_name;

    public Integer getSource() {
        return source;
    }


    public Integer getDestination() {
        return destination;
    }

    private final Integer destination;

    public FigureKilledEvent(Integer source, String source_name, String destination_name, Integer destination) {
        this.source = source;
        this.source_name = source_name;
        this.destination_name = destination_name;
        this.destination = destination;
    }
}
