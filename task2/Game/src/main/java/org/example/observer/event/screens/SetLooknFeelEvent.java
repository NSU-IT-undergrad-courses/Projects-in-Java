package org.example.observer.event.screens;

import org.example.observer.event.Event;

import javax.swing.*;

public class SetLooknFeelEvent extends Event {
    public SetLooknFeelEvent(JRadioButton lookAndFeel) {
        LookAndFeel = lookAndFeel;
    }

    public String getLookAndFeel() {
        JRadioButton btn = LookAndFeel;
        return btn.getText();
    }

    JRadioButton LookAndFeel;
}
