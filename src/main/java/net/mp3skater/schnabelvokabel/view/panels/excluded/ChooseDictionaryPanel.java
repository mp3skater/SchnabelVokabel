package net.mp3skater.schnabelvokabel.view.panels;

import javax.swing.*;
import java.awt.*;

public class ChooseDictionaryPanel extends BasePanel {
    public ChooseDictionaryPanel() {
        setLayout(new BorderLayout());
        add(createHeader(), BorderLayout.NORTH);

        JPanel listPanel = new JPanel(new GridLayout(0, 1));
        // Example dictionary entry
        BaseButton dict1 = new BaseButton("My Dictionary (50 words)");
        dict1.addActionListener(e -> NavigationController.getInstance().navigateTo("trainingMode"));
        listPanel.add(dict1);

        add(new JScrollPane(listPanel), BorderLayout.CENTER);
    }
}