package org.example.model.figure;

public interface figure {
    public String getName();
    public void setName();

    public void setAttack(Integer attack_value);
    public Integer getAttack();

    public void setDefense(Integer defense_value);
    public Integer getDefense();

    public String getMove();

    public String getTrace();
    public void setTrace(String trace);
}