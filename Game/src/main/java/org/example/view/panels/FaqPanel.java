package org.example.view.panels;

import org.example.view.RootViewComponent;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static org.example.GameConfiguration.DEFAULT_X_RESOLUTION;
import static org.example.GameConfiguration.DEFAULT_Y_RESOLUTION;

public class FaqPanel extends GamePanel{

    public FaqPanel(RootViewComponent parent) {
        super(parent);
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this);
        int textwidth = DEFAULT_X_RESOLUTION.getSIZE();
        int textheight = DEFAULT_Y_RESOLUTION.getSIZE()/2;
        JTextArea faq = new JTextArea(textwidth,textheight);
        faq.setFont(new Font("PT Serif", Font.BOLD, 11));
        faq.setBackground(Color.white);
        FillFaq(faq);
        faq.setEditable(false);
        faq.setPreferredSize(new Dimension(textwidth, textheight));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.PAGE_END;
        constraints.gridheight = 10;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        this.add(faq,constraints);
        this.setVisible(true);

    }

    private void FillFaq(JTextArea jtextArea) {
        BufferedReader buff;
        try {
            buff = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/faq/" + "faq.txt"))));
            String str;
            while ((str = buff.readLine()) != null) {
                jtextArea.append("\n"+str);
            }
        } catch (IOException ignored) {
        }
    }

}
