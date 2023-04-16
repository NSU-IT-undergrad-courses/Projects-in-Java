package org.example.view.panels;

import org.example.observer.Observable;
import org.example.observer.Observer;
import org.example.observer.event.Event;
import org.example.observer.event.screens.*;
import org.example.view.Panels;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lev
 */
public class MainScreenPanel extends JPanel implements Observable {

    private final List<Observer> observers = new ArrayList<>();
    private JButton Settings;
    private JButton Quit;
    private JButton Profiles;
    private JButton Offline;
    private JButton Online;
    private JButton Team;
    private JButton Faq;
    private JButton Git;
    private JButton Scores;
    private JButton Creator;
    private Color originalbg;
    private JDialog faqwindow = new JDialog();
    private String click = "click.wav";
    private String mygithub = "https://github.com/Inventorem";
    private String mytelegram = "vonpartridge";

    public MainScreenPanel() {
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
        Quit = new JButton();
        Quit.setOpaque(true);
        Quit.setBorderPainted(false);
        Quit.setFocusPainted(false);
        Quit.setContentAreaFilled(false);
        Quit.addMouseListener(new MouseAdapter() {
            Observable o;
            String alarm = "quitalarm.wav";
            String bruh = "bruh.wav";
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + bruh)));
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
                originalbg = getBackground();
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + alarm)));
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
                setBackground(originalbg);
                clip.stop();
            }
        }.SetObservable(this));

        Profiles = new JButton();
        Profiles.setOpaque(true);
        Profiles.setBorderPainted(false);
        Profiles.setFocusPainted(false);
        Profiles.setContentAreaFilled(false);
        Profiles.addMouseListener(new MouseAdapter() {
            Observable o;
            String profiles = "click.wav";
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + profiles)));
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
                o.notify(new PlaceProfilesEvent());
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

        Offline = new JButton();
        Offline.setOpaque(true);
        Offline.setBorderPainted(false);
        Offline.setFocusPainted(false);
        Offline.setContentAreaFilled(false);
        Offline.addMouseListener(new MouseAdapter() {
            Observable o;
            String profiles = "click.wav";
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + profiles)));
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
                o.notify(new PlaceSessionEvent());
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

        Online = new JButton();
        Online.setOpaque(true);
        Online.setBorderPainted(false);
        Online.setFocusPainted(false);
        Online.setContentAreaFilled(false);
        Online.addMouseListener(new MouseAdapter() {
            Observable o;
            String profiles = "click.wav";
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + profiles)));
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
                o.notify(new PlaceProfilesEvent());
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

        Team = new JButton();
        Team.setOpaque(true);
        Team.setBorderPainted(false);
        Team.setFocusPainted(false);
        Team.setContentAreaFilled(false);
        Team.addMouseListener(new MouseAdapter() {
            Observable o;
            String profiles = "click.wav";
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + profiles)));
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
                o.notify(new PlaceProfilesEvent());
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

        Faq = new JButton();
        Faq.setOpaque(true);
        Faq.setBorderPainted(false);
        Faq.setFocusPainted(false);
        Faq.setContentAreaFilled(false);
        Faq.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addFaqArea();
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

        Git = new JButton();
        Git.setOpaque(true);
        Git.setBorderPainted(false);
        Git.setFocusPainted(false);
        Git.setContentAreaFilled(false);
        Git.addMouseListener(new MouseAdapter() {
            Observable o;
            String profiles = "click.wav";
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + profiles)));
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
                try {
                    URI URImygithub;
                    try {
                        URImygithub= new URI("https://github.com/Inventorem");
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

        Scores = new JButton();
        Scores.setOpaque(true);
        Scores.setBorderPainted(false);
        Scores.setFocusPainted(false);
        Scores.setContentAreaFilled(false);
        Scores.addMouseListener(new MouseAdapter() {
            Observable o;
            String profiles = "click.wav";
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + profiles)));
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
                o.notify(new PlaceProfilesEvent());
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

        Creator = new JButton();
        Creator.setOpaque(true);
        Creator.setBorderPainted(false);
        Creator.setFocusPainted(false);
        Creator.setContentAreaFilled(false);
        Creator.addMouseListener(new MouseAdapter() {
            Observable o;
            String profiles = "click.wav";
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + profiles)));
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
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("https://"+"t.me/"+mytelegram));
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
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

        Settings = new JButton();
        Settings.setOpaque(true);
        Settings.setBorderPainted(false);
        Settings.setFocusPainted(false);
        Settings.setContentAreaFilled(false);
        Settings.addMouseListener(new MouseAdapter() {
            Observable o;
            String profiles = "click.wav";
            private Clip clip;

            public MouseListener SetObservable(Observable o) {
                this.o = o;
                return this;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/sound/" + profiles)));
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
                o.notify(new PlaceProfilesEvent());
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
        //======== this ========
        setMaximumSize(new Dimension(1280, 800));
        setMinimumSize(new Dimension(1280, 800));
        setPreferredSize(new Dimension(1280, 800));
        setBackground(new Color(0x003333));

        //---- Quit ----
        Quit.setIcon(CreateImageIcon("/images/mainscreen/quit.png", 87, 87));

        //---- Profiles ----
        Profiles.setIcon(CreateImageIcon("/images/mainscreen/profiles.png", 66, 66));

        //---- Offline ----
        Offline.setIcon(CreateImageIcon("/images/mainscreen/offline.png", 100, 100));

        //---- Online ----
        Online.setIcon(CreateImageIcon("/images/mainscreen/online.png", 100, 100));

        //---- Team ----
        Team.setIcon(CreateImageIcon("/images/mainscreen/team.png", 87, 87));

        //---- Faq ----
        Faq.setIcon(CreateImageIcon("/images/mainscreen/faq.png", 87, 87));

        //---- Git ----
        Git.setIcon(CreateImageIcon("/images/mainscreen/github.png", 87, 87));

        //---- Scores ----
        Scores.setIcon(CreateImageIcon("/images/mainscreen/scores.png", 87, 87));

        //---- Creator ----
        Creator.setIcon(CreateImageIcon("/images/mainscreen/creator.png", 87, 87));

        //---- Settings ----
        Settings.setIcon(CreateImageIcon("/images/mainscreen/settings.png", 87, 87));

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);
        constraints.weightx = 1;

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridheight = 2;
        add(Profiles, constraints);

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 8;
        constraints.gridheight = 1;
        add(Quit, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 8;
        add(Git, constraints);
        add(Settings, constraints);
        add(Scores, constraints);
        add(Creator, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        add(Online, constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        add(Offline, constraints);

        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        add(Team, constraints);

        GridBagConstraints FaqSettings = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        add(Faq, constraints);

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
        faqwindow.setLocation(Panels.getX() / 2, Panels.getY() / 2);
        faqwindow.setVisible(true);
    }

    private ImageIcon CreateImageIcon(String path, int x, int y) {
        BufferedImage name = null;
        try {
            name = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        assert name != null;
        Image dimg = name.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
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
}
