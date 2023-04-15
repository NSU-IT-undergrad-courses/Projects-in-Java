/*
 * Created by JFormDesigner on Wed Apr 12 20:31:13 NOVT 2023
 */

package org.example.view.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author lev
 */
public class MainScreenPanel extends JPanel {
    public MainScreenPanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        Quit = new JButton();
        Quit.setOpaque(true);

        Profiles = new JButton();
        Profiles.setOpaque(true);

        Offline = new JButton();
        Offline.setOpaque(true);

        Online = new JButton();
        Online.setOpaque(true);

        Team = new JButton();
        Team.setOpaque(true);

        Faq = new JButton();
        Faq.setOpaque(true);

        Git = new JButton();
        Git.setOpaque(true);

        Scores = new JButton();
        Scores.setOpaque(true);


        Creator = new JButton();
        Creator.setOpaque(true);

        //======== this ========
        setMaximumSize(new Dimension(1280, 800));
        setMinimumSize(new Dimension(1280, 800));
        setPreferredSize(new Dimension(1280, 800));
        setBackground(new Color(0x003333));

        //---- Quit ----
        Quit.setIcon(CreateImageIcon("/images/mainscreen/quit.png",181,181));

        //---- Profiles ----
        Profiles.setIcon(CreateImageIcon("/images/mainscreen/profiles.png",66,66));

        //---- Offline ----
        Offline.setIcon(CreateImageIcon("/images/mainscreen/offline.png",66,66));

        //---- Online ----
        Online.setIcon(CreateImageIcon("/images/mainscreen/online.png",66,66));

        //---- Team ----
        Team.setIcon(CreateImageIcon("/images/mainscreen/team.png",87,87));

        //---- Faq ----
        Faq.setIcon(CreateImageIcon("/images/mainscreen/faq.png",87,87));

        //---- Git ----
        Git.setIcon(CreateImageIcon("/images/mainscreen/github.png",87,87));

        //---- Scores ----
        Scores.setIcon(CreateImageIcon("/images/mainscreen/scores.png",87,87));

        //---- Creator ----
        Creator.setIcon(CreateImageIcon("/images/mainscreen/creator.png",87,87));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(Profiles, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Quit, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
                    .addGap(116, 116, 116)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Git, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Scores, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Creator, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(Team, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
                                .addComponent(Offline, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(Faq, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(Online, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))))
                    .addGap(339, 339, 339))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(227, 227, 227)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(Offline, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addComponent(Online, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(Team, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addComponent(Faq, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
                        .addComponent(Profiles, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Scores, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Git, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 178, 178)
                        .addComponent(Quit, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Creator, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                    .addContainerGap(233, Short.MAX_VALUE))
        );
        setVisible(true);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
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
        ImageIcon nameimage = new ImageIcon(dimg);
        return nameimage;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton Quit;
    private JButton Profiles;
    private JButton Offline;
    private JButton Online;
    private JButton Team;
    private JButton Faq;
    private JButton Git;
    private JButton Scores;
    private JButton Creator;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
