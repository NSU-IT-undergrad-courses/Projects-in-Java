package org.example.view;

import java.awt.*;

public enum Panels {
    MAIN_SCREEN,
    TEAM,
    PROFILES,
    SESSION_PREPARATION,
    GAME_SESSION,
    SETTING;
    private static Integer x = 1280;
    private static Integer y = 800;
    public static Integer getX() {
        return x;
    }
    public static Integer getY() {
        return y;
    }


}