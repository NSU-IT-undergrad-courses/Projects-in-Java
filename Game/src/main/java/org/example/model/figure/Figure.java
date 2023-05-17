package org.example.model.figure;

import java.util.List;

public interface Figure {
    String getName();

    void setName(String name);

    void setAttack(Integer attack_value);

    Integer getAttack();

    void setDefense(Integer defense_value);

    Integer getDefense();

    Integer isWhite();

    void setWhite(Integer isWhite);

    void setTrace(String[] trace_arguments);

    List<Integer> getTrace();


}