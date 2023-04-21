package org.example.view;

import org.example.GameConstants;
import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.GameStopEvent;
import org.example.observer.event.screens.PlacePanelEvent;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.GameConstants.*;

/**
 * @author lev
 */
public class StartPanel extends JPanel implements Observable {
    private final String CLICK_SOUND = "click.wav";
    private final String ALARM_SOUND = "alarm.wav";
    private final String BRUH_SOUND = "bruh.wav";
    private final String GITHUB_LINK = "https://github.com/Inventorem";
    private final String GIT_IMAGE = "github.png";
    private final String TELEGRAM_LINK = "https://t.me/vonpartridge";
    private final String CREATOR_IMAGE = "creator.png";

    private final List<Observer> observers = new ArrayList<>();
    private final JDialog faqwindow = new JDialog();

    public StartPanel() {
        initComponents();
    }

    private static void DeactivateButton(MouseEvent e) {
        ((JButton) e.getSource()).setOpaque(false);
    }

    private static void PaintActiveButton(MouseEvent e) {
        ((JButton) e.getSource()).setOpaque(true);
        ((JButton) e.getSource()).setForeground(Color.LIGHT_GRAY);
    }

    private void initComponents() {
        GameConstants.SetPanelSizeColor(this);

        JButton quit = CreateMainQuitButton();

        JButton profiles = CreateDefaultMenuButton(PROFILES);
        JButton boardcreator = CreateDefaultMenuButton(BOARDCREATOR);
        JButton online = CreateDefaultMenuButton(BOARDCREATOR);
        JButton team = CreateDefaultMenuButton(TEAM);
        JButton faq = CreateDefaultMenuButton(FAQ);
        JButton scores = CreateDefaultMenuButton(SCORES);
        JButton settings = CreateDefaultMenuButton(SETTINGS);

        JButton git = CreateDefaultLinkButton(GITHUB_LINK, GIT_IMAGE);
        JButton creator = CreateDefaultLinkButton(TELEGRAM_LINK, CREATOR_IMAGE);


        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);
        constraints.weightx = 1;

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridheight = 2;
        add(profiles, constraints);

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 8;
        constraints.gridheight = 1;
        add(quit, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 8;
        add(git, constraints);
        add(settings, constraints);
        add(scores, constraints);
        add(creator, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        add(online, constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        add(boardcreator, constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        add(team, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        add(faq, constraints);

        revalidate();
        repaint();
        setVisible(true);
    }

    private void addFaqArea() {
        JTextArea faq = new JTextArea();
        int textwidth = 800;
        int textheight = 50;
        faq.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        faq.setBackground(Color.white);
        faq.append("Просто играйте в шахматы и все будет");
        faq.setPreferredSize(new Dimension(textwidth, textheight));
        faqwindow.add(faq);
        faqwindow.pack();
        faqwindow.setLocation(DEFAULT_X_RESOLUTION.getSIZE() / 2, DEFAULT_Y_RESOLUTION.getSIZE() / 2);
        faqwindow.setVisible(true);
    }

    private JButton CreateMainQuitButton() {
        JButton quit = new JButton();
        quit.setOpaque(true);
        quit.setBorderPainted(false);
        quit.setFocusPainted(false);
        quit.setContentAreaFilled(false);
        quit.addMouseListener(new MouseAdapter() {
            Observable o;
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                clip.stop();
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + BRUH_SOUND)));
                } catch (UnsupportedAudioFileException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    clip.open(audioInputStream);
                } catch (LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                clip.start();
                o.notify(new GameStopEvent());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + ALARM_SOUND)));
                } catch (UnsupportedAudioFileException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    clip.open(audioInputStream);
                } catch (LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                clip.start();
                setBackground(Color.decode("#660000"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(DEFAULT_COLOR.getCOLOR());
                clip.stop();
            }
        }.SetObservable(this));
        //---- Quit ----
        quit.setIcon(CreateImageIcon("/images/general/quit.png", DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE(), DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE()));
        return quit;
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void remove(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notify(Event e) {
        for (Observer o : observers) {
            o.handle(e);
        }
    }

    private JButton CreateDefaultMenuButton(GameConstants PAGE_CONSTANTS) {
        JButton MenuButton = new JButton();
        MenuButton.setOpaque(true);
        MenuButton.setBorderPainted(false);
        MenuButton.setFocusPainted(false);
        MenuButton.setContentAreaFilled(false);
        MenuButton.addMouseListener(new MouseAdapter() {
            Observable o;
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + CLICK_SOUND)));
                } catch (UnsupportedAudioFileException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    clip.open(audioInputStream);
                } catch (LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                clip.start();
                DeactivateButton(e);
                o.notify(new PlacePanelEvent(PAGE_CONSTANTS.getPANEL_INDEX()));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                PaintActiveButton(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                DeactivateButton(e);
            }
        }.SetObservable(this));
        MenuButton.setIcon(CreateImageIcon
                (IMG_START.getDEFAULT_PATH_RESOURCE() + PAGE_CONSTANTS.getPANEL_NAME() + ".png",
                        DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE(),
                        DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE()));
        return MenuButton;
    }

    private JButton CreateDefaultLinkButton(String Link, String ImagePath) {
        JButton LinkButton = new JButton();
        LinkButton.setOpaque(true);
        LinkButton.setBorderPainted(false);
        LinkButton.setFocusPainted(false);
        LinkButton.setContentAreaFilled(false);
        LinkButton.addMouseListener(new MouseAdapter() {
            Observable o;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + CLICK_SOUND)));
                } catch (UnsupportedAudioFileException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                Clip clip;
                try {
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    clip.open(audioInputStream);
                } catch (LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                clip.start();
                try {
                    URI URImygithub;
                    try {
                        URImygithub = new URI(Link);
                    } catch (URISyntaxException eURI) {
                        throw new RuntimeException(eURI);
                    }
                    Desktop.getDesktop().browse(URImygithub);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                PaintActiveButton(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                DeactivateButton(e);
            }
        }.SetObservable(this));
        LinkButton.setIcon(CreateImageIcon
                (IMG_START.getDEFAULT_PATH_RESOURCE() + ImagePath,
                        DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE(),
                        DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE()));
        return LinkButton;
    }

}
