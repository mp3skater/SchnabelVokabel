package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Fach;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Lernkartei;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;
import net.mp3skater.schnabelvokabel.view.elements.BaseButton;
import net.mp3skater.schnabelvokabel.view.elements.BaseFont;

import javax.swing.*;
import java.awt.*;

public class TrainingModePanel extends BasePanel {
    private JLabel l1, l2, desc;
    private BaseButton checkBtn;
    private Lernkartei kartei;
    private Fach fach;

    @Override
    public void update() {
        kartei = VokabeltrainerDB.getLernkartei(AppState.getInstance().getCurrentKarteiNummer());
        fach = VokabeltrainerDB.getFach(AppState.getInstance().getCurrentFach());

        l1 = new JLabel(kartei.getWortEinsBeschreibung());
        l1.setBounds(245, 50, 630, 70);
        l1.setFont(new BaseFont());
        add(l1);

        l2 = new JLabel(kartei.getWortZweiBeschreibung());
        l2.setBounds(245, 350, 630, 70);
        l2.setFont(new BaseFont());
        add(l2);

        checkBtn = new BaseButton("Check", 45, 550);
        checkBtn.addActionListener(e -> {
            check();
        });
        add(checkBtn);

//        JPanel stats = new JPanel(new GridLayout(1, 3));
//        stats.add(new JLabel("Words: 0"));
//        stats.add(new JLabel("Time: 0s"));
//        stats.add(new JLabel("CAPS: OFF"));

//        add(stats);

        revalidate();
        repaint();
    }

    private boolean check() {
        return false;
    }
}