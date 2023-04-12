/*
 * Created by JFormDesigner on Wed Apr 12 20:31:13 NOVT 2023
 */

package org.example.view.panels;

import javax.swing.*;
import java.awt.*;

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
        Profiles = new JButton();
        Offline = new JButton();
        Online = new JButton();
        Team = new JButton();
        Faq = new JButton();
        Git = new JButton();
        Scores = new JButton();
        Creator = new JButton();

        //======== this ========
        setMaximumSize(new Dimension(1280, 800));
        setMinimumSize(new Dimension(1280, 800));
        setPreferredSize(new Dimension(1280, 800));

        //---- Quit ----
        Quit.setText("Quit");

        //---- Profiles ----
        Profiles.setText("Profiles");

        //---- Offline ----
        Offline.setText("OFFLINE");

        //---- Online ----
        Online.setText("ONLINE");

        //---- Team ----
        Team.setText("Team");

        //---- Faq ----
        Faq.setText("FAQ");

        //---- Git ----
        Git.setText("GIT");

        //---- Scores ----
        Scores.setText("SCORES");

        //---- Creator ----
        Creator.setText("CREATOR");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(Quit, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Profiles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(55, 55, 55)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(Offline, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Team, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
                                                .addGap(29, 29, 29)
                                                .addGroup(layout.createParallelGroup()
                                                        .addComponent(Online, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Faq, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap(495, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                                                .addComponent(Git)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Scores)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Creator)
                                                .addGap(339, 339, 339))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(227, 227, 227)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(Offline, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Online, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(230, 230, 230)
                                                .addComponent(Profiles)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(Faq)
                                        .addComponent(Team))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 348, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(Quit, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(Git)
                                                .addComponent(Scores)
                                                .addComponent(Creator)))
                                .addGap(19, 19, 19))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
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
