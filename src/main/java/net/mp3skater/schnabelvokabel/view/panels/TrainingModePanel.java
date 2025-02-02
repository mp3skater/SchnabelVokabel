package net.mp3skater.schnabelvokabel.view.panels;

import net.mp3skater.schnabelvokabel.model.AppState;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Fach;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Karte;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.Lernkartei;
import net.mp3skater.schnabelvokabel.model.RohdateienJava.RohdateienJava.Rohdateien.src.net.tfobz.vokabeltrainer.model.VokabeltrainerDB;
import net.mp3skater.schnabelvokabel.view.elements.BackButton;
import net.mp3skater.schnabelvokabel.view.elements.BaseButton;
import net.mp3skater.schnabelvokabel.view.elements.BaseFont;
import net.mp3skater.schnabelvokabel.view.elements.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrainingModePanel extends BasePanel {
    private JLabel l1, l2, w1;
    private BaseButton checkBtn;
    private JTextField w2;
    private Lernkartei kartei;
    private Fach fach;
    private Karte karte;
    private ArrayList<Fach> faches;

    @Override
    public void update() {
        removeAll();

        add(new BackButton());

        kartei = VokabeltrainerDB.getLernkartei(AppState.getInstance().getCurrentKarteiNummer());
        fach = VokabeltrainerDB.getFach(AppState.getInstance().getCurrentFach());
        karte = VokabeltrainerDB.getZufaelligeKarte(kartei.getNummer(), fach.getNummer());
        if(karte == null) {
            NavigationController.getInstance().goBack();
            return;
        }

        int panelWidth = 720;
        int panelHeight = 770;
        int spacing = 25;
        int descHeight = 70;
        int l1Height = 70;
        int w1Height = 70;
        int l2Height = 70;
        int btnHeight = 40;
        int w2Width = 500;

        int totalComponentsHeight = descHeight + l1Height + w1Height + l2Height + descHeight + btnHeight;
        int totalSpacing = spacing * 5;
        int totalUsedHeight = totalComponentsHeight + totalSpacing;
        int startY = (int) ((panelHeight - totalUsedHeight) / 4.5);

//        desc = new JLabel(kartei.getBeschreibung(), SwingConstants.CENTER);
//        desc.setBounds((panelWidth - descWidth) / 2, startY, descWidth, descHeight);
//        desc.setFont(new BaseFont());
//        add(desc);

        int y = startY + descHeight + spacing;
        l1 = new JLabel(kartei.getWortEinsBeschreibung(), SwingConstants.CENTER);
        l1.setFont(new BaseFont());
        l1.setBounds(0, y, panelWidth, l1Height);
        l1.setFont(new BaseFont());
        add(l1);

        y += l1Height + spacing;
        w1 = new JLabel(karte.getWortEins(), SwingConstants.CENTER);
        w1.setBounds(0, y, panelWidth, w1Height);
        w1.setFont(new BaseFont());
        add(w1);

        y += w1Height + spacing;
        l2 = new JLabel(kartei.getWortZweiBeschreibung(), SwingConstants.CENTER);
        l2.setFont(new BaseFont());
        l2.setBounds(0, y, panelWidth, l2Height);
        l2.setFont(new BaseFont());
        add(l2);

        y += l2Height + spacing;
        w2 = new JTextField();
        w2.setBounds((panelWidth - w2Width) / 2, y, w2Width, descHeight);
        w2.setFont(new BaseFont());
        add(w2);

        y += descHeight + spacing;
        checkBtn = new BaseButton("Check", 45, 550);
        checkBtn.addActionListener(e -> showAnswers());
        add(checkBtn);

        revalidate();
        repaint();
    }

    private void showAnswers() {
        Rectangle r = w2.getBounds();
        remove(w2);

        JLabel a = new JLabel(karte.getWortZwei());
        a.setBounds(r);
        a.setFont(new BaseFont());
        add(a);

        // next card
        checkBtn.setText("Continue");
        for (ActionListener al : checkBtn.getActionListeners()) {
            checkBtn.removeActionListener(al);
        }
        checkBtn.addActionListener(e -> update());

        if(check()) {
            checkBtn.setBackground(Color.green);
            if(VokabeltrainerDB.setKarteRichtig(karte) == 2) {
                VokabeltrainerDB.hinzufuegenFach(kartei.getNummer(), fach);
                VokabeltrainerDB.setKarteRichtig(karte);
            }
        }
        else {
            checkBtn.setBackground(Color.red);
            VokabeltrainerDB.setKarteFalsch(karte);
        }
        checkBtn.setOpaque(true);

        revalidate();
        repaint();
    }

    private boolean check() {
        return karte.getWortZwei().equals(w2.getText());
    }
}
