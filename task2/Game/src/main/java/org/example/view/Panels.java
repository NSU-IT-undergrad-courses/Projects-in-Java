package org.example.view;

public enum Panels {
    MAIN_SCREEN,
    TEAM,
    PROFILES,
    SESSION_PREPARATION,
    GAME_SESSION,
    SETTING;
    private static final Integer x = 1280;
    private static final Integer y = 800;

    public static Integer getX() {
        return x;
    }

    public static Integer getY() {
        return y;
    }


}
