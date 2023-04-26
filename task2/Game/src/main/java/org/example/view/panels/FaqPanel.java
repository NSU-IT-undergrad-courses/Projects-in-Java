package org.example.view.panels;

import org.example.observer.event.Event;
import org.example.view.RootViewComponent;

import javax.swing.*;
import java.awt.*;
public class FaqPanel extends GamePanel{

    public FaqPanel(RootViewComponent parent) {
        super(parent);
        initComponents();
    }

    private void initComponents() {
        SetDefaultPanel(this);
        JTextArea faq = new JTextArea();
        int textwidth = 800;
        int textheight = 50;
        faq.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        faq.setBackground(Color.white);
        faq.append("Просто играйте в шахматы и все будет");
        faq.setPreferredSize(new Dimension(textwidth, textheight));
        this.add(faq);

    }

}
