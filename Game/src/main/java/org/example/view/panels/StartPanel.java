package org.example.view.panels;

import org.example.GameConfiguration;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.GameStopRequest;
import org.example.observer.event.screens.PlacePanelEvent;
import org.example.view.RootViewComponent;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static org.example.GameConfiguration.*;

/**
 * @author lev
 */
public class StartPanel extends GamePanel implements  Observer {
    private final String CLICK_SOUND = "click.wav";
    private final String ALARM_SOUND = "alarm.wav";
    private final String BRUH_SOUND = "bruh.wav";

    public StartPanel(RootViewComponent parent) {
        super(parent);
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
        GameConfiguration.SetPanelSizeColor(this);

        JButton quit = CreateMainQuitButton();

        JButton profiles = CreateDefaultMenuButton(PROFILES);
        JButton boardcreator = CreateDefaultMenuButton(BOARDCREATOR);
        JButton online = CreateDefaultMenuButton(BOARDCREATOR);
        JButton team = CreateDefaultMenuButton(TEAM);
        JButton faq = CreateDefaultMenuButton(FAQ);
        JButton scores = CreateDefaultMenuButton(SCORES);
        JButton settings = CreateDefaultMenuButton(SETTINGS);

        String GITHUB_LINK = "https://github.com/Inventorem";
        String GIT_IMAGE = "github.png";
        JButton git = CreateDefaultLinkButton(GITHUB_LINK, GIT_IMAGE);
        String TELEGRAM_LINK = "https://t.me/vonpartridge";
        String CREATOR_IMAGE = "creator.png";
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

    private JButton CreateMainQuitButton() {
        JButton quit = new JButton();
        quit.setOpaque(true);
        quit.setBorderPainted(false);
        quit.setFocusPainted(false);
        quit.setContentAreaFilled(false);
        quit.addMouseListener(new MouseAdapter() {
            private Clip clip;
            @Override
            public void mouseClicked(MouseEvent e) {
                clip.stop();
                SetActionClip(BRUH_SOUND);
                StartPanel.this.notify(new GameStopRequest());
            }

            private void SetActionClip(String bruhSound) {
                InputStream audioSrc = getClass().getResourceAsStream("/sound/" + bruhSound);
                assert audioSrc != null;
                InputStream bufferedIn = new BufferedInputStream(audioSrc);
                AudioInputStream audioStream;
                try {
                    audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SetActionClip(ALARM_SOUND);
                setBackground(Color.decode("#660000"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(DEFAULT_COLOR.getCOLOR());
                clip.stop();
            }
        });
        //---- Quit ----
        quit.setIcon(CreateImageIcon("/images/general/quit.png", DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE(), DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE()));
        return quit;
    }

    private JButton CreateDefaultMenuButton(GameConfiguration PAGE_CONSTANTS) {
        JButton MenuButton = new JButton();
        MenuButton.setOpaque(true);
        MenuButton.setBorderPainted(false);
        MenuButton.setFocusPainted(false);
        MenuButton.setContentAreaFilled(false);
        MenuButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                //read audio data from whatever source (file/classloader/etc.)
                InputStream audioSrc = getClass().getResourceAsStream("/sound/" + CLICK_SOUND);
                assert audioSrc != null;
                InputStream bufferedIn = new BufferedInputStream(audioSrc);
                AudioInputStream audioStream;
                try {
                    audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                    Clip clip;
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                    DeactivateButton(e);
                    StartPanel.this.notify(new PlacePanelEvent(PAGE_CONSTANTS.getPANEL_INDEX()));
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
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
        });
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

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream;
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
        });
        LinkButton.setIcon(CreateImageIcon
                (IMG_START.getDEFAULT_PATH_RESOURCE() + ImagePath,
                        DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE(),
                        DEFAULT_INTERFACE_BUTTON_SIZE.getSIZE()));
        return LinkButton;
    }

    @Override
    public void handle(Event e) {

    }
}
