package org.example.observer.event.session;

public class FailedAttackEvent extends GameSessionEvent{
    @Override
    public String getName() {
        return name;
    }


    public FailedAttackEvent(String name, Integer damage) {
        this.name = name;
        this.damage = damage;
    }

    private String name;

    public Integer getDamage() {
        return damage;
    }

    private Integer damage;

}
