package org.example;

import org.example.observer.Observable;
import org.example.observer.event.screens.PlacePanelEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public enum GameConfiguration {
    DEFAULT_X_RESOLUTION(1200),
    DEFAULT_Y_RESOLUTION(800),
    DEFAULT_INTERFACE_BUTTON_SIZE(80),
    DEFAULT_RADIO_BUTTON_SIZE(50),
    DEFAULT_COLOR(new Color(0x003333)),
    TEAM_DEFAULT("src/main/resources/saved/Default.txt"),
    IMG_BOARDCREATOR("/imaGes/boardcreator/"),
    IMG_START("/images/start/"),
    IMG_SETTINGS("/images/settings/"),
    IMG_FIGURES("/images/figures/"),
    IMG_TEAM("/images/team/"),
    PROFILES("/src/main/resources/profiles/","/profiles/",6,"profiles"),
    OFFLINE("src/main/resources/session/","/session/",2,"session"),
    TEAM("src/main/resources/team/","/team/",3,"team"),
    FAQ("/src/main/resources/faq/","/faq/",5,"faq"),
    SETTINGS("/src/main/resources/settings/","/settings/",4,"settings"),

    START("/src/main/resources/start/","/start/",0,"start"),
    BOARDCREATOR("/src/main/resources/boardcreator/","/boardcreator/",1,"boardcreator"),
    SCORES("/src/main/resources/scores/","/scores/",6,"scores"),

    ;

    public Integer getSIZE() {
        return SIZE;
    }

    GameConfiguration(Integer SIZE) {
        this.SIZE = SIZE;
    }

    private Integer SIZE;

    public Color getCOLOR() {
        return COLOR;
    }

    GameConfiguration(Color COLOR) {
        this.COLOR = COLOR;
    }

    private Color COLOR;
    private String DEFAULT_PATH_FILE;
    private String DEFAULT_PATH_RESOURCE;
    private Integer PANEL_INDEX;

    public String getPANEL_NAME() {
        return PANEL_NAME;
    }

    private String PANEL_NAME;
    public static final String[] distance = new String[]{"1", "2", "3", "4", "5", "6", "7"};
    public static final String[] trace = new String[]{"crosshair", "diagonal", "forward", "horse", "line", "queen"};

    GameConfiguration(String DEFAULT_PATH_RESOURCE) {
        this.DEFAULT_PATH_RESOURCE = DEFAULT_PATH_RESOURCE;
    }

    GameConfiguration(String DEFAULT_PATH_FILE, String DEFAULT_PATH_RESOURCE, Integer PANEL_INDEX, String PANEL_NAME) {
        this.DEFAULT_PATH_FILE = DEFAULT_PATH_FILE;
        this.DEFAULT_PATH_RESOURCE = DEFAULT_PATH_RESOURCE;
        this.PANEL_INDEX = PANEL_INDEX;
        this.PANEL_NAME = PANEL_NAME;
    }

    public String getDEFAULT_PATH_FILE() {
        return DEFAULT_PATH_FILE;
    }

    public String getDEFAULT_PATH_RESOURCE() {
        return DEFAULT_PATH_RESOURCE;
    }

    public Integer getPANEL_INDEX() {
        return PANEL_INDEX;
    }

    public static void SetDefaultPanel(JPanel panel, Observable observer) {
        SetPanelSizeColor(panel);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        if (observer != null){

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.RELATIVE;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 8;
        constraints.gridheight = 1;
        JButton Quit = new JButton();
        Quit.setOpaque(true);
        Quit.setBorderPainted(false);
        Quit.setFocusPainted(false);
        Quit.setContentAreaFilled(false);
        Quit.setVisible(true);
        Quit.addMouseListener(new MouseAdapter() {
            Observable o;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                o.notify(new PlacePanelEvent(GameConfiguration.START.getPANEL_INDEX()));
            }

        }.SetObservable(observer));
        panel.add(Quit, constraints);
        panel.setVisible(true);

        //---- Quit ----
        Quit.setIcon(CreateImageIcon("/images/general/quit.png", DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE(), DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE()));
    }
    }

    public static void SetPanelSizeColor(JPanel panel) {
        panel.setMaximumSize(new Dimension(DEFAULT_X_RESOLUTION.SIZE, DEFAULT_Y_RESOLUTION.SIZE));
        panel.setMinimumSize(new Dimension(DEFAULT_X_RESOLUTION.SIZE, DEFAULT_Y_RESOLUTION.SIZE));
        panel.setPreferredSize(new Dimension(DEFAULT_X_RESOLUTION.SIZE, DEFAULT_Y_RESOLUTION.SIZE));
        panel.setBackground(new Color(0x003333));
    }

    public static ImageIcon CreateImageIcon(String path, int x, int y) {
        BufferedImage name = null;
        try {
            name = ImageIO.read(Objects.requireNonNull(GameConfiguration.class.getResource(path)));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        assert name != null;
        Image dimg = name.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
    }

    public static void CheckTeamDirectory(String path, List<String> teams) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile()) {
                teams.add(listOfFiles[i].getName());
            }
        }
    }
}
