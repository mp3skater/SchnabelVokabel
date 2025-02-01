package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.view.elements.BaseButton;
import net.mp3skater.schnabelvokabel.view.elements.BaseButtonHover;
import net.mp3skater.schnabelvokabel.view.elements.Colors;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class MainMenuPanel extends JPanel {
    final int buttonY = 450;
    final int distance = 90;

    public MainMenuPanel() {
        setLayout(null);

        JLabel logo = new JLabel();
        logo.setBounds(180, 20, 380, 380);
        try {
            logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/net/mp3skater/schnabelvokabel/view/icons/logo.png"))));
        } catch (NullPointerException e) {
            System.out.println("Error: Couldnt find Image (Logo)");
        }
        add(logo);

        BaseButton trainBtn = new BaseButton("Train", 45, buttonY);
        trainBtn.addActionListener(e -> NavigationController.getInstance().navigateTo("trainMenu"));

        BaseButton exportBtn = new BaseButton("Export Dictionaries", 45, buttonY + distance);
        exportBtn.addActionListener(e -> NavigationController.getInstance().navigateTo("exportScreen"));

        BaseButton settingsBtn = new BaseButton("Settings", 45, buttonY + 2*distance);
        settingsBtn.addActionListener(e -> NavigationController.getInstance().navigateTo("settings"));

        add(trainBtn);
        add(exportBtn);
        add(settingsBtn);
    }
}
