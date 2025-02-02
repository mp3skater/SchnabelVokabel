package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Fach;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Karte;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Lernkartei;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;
import net.mp3skater.schnabelvokabel.view.elements.BaseButton;
import net.mp3skater.schnabelvokabel.view.elements.DictionaryButton;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
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

        BaseButton newBtn = genNewButton();

        BaseButton loadBtn = new BaseButton("Load", 45, buttonY + 2*distance);
        loadBtn.addActionListener(e -> {
            LoadPanel.loadDictionaries();
            NavigationController.getInstance().navigateTo("load");
        });

        add(importBtn);
        add(newBtn);
        add(loadBtn);
        add(logo);
    }

    private BaseButton genNewButton() {
        BaseButton newBtn = new BaseButton("New", 45, buttonY + distance);
        newBtn.addActionListener(e -> {
            Lernkartei kart = new Lernkartei(1, "Neue Kartei", "lang1", "lang2",
                    AppState.getInstance().isReverseLanguage(),
                    AppState.getInstance().isCheckCapitalization());
            VokabeltrainerDB.hinzufuegenLernkartei(kart);
            VokabeltrainerDB.hinzufuegenFach(kart.getNummer(), new Fach(1, "beschreibung", 3, new Date()));
            VokabeltrainerDB.hinzufuegenKarte(kart.getNummer(), new Karte(1, "cat", "Katze",
                    AppState.getInstance().isReverseLanguage(), AppState.getInstance().isCheckCapitalization()));
            LoadPanel.addDictionaryToList(new DictionaryButton(new Rectangle(LoadPanel.x, LoadPanel.y - 70, LoadPanel.width, LoadPanel.height),
                    kart.getNummer(), kart.getBeschreibung(), kart.getWortEinsBeschreibung(),
                    kart.getWortZweiBeschreibung(), this, kart, null));
        });
        return newBtn;
    }

    @Override
    public void update() {}
}