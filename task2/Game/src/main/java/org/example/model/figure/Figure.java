package org.example.model.figure;

public interface Figure {
    public String getName();
    public void setName(String name);

    public void setAttack(Integer attack_value);
    public Integer getAttack();

    public void setDefense(Integer defense_value);
    public Integer getDefense();

    public String getMove();

    public String getTrace();


}