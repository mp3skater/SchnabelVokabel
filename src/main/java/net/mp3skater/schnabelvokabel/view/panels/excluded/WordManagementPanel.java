package net.mp3skater.schnabelvokabel.view.panels;

import javax.swing.*;
import java.awt.*;

public class WordManagementPanel extends BasePanel {
    public WordManagementPanel() {
        setLayout(new BorderLayout());
        add(createHeader(), BorderLayout.NORTH);

        JPanel main = new JPanel(new GridLayout(2, 1));
        JPanel wordsPanel = new JPanel(new GridLayout(0, 1));
        wordsPanel.add(new JLabel("Haus - House"));
        wordsPanel.add(new JLabel("Auto - Car"));

        JPanel settingsPanel = new JPanel(new GridLayout(2, 2));
        settingsPanel.add(new JLabel("Interval:"));
        settingsPanel.add(new JTextField("7 days"));
        settingsPanel.add(new JLabel("Deadline:"));
        settingsPanel.add(new JTextField("2023-12-31"));

        main.add(new JScrollPane(wordsPanel));
        main.add(settingsPanel);

        add(main, BorderLayout.CENTER);
    }
}