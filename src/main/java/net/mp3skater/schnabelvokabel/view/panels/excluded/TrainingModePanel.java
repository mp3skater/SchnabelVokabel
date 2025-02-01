package net.mp3skater.schnabelvokabel.view.panels;

import javax.swing.*;
import java.awt.*;

public class TrainingModePanel extends BasePanel {
    public TrainingModePanel() {
        setLayout(new BorderLayout());
        add(createHeader(), BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(3, 1));
        center.add(new JLabel("Language 1 Word"));
        center.add(new JLabel("Language 2 Word"));
        center.add(new JTextField());

        BaseButton checkBtn = new BaseButton("Check");
        checkBtn.addActionListener(e -> {}); // Empty listener

        JPanel stats = new JPanel(new GridLayout(1, 3));
        stats.add(new JLabel("Words: 0"));
        stats.add(new JLabel("Time: 0s"));
        stats.add(new JLabel("CAPS: OFF"));

        add(stats, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(checkBtn, BorderLayout.SOUTH);
    }
}