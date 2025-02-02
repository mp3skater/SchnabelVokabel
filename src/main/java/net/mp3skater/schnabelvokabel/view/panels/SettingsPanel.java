package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.Main;
import net.mp3skater.schnabelvokabel.view.elements.BaseCheckBox;

import javax.swing.*;

public class SettingsPanel extends BasePanel {
    final int x = 250;

    public SettingsPanel() {
        JCheckBox reverseLang = new BaseCheckBox("Reverse Language?", x, 200);
        reverseLang.addActionListener(e -> Main.appState.setReverseLanguage(reverseLang.isSelected()));
        JCheckBox checkCaps = new BaseCheckBox("Check Capitalization?", x, 300);
        checkCaps.addActionListener(e -> Main.appState.setCheckCapitalization(checkCaps.isSelected()));

        add(reverseLang);
        add(checkCaps);
    }

    @Override
    public void update() {}
}