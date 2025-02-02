package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.view.elements.BackButton;
import net.mp3skater.schnabelvokabel.view.elements.BaseButton;

import javax.swing.*;

public abstract class BasePanel extends JPanel implements Updatable {
    public BasePanel() {
        this.setLayout(null);
        this.add(new BackButton());
    }
}