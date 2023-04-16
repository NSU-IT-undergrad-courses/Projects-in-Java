/*
 * Created by JFormDesigner on Wed Apr 12 20:31:13 NOVT 2023
 */

package org.example.view.panels.temp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author lev
 */
public class Mainee extends JPanel {
    public Mainee() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        Quit = new JButton();
        Profiles = new JButton();
        Offline = new JButton();
        Online = new JButton();
        Team = new JButton();
        Faq = new JButton();
        Git = new JButton();
        Scores = new JButton();
        Creator = new JButton();
        button1 = new JButton();

        //======== this ========
        setMaximumSize(new Dimension(1280, 800));
        setMinimumSize(new Dimension(1280, 800));
        setPreferredSize(new Dimension(1280, 800));
        setBackground(new Color(0x003333));

        //---- Quit ----
        Quit.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/quit.png")));

        //---- Profiles ----
        Profiles.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/profiles.png")));

        //---- Offline ----
        Offline.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/offline.png")));

        //---- Online ----
        Online.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/online.png")));

        //---- Team ----
        Team.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/team.png")));

        //---- Faq ----
        Faq.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/faq.png")));

        //---- Git ----
        Git.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/github.png")));

        //---- Scores ----
        Scores.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/scores.png")));

        //---- Creator ----
        Creator.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/creator.png")));

        //---- button1 ----
        button1.setIcon(new ImageIcon(getClass().getResource("/images/mainscreen/settings.png")));

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
                                .addComponent(Offline, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Faq, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                    .addGap(339, 339, 339))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(147, 147, 147))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Online, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addGap(284, 284, 284))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(112, 112, 112)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(307, 307, 307)
                    .addComponent(Online, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                    .addGap(184, 184, 184)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Offline, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(Team, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                .addComponent(Faq, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
                        .addComponent(Profiles, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Scores, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Git, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(Quit, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Creator, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
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
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
