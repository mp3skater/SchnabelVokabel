package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.view.elements.BaseButton;

import javax.swing.*;
import java.util.Objects;

public class TrainMenuPanel extends BasePanel {
    final int buttonY = 450;
    final int distance = 90;

    public TrainMenuPanel() {
        JLabel logo = new JLabel();
        logo.setBounds(180, 20, 380, 380);
        try {
            logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/net/mp3skater/schnabelvokabel/view/icons/logo.png"))));
        } catch (NullPointerException e) {
            System.out.println("Error: Couldnt find Image (Logo)");
        }

        BaseButton importBtn = new BaseButton("Import", 45, buttonY);
        importBtn.addActionListener(e -> NavigationController.getInstance().navigateTo("import"));

        BaseButton newBtn = new BaseButton("New", 45, buttonY + distance);
        newBtn.addActionListener(e -> NavigationController.getInstance().navigateTo("newDictionary"));

        BaseButton loadBtn = new BaseButton("Load", 45, buttonY + 2*distance);
        loadBtn.addActionListener(e -> NavigationController.getInstance().navigateTo("load"));

        add(importBtn);
        add(newBtn);
        add(loadBtn);
        add(logo);
    }
}